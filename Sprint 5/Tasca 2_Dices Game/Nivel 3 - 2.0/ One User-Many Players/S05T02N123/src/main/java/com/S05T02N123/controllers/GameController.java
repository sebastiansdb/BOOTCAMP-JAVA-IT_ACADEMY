package com.S05T02N123.controllers;

import com.S05T02N123.message.ResponseMessage;
import com.S05T02N123.model.dto.GameEntityDTO;
import com.S05T02N123.model.services.interfaces.GameService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class GameController {

    private final GameService gameServiceImpl;

    // make a play
    @PostMapping("/{id}/games/")
    @Operation(summary = "START A NEW GAME", description = "Make a dices roll")
    public ResponseEntity<ResponseMessage<GameEntityDTO>> rollOfDices(
            @PathVariable(value = "id") Long idPlayer,
            @RequestHeader (name = "Authorization")  String jwtToken){

        jwtToken = jwtToken.substring(7);
        GameEntityDTO newGameEntity = gameServiceImpl.newGame(idPlayer, jwtToken);
        ResponseMessage<GameEntityDTO> gameEntityResponseMessage = new ResponseMessage<> (
                "Game´s result:", newGameEntity);
        return ResponseEntity.status(HttpStatus.OK).body(gameEntityResponseMessage);
    }

    // delete all games from a player
    @DeleteMapping("/{id}/games")
    @Operation(summary = "DELETE ALL THE GAMES OF ONE PLAYER ")
    public ResponseEntity<String> deleteAllGamesOfOnePlayer (
            @PathVariable(value = "id") Long idPlayer,
            @RequestHeader (name = "Authorization")  String jwtToken) {

        jwtToken = jwtToken.substring(7);
        gameServiceImpl.deleteAllGamesOfOnePlayer(idPlayer, jwtToken);
        return ResponseEntity.status(HttpStatus.OK).body("All games were deleted from your player with id = " + idPlayer);
    }

    // Get all games from one player
    @GetMapping("/{id}/games")
    @Operation(summary = "GET ALL GAMES FROM A PLAYER", description = "Enter de player´s id")
    public ResponseEntity<ResponseMessage<List<GameEntityDTO>>> getAllGamesFromAPlayer(
            @PathVariable(value = "id") Long playerId,
            @RequestHeader (name = "Authorization")  String jwtToken){

        jwtToken = jwtToken.substring(7);
        List<GameEntityDTO> playerGames = gameServiceImpl.getAllGamesOfOnePlayer(playerId, jwtToken);
        ResponseMessage<List<GameEntityDTO>> responseMessageGameList;
        responseMessageGameList = new ResponseMessage<>(
                "Player with ´id = " + playerId + "' game List" ,playerGames);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessageGameList);
    }

}
