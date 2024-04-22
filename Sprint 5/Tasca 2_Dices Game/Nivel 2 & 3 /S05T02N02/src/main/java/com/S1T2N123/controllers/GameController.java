package com.S1T2N123.controllers;

import com.S1T2N123.message.ResponseMessage;
import com.S1T2N123.model.dto.GameEntityDTO;
import com.S1T2N123.model.services.interfaces.GameService;
import com.S1T2N123.model.services.interfaces.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class GameController {

    private final GameService gameServiceImpl;  // Ver si es objeto GameService o GameServiceImpl

    // make a play
    @PostMapping("/{id}/games/")
    @Operation(summary = "START A NEW GAME", description = "Make a dices roll")
    public ResponseEntity<ResponseMessage<GameEntityDTO>> rollOfDices(@PathVariable(value = "id") Long idPlayer){
        GameEntityDTO newGameEntity = gameServiceImpl.newGame(idPlayer);
        ResponseMessage<GameEntityDTO> gameEntityResponseMessage = new ResponseMessage<> (
                "Game´s result:", newGameEntity);
        return ResponseEntity.status(HttpStatus.OK).body(gameEntityResponseMessage);
    }

    // delete all games from a player
    @DeleteMapping("/{id}/games")
    @Operation(summary = "DELETE ALL THE GAMES OF ONE PLAYER ")
    public ResponseEntity<String> deleteAllGamesOfOnePlayer (@PathVariable(value = "id") Long idPlayer) {
        gameServiceImpl.deleteAllGamesOfOnePlayer(idPlayer);
        return ResponseEntity.status(HttpStatus.OK).body("All games were deleted from player with id = " + idPlayer);
    }

    // Get all games from one player
    @GetMapping("/{id}/games")
    @Operation(summary = "GET ALL GAMES FROM A PLAYER", description = "Enter de player´s id")
    public ResponseEntity<ResponseMessage<List<GameEntityDTO>>> getAllGamesFromAPlayer
    (@PathVariable(value = "id") Long playerId){
        List<GameEntityDTO> playerGames = gameServiceImpl.getAllGamesOfOnePlayer(playerId);
        ResponseMessage<List<GameEntityDTO>> responseMessageGameList;
        if (!playerGames.isEmpty()) {
            responseMessageGameList = new ResponseMessage<>(
                    "Player with ´id = " + playerId + "' game List" ,playerGames);
        } else {
            responseMessageGameList = new ResponseMessage<>("No games found for player with id = " + playerId,null );
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseMessageGameList);
    }

}
