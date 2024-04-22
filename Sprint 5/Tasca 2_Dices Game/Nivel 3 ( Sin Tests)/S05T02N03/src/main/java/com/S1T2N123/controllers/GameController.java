package com.S1T2N123.controllers;

import com.S1T2N123.message.ResponseMessage;
import com.S1T2N123.model.dto.GameEntityDTO;
import com.S1T2N123.model.services.interfaces.GameService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class GameController {

    @Autowired
    private GameService gameServiceImpl;  // Ver si es objeto GameService o GameServiceImpl

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

}
