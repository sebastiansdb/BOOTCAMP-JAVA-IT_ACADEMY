package com.S05T02N123.model.services.implementations;

import com.S05T02N123.conversions.PlayerEntityMapper;
import com.S05T02N123.exceptions.EmptyListException;
import com.S05T02N123.exceptions.ResourceNotFoundException;
import com.S05T02N123.model.domain.PlayerEntity;
import com.S05T02N123.model.dto.PlayerEntityDTO;
import com.S05T02N123.model.repositories.PlayerRepository;
import com.S05T02N123.model.repositories.UserRepository;
import com.S05T02N123.model.services.interfaces.GameService;
import com.S05T02N123.model.services.interfaces.JwtService;
import com.S05T02N123.model.services.interfaces.PlayerService;
import com.S05T02N123.security.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final GameService gameServiceImpl;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    // "CREATE A NEW PLAYER"
    public PlayerEntityDTO addNewPlayer(PlayerEntityDTO playerEntityDTOToAdd, String jwtToken){
        String userEmail = jwtService.extractUserEmail(jwtToken);
        // Si la la linea de ejecución llegó hasta aquí, es porque el usuario está en la BBDD
        User actualUser = userRepository.findByEmail(userEmail).orElse(null);
        // cargo fecha de creación del jugador
        PlayerEntity playerEntityToAdd = PlayerEntityMapper.mapPlayerEntityDTOToNewPlayerEntity(playerEntityDTOToAdd);
        playerEntityToAdd.setUserEmail(userEmail);
        if (playerEntityToAdd.getPlayerName().equals("null")) {
            playerEntityToAdd.setPlayerName("ANONYMOUS");
            assert actualUser != null;
            actualUser.setPlayersIds(playerEntityToAdd.getPlayerId());
            userRepository.save(actualUser);
            return PlayerEntityMapper.mapNewPlayerEntityToDTO(playerRepository.save(playerEntityToAdd));
        } else if (playerRepository.existsByPlayerName(playerEntityToAdd.getPlayerName())) {
            throw new EntityExistsException("The player '" + playerEntityDTOToAdd.getPlayerName() + "' already exists");
        }
            playerEntityToAdd = playerRepository.save(playerEntityToAdd);
            assert actualUser != null;
            actualUser.setPlayersIds(playerEntityToAdd.getPlayerId());
            userRepository.save(actualUser);
            return PlayerEntityMapper.mapNewPlayerEntityToDTO(playerEntityToAdd);
        }

    // Update player´s name
    @Override
    // En el caso de PLAYER ANONIMO, actualizará el nombre del primer jugador con nombre "anónimo" que se encuentre en
    // la lista.
    public PlayerEntityDTO updatePlayerName (String[] updatedPlayerNameArr, String jwtToken){
        String userEmail = jwtService.extractUserEmail(jwtToken);
        // Si la la linea de ejecución llegó hasta aquí, es porque el usuario está en la BBDD
        User actualUser = userRepository.findByEmail(userEmail).orElse(null);
        Optional<PlayerEntity> playerToUpdate = playerRepository.findByPlayerName(updatedPlayerNameArr[0]);
        // check que exista el player
        if (playerToUpdate.isPresent()) {
            assert actualUser != null;
            // check que el usuario tenga cargado el player buscado para actualizar
            if(actualUser.getPlayersIds().stream().anyMatch(id -> id.equals(playerToUpdate.get().getPlayerId()))){
                // check que el nombre actualizado del jugador no exista previamente en la BBDD
                if (playerRepository.existsByPlayerName(updatedPlayerNameArr[1])){
                    throw new EntityExistsException("The player with name '" + updatedPlayerNameArr[1] + "' already exists");
                }
                playerToUpdate.get().setPlayerName(updatedPlayerNameArr[1]);
                return PlayerEntityMapper.mapExistingPlayerEntityToDTO(playerRepository.save(playerToUpdate.get()));
            } else{
                throw new EntityNotFoundException("The player with name '" + updatedPlayerNameArr[0] +
                        "' does not belongs to your user");
            }
        } else {
            throw new ResourceNotFoundException("Player", "Name", updatedPlayerNameArr[0]);
        }
    }

    // Get all players and each average success rate
    @Override
    public List<PlayerEntityDTO> getAllPlayers(String jwtToken) {
        String userEmail = jwtService.extractUserEmail(jwtToken);
        // Si la la linea de ejecución llegó hasta aquí, es porque el usuario está en la BBDD
        User actualUser = userRepository.findByEmail(userEmail).orElse(null);
        assert actualUser != null;
        if((actualUser.getPlayersIds().isEmpty())){
            throw new EmptyListException("Your user has no players");
        } else {
            List<PlayerEntity> playerEntitiesList = playerRepository.findAll();
            return playerEntitiesList.stream()
                    .map(PlayerEntityMapper::mapExistingPlayerEntityToDTO).collect(Collectors.toList());
        }
    }

    // Get the average success rate of all players
    @Override
    public Double getAverageSuccessRate(String jwtToken){
        String userEmail = jwtService.extractUserEmail(jwtToken);
        // Si la la linea de ejecución llegó hasta aquí, es porque el usuario está en la BBDD
        User actualUser = userRepository.findByEmail(userEmail).orElse(null);
        assert actualUser != null;
        if((actualUser.getPlayersIds().isEmpty())){
            throw new EmptyListException("Your user has no players");
        } else {
            if(!gameServiceImpl.checkIfAnyRegisteredGames(actualUser)){
                throw new EmptyListException("You have no players who have been played yet");
            }
            // filtro los jugadores que han jugado al menos una vez con "getPlayersWhoPlayed()"
            return getPlayersDtoWhoPlayed().stream()
                    .mapToDouble(PlayerEntityDTO::getAverageWin)
                    .average()
                    // no tendria porque lanzar excepción porque está checkeada lista Games no vacia arriba. Igual el ide se queja si sólo pongo "getAsDouble()"
                    .orElseThrow(()-> new EmptyListException("You have no players who have been played yet"));
        }
    }
    /**
     + * Get the loser player(s) with the worst success rate.
     + *
     + * @param jwtToken the JWT token for authentication
     + * @return a list of PlayerEntityDTO representing the loser player(s)
     */
    @Override
    public List<PlayerEntityDTO> getLoserPlayer(String jwtToken){
        String userEmail = jwtService.extractUserEmail(jwtToken);
        // Si la la linea de ejecución llegó hasta aquí, es porque el usuario está en la BBDD
        User actualUser = userRepository.findByEmail(userEmail).orElse(null);
        assert actualUser != null;
        if((actualUser.getPlayersIds().isEmpty())){
            throw new EmptyListException("Your user has no players");
        } else{
            // checkeo esto primero, para no tener que cargarme todos los jugadores si no hay jugadas hechas
            if(!gameServiceImpl.checkIfAnyRegisteredGames(actualUser)){
                throw new EmptyListException("You have no players who have been played yet");
            }
            // Retrieve the list of player entities who have played from BBDD
            List<PlayerEntityDTO> playerEntityDtoList = getPlayersDtoWhoPlayed();
            // si hay un solo jugador que jugó y éste ha ganado alguna partida, no hay perdedor
            if (playerEntityDtoList.size() == 1 && playerEntityDtoList.getFirst().getAverageWin() > 0 ){
                throw new EntityNotFoundException("\"You have a registered single-player plays and he has won. " +
                        "Therefore, there are no losers");
            }
            // Find the minimum average success rate from all players.
            double minimumSuccessRate = playerEntityDtoList.stream()
                    .mapToDouble(PlayerEntityDTO::getAverageWin)
                    .min()
                    // no tendria porque lanzar excepción porque está checkeada lista Games no vacia arriba. Igual el ide se queja si sólo pongo "getAsDouble()"
                    .orElseThrow(()-> new EmptyListException("You have no players who have been played yet"));
            // Get the player(s) who have lost (player(s) with the minimum success rate)
            return playerEntityDtoList.stream()
                    .filter(playerEntityDTO -> playerEntityDTO.getAverageWin() == minimumSuccessRate)
                    .collect(Collectors.toList());
        }
    }

    // Get WINNER/S ( player(s) with the best success rate )
    @Override
    public List<PlayerEntityDTO> getWinnerPlayer(String jwtToken){
        String userEmail = jwtService.extractUserEmail(jwtToken);
        // Si la la linea de ejecución llegó hasta aquí, es porque el usuario está en la BBDD
        User actualUser = userRepository.findByEmail(userEmail).orElse(null);
        assert actualUser != null;
        if((actualUser.getPlayersIds().isEmpty())){
            throw new EmptyListException("Your user has no players");
        } else {
            // checkeo esto primero, para no tener que cargar todos los jugadores si no hay jugadas hechas
            if(!gameServiceImpl.checkIfAnyRegisteredGames(actualUser)){
                throw new EmptyListException("You have no players who have been played yet");
            }
            // Retrieve the list of players entities who have played from BBDD
            List<PlayerEntityDTO> playerEntityDtoList = getPlayersDtoWhoPlayed();
            // si hay un solo jugador que jugó y no ha ganado nada, entra aquí:
            if (playerEntityDtoList.size() == 1 && playerEntityDtoList.getFirst().getAverageWin() == 0 ){
                throw new EntityNotFoundException("You have just one registered single-player plays and he has lost. " +
                        "Therefore, there are no winners");
            }
            // Second, find the maximum average success rate from all players.
            double maximumSuccesRate = playerEntityDtoList.stream()
                    .mapToDouble(PlayerEntityDTO::getAverageWin)
                    .max()
                    // no tendria porque lanzar excepción porque está checkeada lista Games no vacia arriba. Igual el ide se queja si sólo pongo "getAsDouble()"
                    .orElseThrow(()-> new EmptyListException("You have no players who have been played yet"));
            // third, get the player or those players who have won.
            return playerEntityDtoList.stream()
                    .filter(playerEntityDTO -> playerEntityDTO.getAverageWin() == maximumSuccesRate)
                    .collect(Collectors.toList());
        }
    }

    /**              Metodos de logica interna del service ( estos no son invocados desde el controlador )             */

    public List<PlayerEntityDTO> getPlayersDtoWhoPlayed(){
        // filtro los jugadores que han realizado por lo menos una partida
        return playerRepository.findAll().stream()
                .filter(playerEntity -> !playerEntity.getGames().isEmpty())
                .map(PlayerEntityMapper::mapExistingPlayerEntityToDTO)
                .collect(Collectors.toList());
    }
}
