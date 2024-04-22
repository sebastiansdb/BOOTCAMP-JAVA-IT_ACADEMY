package com.S1T2N123.model.services.implementations;

import com.S1T2N123.conversions.GameEntityMapper;
import com.S1T2N123.exceptions.ResourceNotFoundException;
import com.S1T2N123.model.domain.GameEntity;
import com.S1T2N123.model.domain.PlayerEntity;
import com.S1T2N123.model.dto.GameEntityDTO;
import com.S1T2N123.model.repositories.GameRepository;
import com.S1T2N123.model.repositories.PlayerRepository;
import com.S1T2N123.model.services.interfaces.GameService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameServiceimpl implements GameService {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    PlayerServiceImpl playerServiceImpl;

    // new Game
    public GameEntityDTO newGame (Long idPlayer){
        GameEntity newGameEntity = new GameEntity();                                // creo un juego nuevo
        newGameEntity.rollDices();                                                  // lanzo dados
        PlayerEntity playerPlaying = playerServiceImpl.getPlayerById(idPlayer);     // busco jugador que está jugando
        playerPlaying.addNewGame(newGameEntity);                                    // agrego el juego nuevo
        playerRepository.save(playerPlaying);                                       // actualizo jugador en BBDD con su nueva jugada
        // La línea de abajo ("setGameId(---)") debe ir después de guardar el jugador con la nueva jugada en BBDD,
        // porque es en ese momento que se autogenera el gameId en el juego nuevo creado. Antes de la persistencia
        // del jugador con la nueva jugada, dicho id es null
        newGameEntity.setGameId(playerPlaying.getGames().getLast().getGameId());    // Guardo, en la entidad que devuelvo al controlador, el id del nuevo juego
        return GameEntityMapper.mapGameEntitytoDTO(newGameEntity);
    }

    // delete all games from a player

    /*

    Al agregar @Transactional al método deleteAllGamesOfOnePlayer, garantizo que todas las operaciones dentro de ese
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
    @Transactional
    public void deleteAllGamesOfOnePlayer(Long playerId){
        PlayerEntity playerToUpdate = playerServiceImpl.getPlayerById(playerId);
        List<GameEntity> gamesToDelete = playerToUpdate.getGames();
        if (!gamesToDelete.isEmpty()){
            // DUDA: POR QUE si no hago esto, no se borran los games de la tabla "games"??
            for (GameEntity gameToDelete : gamesToDelete){
                gameRepository.delete(gameToDelete);
            }
           playerToUpdate.deleteAllGames();
           playerRepository.save(playerToUpdate);
        } else{
            // El jugador seleccionado no tiene ninguna partida jugada
            throw new ResourceNotFoundException("Game", "id",playerId);
        }
    }


    // VER SI BORRO ESTO: (Coloque estos métodos en otro sitio)


    // metodos de logica interna del service ( estos no son invocados desde el controlador )
//    public GameEntity rollDices (){
//        // atributos del nuevo juego
//        int dice1 = (int) (Math.random() * 6) + 1;
//        int dice2 = (int) (Math.random() * 6) + 1;
//        boolean gameWon = (dice1 + dice2) == 7;
//        return new GameEntity(dice1, dice2, gameWon);
//    }

//    public PlayerEntity getPlayerById(Long playerId){
//        return playerRepository.findById(playerId)
//                .orElseThrow(()->new ResourceNotFoundException("Player not found","id",playerId));
//    }
}
