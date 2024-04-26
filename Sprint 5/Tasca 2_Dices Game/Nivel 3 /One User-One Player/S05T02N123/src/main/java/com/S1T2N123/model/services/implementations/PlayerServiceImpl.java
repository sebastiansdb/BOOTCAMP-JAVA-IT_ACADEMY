package com.S1T2N123.model.services.implementations;

import com.S1T2N123.conversions.PlayerEntityMapper;
import com.S1T2N123.exceptions.EmptyListException;
import com.S1T2N123.exceptions.ResourceNotFoundException;
import com.S1T2N123.model.domain.PlayerEntity;
import com.S1T2N123.model.dto.PlayerEntityDTO;
import com.S1T2N123.model.repositories.PlayerRepository;
import com.S1T2N123.model.repositories.UserRepository;
import com.S1T2N123.model.services.interfaces.GameService;
import com.S1T2N123.model.services.interfaces.JwtService;
import com.S1T2N123.model.services.interfaces.PlayerService;
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

    // add new player
    @Override
    public PlayerEntityDTO addNewPlayer(PlayerEntityDTO playerEntityDTOToAdd){
        // Aquí cargo fecha de creación del playerDTO
        PlayerEntity playerEntityToAdd = PlayerEntityMapper.mapPlayerEntityDTOToNewPlayerEntity(playerEntityDTOToAdd);
        if (playerEntityToAdd.getPlayerName().equals("null")) {
            playerEntityToAdd.setPlayerName("ANONYMOUS");
            return PlayerEntityMapper.mapNewPlayerEntityToDTO(playerRepository.save(playerEntityToAdd));
        } else if (playerRepository.existsByPlayerName(playerEntityToAdd.getPlayerName())) {
            throw new EntityExistsException("The player " + playerEntityDTOToAdd.getPlayerName() + " already exists");
        } else {
            return PlayerEntityMapper.mapNewPlayerEntityToDTO(playerRepository.save(playerEntityToAdd));
        }
    }
    
    // update player´s name
    @Override
    // En el caso de PLAYER ANONIMO, actualizará el nombre del primer jugador con nombre "anónimo" que se encuentre en
    // la lista.
    public PlayerEntityDTO updatePlayerName (String[] updatedPlayerNameArr){
        Optional<PlayerEntity> playerToUpdate = playerRepository.findByPlayerName(updatedPlayerNameArr[0]);
        if (playerToUpdate.isPresent()) {
            // check que el nombre actualizado del jugador no exista previamente en la BBDD
            if (playerRepository.existsByPlayerName(updatedPlayerNameArr[1])){
                throw new EntityExistsException("The player with name " + updatedPlayerNameArr[1] + " already exists");
            }
            playerToUpdate.get().setPlayerName(updatedPlayerNameArr[1]);
            return PlayerEntityMapper.mapExistingPlayerEntityToDTO(playerRepository.save(playerToUpdate.get()));
        } else {
            throw new ResourceNotFoundException("The player", "Name", updatedPlayerNameArr[0]);
        }
    }

    // get all players and each average success rate
    @Override
    public List<PlayerEntityDTO> getAllPlayers() {
        List<PlayerEntity> playerEntitiesList = playerRepository.findAll();
        return playerEntitiesList.stream()
                .map(PlayerEntityMapper::mapExistingPlayerEntityToDTO).collect(Collectors.toList());
    }

    // Get the average success rate of all players
    @Override
    public Double getAverageSuccessRate(){
        // checkeo esto primero, para no tener que cargarme todos los jugadores si no hay jugadas hechas
        if(!gameServiceImpl.checkIfRegisteredGames()){
            throw new EmptyListException("There are no players who have been played yet");
        }
        // filtro los jugadores que han jugado al menos una vez con "getPlayersWhoPlayed()"
        return getPlayersDtoWhoPlayed().stream()
                .mapToDouble(PlayerEntityDTO::getAverageWin)
                .average()
        // no tendria porque lanzar excepción porque está checkeada lista Games no vacia arriba. Igual el ide se queja si sólo pongo "getAsDouble()"
                .orElseThrow(()-> new EmptyListException("There are no players who have been played yet"));
    }

    // Get the the loser player ( player/s with the worst success rate )
    @Override
    // Si hay mas de un jugador perdedor, devuelvo todos ellos.
    public List<PlayerEntityDTO> getLoserPlayer(){
        // checkeo esto primero, para no tener que cargarme todos los jugadores si no hay jugadas hechas
        if(gameServiceImpl.checkIfRegisteredGames()){
            // Retrieve the list of player entities who have played from BBDD
            List<PlayerEntityDTO> playerEntityDtoList = getPlayersDtoWhoPlayed();
            // si hay un solo jugador que jugó y éste ha ganado alguna partida, no hay perdedor
            if (playerEntityDtoList.size() == 1 && playerEntityDtoList.getFirst().getAverageWin() > 0 ){
                throw new EntityNotFoundException("\"There are registered single-player plays and he has won. " +
                        "Therefore, there are no losers");
            }
            // Second, find the minimum average success rate from all players.
            double minimumSuccessRate = playerEntityDtoList.stream()
                    .mapToDouble(PlayerEntityDTO::getAverageWin)
                    .min()
                    // no tendria porque lanzar excepción porque está checkeada lista Games no vacia arriba. Igual el ide se queja si sólo pongo "getAsDouble()"
                    .orElseThrow(()-> new EmptyListException("There are no players who have been played yet"));
            // third, get the player or those players who have lost.
            return playerEntityDtoList.stream()
                    .filter(playerEntityDTO -> playerEntityDTO.getAverageWin() == minimumSuccessRate)
                    .collect(Collectors.toList());
        } else {
            throw new EmptyListException("There are no players who have been played yet");
        }

    }

    // get the WINNER ( player with the best success rate )
    @Override
    public List<PlayerEntityDTO> getWinnerPlayer(){
        // checkeo esto primero, para no tener que cargar todos los jugadores si no hay jugadas hechas
        if(!gameServiceImpl.checkIfRegisteredGames()){
            throw new EmptyListException("There are no players who have been played yet");
        }
        // Retrieve the list of players entities who have played from BBDD
        List<PlayerEntityDTO> playerEntityDtoList = getPlayersDtoWhoPlayed();
        // si hay un solo jugador que jugó y no ha ganado nada, entra aquí:
        if (playerEntityDtoList.size() == 1 && playerEntityDtoList.getFirst().getAverageWin() == 0 ){
            throw new EntityNotFoundException("There is just one registered single-player plays and he has lost. " +
                    "Therefore, there are no winners");
        }
        // Second, find the maximum average success rate from all players.
        double maximumSuccesRate = playerEntityDtoList.stream()
                .mapToDouble(PlayerEntityDTO::getAverageWin)
                .max()
        // no tendria porque lanzar excepción porque está checkeada lista Games no vacia arriba. Igual el ide se queja si sólo pongo "getAsDouble()"
                .orElseThrow(()-> new EmptyListException("There are no players who have been played yet"));
        // third, get the player or those players who have won.
        return playerEntityDtoList.stream()
                .filter(playerEntityDTO -> playerEntityDTO.getAverageWin() == maximumSuccesRate)
                .collect(Collectors.toList());

    }

    //              Metodos de logica interna del service ( estos no son invocados desde el controlador )             //

    public List<PlayerEntityDTO> getPlayersDtoWhoPlayed(){
        // filtro los jugadores que han realizado por lo menos una partida
        return playerRepository.findAll().stream()
                .filter(playerEntity -> !playerEntity.getGames().isEmpty())
                .map(PlayerEntityMapper::mapExistingPlayerEntityToDTO)
                .collect(Collectors.toList());
    }
}
