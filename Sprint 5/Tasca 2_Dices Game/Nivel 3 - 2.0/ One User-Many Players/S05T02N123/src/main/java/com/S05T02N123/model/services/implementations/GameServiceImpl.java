package com.S05T02N123.model.services.implementations;

import com.S05T02N123.conversions.GameEntityMapper;
import com.S05T02N123.exceptions.EmptyListException;
import com.S05T02N123.exceptions.ResourceNotFoundException;
import com.S05T02N123.model.domain.GameEntity;
import com.S05T02N123.model.domain.PlayerEntity;
import com.S05T02N123.model.dto.GameEntityDTO;
import com.S05T02N123.model.repositories.GameRepository;
import com.S05T02N123.model.repositories.PlayerRepository;
import com.S05T02N123.model.repositories.UserRepository;
import com.S05T02N123.model.services.interfaces.GameService;
import com.S05T02N123.model.services.interfaces.JwtService;
import com.S05T02N123.security.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    // New Game
    @Override
    public GameEntityDTO newGame (Long playerId , String jwtToken) {
        String userEmail = jwtService.extractUserEmail(jwtToken);
        // Si la la linea de ejecución llegó hasta aquí, es porque el usuario está en la BBDD
        User actualUser = userRepository.findByEmail(userEmail).orElse(null);
        assert actualUser != null;
        if(actualUser.getPlayersIds().isEmpty()){
            throw new EmptyListException("Your user has no players");
        }
        PlayerEntity playerPlaying = playerRepository.findById(playerId)               // busco jugador que quiere jugar
                .orElseThrow(() -> new ResourceNotFoundException("Player not found","id",playerId)); // check que exista
        if(actualUser.getPlayersIds().stream().anyMatch(id -> id.equals(playerPlaying.getPlayerId()))){
            GameEntity newGameEntity = rollDices();                     // creo un juego nuevo y lanzo dados
            addNewGameToPLayer(playerPlaying,newGameEntity);            // agrego el  juego nuevo al jugador
            playerRepository.save(playerPlaying);                       // actualizo jugador en BBDD con su nueva jugada
            // La línea de abajo ("setGameId(---)") debe ir después de guardar el jugador con la nueva jugada en BBDD,
            // porque es en ese momento que se autogenera el gameId en el juego nuevo creado. Antes de la persistencia
            // del jugador con la nueva jugada, dicho id es null

            // Guardo, en la entidad que devuelvo al controlador, el id del nuevo juego
            newGameEntity.setGameId(playerPlaying.getGames().getLast().getGameId());
            return GameEntityMapper.mapGameEntityToDTO(newGameEntity);
        } else {
            throw new EntityNotFoundException("The player with id '" + playerPlaying.getPlayerId() +
                    "' does not belong to your user");
        }
    }

    // Delete all games from a player
    @Transactional
    @Override
    public void deleteAllGamesOfOnePlayer(Long playerId, String jwtToken) {
        String userEmail = jwtService.extractUserEmail(jwtToken);
        // Si la la linea de ejecución llegó hasta aquí, es porque el usuario está en la BBDD
        User actualUser = userRepository.findByEmail(userEmail).orElse(null);
        assert actualUser != null;
        if(actualUser.getPlayersIds().isEmpty()){
            throw new EmptyListException("Your user has no players");
        }
        PlayerEntity playerToDeleteGames = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found","id",playerId));
        if(actualUser.getPlayersIds().stream().anyMatch(id -> id.equals(playerToDeleteGames.getPlayerId()))){
            List<GameEntity> gamesToDelete = playerToDeleteGames.getGames();
            if (!gamesToDelete.isEmpty()) {
                gameRepository.deleteByPlayer(playerToDeleteGames);
                deleteAllGamesFromPlayer(playerToDeleteGames);
                playerRepository.save(playerToDeleteGames);
            } else {
                // El jugador seleccionado no tiene ninguna partida jugada
                throw new EntityNotFoundException("No games found for your player with id = " + playerId);
            }
        } else {
            throw new EmptyListException("The player with id '" + playerToDeleteGames.getPlayerId() +
                    "' does not belong to your user");
        }
    }
    // Get all games from one player
    @Override
    public List<GameEntityDTO> getAllGamesOfOnePlayer(Long playerId, String jwtToken){
        String userEmail = jwtService.extractUserEmail(jwtToken);
        // Si la la linea de ejecución llegó hasta aquí, es porque el usuario está en la BBDD
        User actualUser = userRepository.findByEmail(userEmail).orElse(null);
        assert actualUser != null;
        if(actualUser.getPlayersIds().isEmpty()){
            throw new EmptyListException("Your user has no players");
        }
        PlayerEntity playerToShowGames = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found","id",playerId));
        if(actualUser.getPlayersIds().stream().anyMatch(id -> id.equals(playerToShowGames.getPlayerId()))){
            List<GameEntity> gamesListResponse = playerToShowGames.getGames();
            if(gamesListResponse.isEmpty()){
                throw  new EmptyListException("No games found for your player with id = " + playerId);
            }
            return gamesListResponse.stream()
                    .map(GameEntityMapper::mapGameEntityToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new EmptyListException("The player with id '" + playerToShowGames.getPlayerId() +
                    "' does not belong to your user");
        }
    }

/**              Metodos de logica interna del service ( estos no son invocados desde el controlador )                 */

    // Checkear que un usuario no tenga jugdores que hayan jugado
    @Override
    public boolean checkIfAnyRegisteredGames(User user) {
        List<Long> playerEntityList = user.getPlayersIds();
        List<GameEntity> allGamesList = gameRepository.findAll();
        return allGamesList.stream()
                .anyMatch(gameEntity ->
                        playerEntityList.contains(gameEntity.getPlayer().getPlayerId())
                );
    }
    public GameEntity rollDices(){
        int dice1 = (int) (Math.random() * 6) + 1;
        int dice2 = (int) (Math.random() * 6) + 1;
        boolean gameWon = (dice1 + dice2) == 7;
        return new GameEntity(dice1, dice2, gameWon);
    }
    public void addNewGameToPLayer(PlayerEntity playerPlaying, GameEntity gameToAdd){
        gameToAdd.setPlayer(playerPlaying);
        playerPlaying.getGames().add(gameToAdd);
    }
    public void deleteAllGamesFromPlayer(PlayerEntity playerToDeleteGames){
        playerToDeleteGames.getGames().clear();
    }

    @Override
    public double calculateAverageWin(PlayerEntity playerToCalculateSuccess){
        double wonGamesCount;
        List<GameEntity> gamesList = playerToCalculateSuccess.getGames();
        if (!gamesList.isEmpty()){
            wonGamesCount = (double) (gamesList.stream().filter(GameEntity::isGameWon).count());
            return (wonGamesCount / gamesList.size()) * 100;
        } else {
            return 0.0;
        }
    }
}

 /**
                                                     * Aclaraciones teóricas

    Al agregar @Transactional al método "deleteAllGamesOfOnePlayer()", garantizo que todas las operaciones dentro de ese
    método (eliminar juegos y guardar el jugador actualizado) se ejecuten dentro de una única transacción.

    Sin @Transactional, cada operación de base de datos podría tratarse como una transacción separada. Esto significa
    que, si por alguna razón una de las operaciones falla (por ejemplo, al intentar eliminar un juego), las operaciones
    anteriores (como borrar la lista de juegos del jugador) podrían no revertirse, dejando la base de datos en un estado
    inconsistente.

    Al utilizar @Transactional, me aseguro de que todas las operaciones se realicen como una unidad atómica: si alguna
    operación falla, todas las operaciones se revertirán, manteniendo la integridad de la base de datos.

    Resumiendo, @Transactional es una forma de garantizar la integridad y consistencia de la base de datos al realizar
    operaciones que deben tratarse como una sola unidad lógica de trabajo.

                            ----------------------------------------------------------------
    Cuando no estaba "@Transactional", por más de que borraba cada uno de los juegos
    individualmente en la base de datos y luego hacia "playerToUpdate.deleteAllGames()", la tabla
    "games" no se actualizaba y continuaba teniendo los mismos valores que antes de realizar las operaciones de borrado
  */