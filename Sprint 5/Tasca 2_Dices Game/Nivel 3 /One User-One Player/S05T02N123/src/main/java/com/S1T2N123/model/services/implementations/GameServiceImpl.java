package com.S1T2N123.model.services.implementations;

import com.S1T2N123.conversions.GameEntityMapper;
import com.S1T2N123.exceptions.EmptyListException;
import com.S1T2N123.exceptions.ResourceNotFoundException;
import com.S1T2N123.model.domain.GameEntity;
import com.S1T2N123.model.domain.PlayerEntity;
import com.S1T2N123.model.dto.GameEntityDTO;
import com.S1T2N123.model.repositories.GameRepository;
import com.S1T2N123.model.repositories.PlayerRepository;
import com.S1T2N123.model.services.interfaces.GameService;
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


    // CHECK!!!

    // playerToUpdate CANNOT FIND


    // new Game
    @Override
    public GameEntityDTO newGame (Long playerId) throws ResourceNotFoundException {
        PlayerEntity playerPlaying = playerRepository.findById(playerId)               // busco jugador que está jugando
                .orElseThrow(() -> new ResourceNotFoundException("Player not found","id",playerId)); // check que exista
        GameEntity newGameEntity = new GameEntity();             // creo un juego nuevo
        newGameEntity.rollDices();                               // lanzo dados
        playerPlaying.addNewGame(newGameEntity);                 // agrego el juego nuevo al jugador
        playerRepository.save(playerPlaying);                    // actualizo jugador en BBDD con su nueva jugada
        // La línea de abajo ("setGameId(---)") debe ir después de guardar el jugador con la nueva jugada en BBDD,
        // porque es en ese momento que se autogenera el gameId en el juego nuevo creado. Antes de la persistencia
        // del jugador con la nueva jugada, dicho id es null
        newGameEntity.setGameId(playerPlaying.getGames().getLast().getGameId());    // Guardo, en la entidad que devuelvo al controlador, el id del nuevo juego
        return GameEntityMapper.mapGameEntitytoDTO(newGameEntity);
    }

    // delete all games from a player
    @Transactional
    @Override
    public void deleteAllGamesOfOnePlayer(Long playerId) throws ResourceNotFoundException {
        PlayerEntity playerToDeleteGames = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found","id",playerId));
        List<GameEntity> gamesToDelete = playerToDeleteGames.getGames();
        if (!gamesToDelete.isEmpty()) {
           gameRepository.deleteByPlayer(playerToDeleteGames);
           playerToDeleteGames.deleteAllGames();
           playerRepository.save(playerToDeleteGames);
        } else {
            // El jugador seleccionado no tiene ninguna partida jugada
            throw new EmptyListException("The player with id = " + playerId + " has not been played yet");
        }
    }
    // Get all games from one player
    @Override
    public List<GameEntityDTO> getAllGamesOfOnePlayer(Long playerId){
        List<GameEntity> gamesListResponse = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player","id",playerId))
                .getGames();
        if(gamesListResponse.isEmpty()){
            throw  new EmptyListException("No games found for player with id = " + playerId);
        }
        return gamesListResponse.stream()
               .map(GameEntityMapper::mapGameEntitytoDTO)
               .collect(Collectors.toList());
    }

//              Metodos de logica interna del service ( estos no son invocados desde el controlador )                 //

    // checkear que no haya jugadas en sistema
    @Override
    public boolean checkIfRegisteredGames(){
        return !gameRepository.findAll().isEmpty();
    }

    /*

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
    En definitiva, agregué "@Transactional" porque cuando no estaba, por más de que borraba cada uno de los juegos
    individualmente en la base de datos (con el "for") y luego hacia "playerToUpdate.getGames().clear();", la tabla
    "games" no se actualizaba y continuaba teniendo los mismos valores que antes de realizar las operaciones de borrado
     */
}
