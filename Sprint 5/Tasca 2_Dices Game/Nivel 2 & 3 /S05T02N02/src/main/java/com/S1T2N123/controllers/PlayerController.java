package com.S1T2N123.controllers;

import com.S1T2N123.message.ResponseMessage;
import com.S1T2N123.model.domain.PlayerEntity;
import com.S1T2N123.model.dto.GameEntityDTO;
import com.S1T2N123.model.dto.PlayerEntityDTO;
import com.S1T2N123.model.services.interfaces.PlayerService;
import com.S1T2N123.security.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//                                                   TO DO
//
// Resolver : POST: /players: crea un jugador/a.
//            PUT /players: modifica el nom del jugador/a.
//
// PlayerService playerServiceImpl -- PlayerServiceImpl o PlayerService ?? Que diferencia habria?

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerServiceImpl;

    // "CREATE A NEW PLAYER" -- add new playerEntity
    @PostMapping("")
    @Operation(summary = "CREATE A NEW PLAYER", description =
            "Insert the playerEntity name or insert nothing for registering yourself an 'Anonymous' playerEntity ")
    public ResponseEntity<ResponseMessage<PlayerEntityDTO>> addNewPlayer(@RequestBody PlayerEntityDTO playerEntityDTO) {
        ResponseMessage<PlayerEntityDTO> playerResponseMessage =
                new ResponseMessage<>("Player created", playerServiceImpl.addNewPlayer(playerEntityDTO));
        return ResponseEntity.status(HttpStatus.OK).body(playerResponseMessage);
    }

    // update player´s name

    // FALTA VER el tema de los ANONIMOS
    @PutMapping("")
    @Operation(summary = "UPDATE PLAYER", description = "Insert the player´s old name and the player´s new name")
    public ResponseEntity<ResponseMessage<PlayerEntityDTO>> updatePlayer(@RequestBody String[] updatedPlayerArr){
        ResponseMessage<PlayerEntityDTO> playerResponseMessage =
                new ResponseMessage<>("PlayerEntity´s name updated succesfully",
                        playerServiceImpl.updatePlayerName(updatedPlayerArr));
        return ResponseEntity.status(HttpStatus.OK).body(playerResponseMessage);
    }

    // get all players and their individual Average Success Rate
    @GetMapping("/")
    @Operation(summary = "GET THE PLAYER´S LIST AND EACH AVERAGE SUCCESS RATE")
    public ResponseEntity<ResponseMessage<List<PlayerEntityDTO>>> getAllPlayers() {
        ResponseMessage<List<PlayerEntityDTO>> playersResponseMessage =
                new ResponseMessage<>("Player´s List:", playerServiceImpl.getAllPlayers());
        return ResponseEntity.status(HttpStatus.OK).body(playersResponseMessage);
    }

    // Get the average success rate of all players
    @GetMapping("/ranking")
    @Operation(summary = "GET THE AVERAGE SUCCESS RATE OF ALL PLAYERS")
    public ResponseEntity<ResponseMessage<Double>> getRanking(){
        ResponseMessage<Double> rankingResponseMessage = new ResponseMessage<>("Average success rate of all players:",
                playerServiceImpl.getAverageSuccessRate());
        return ResponseEntity.status(HttpStatus.OK).body(rankingResponseMessage);
    }

    // Get the player/s with the worst success rate, therefore, the loser player/s
    @GetMapping("/ranking/loser")
    @Operation(summary = "GET THE PLAYER/S WITH THE WORST SUCCESS RATE", description = "THE LOSER/S PLAYER/S")
    public ResponseEntity<ResponseMessage<List<PlayerEntityDTO>>> getLoserPlayer(){
        List<PlayerEntityDTO> playerEntityDTOList = playerServiceImpl.getLoserPlayer();
        if (playerEntityDTOList == null){
            ResponseMessage<List<PlayerEntityDTO>> responseMessageOnePLayer = new ResponseMessage<>("There are " +
                    "registered single-player plays and he has won. Therefore, there are no losers", playerEntityDTOList);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessageOnePLayer);
        }else {
            ResponseMessage<List<PlayerEntityDTO>> responseMessageLoser = new ResponseMessage<>("Loser player/s:",
                    playerEntityDTOList);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessageLoser);
        }
    }

    // get the player with the best success rate
    @GetMapping("/ranking/winner")
    @Operation(summary = "GET THE PLAYER/S WITH THE BEST SUCCESS RATE", description = "THE WINNER/S PLAYER/S")
    public ResponseEntity<ResponseMessage<List<PlayerEntityDTO>>> getWinnerPlayer(){
        List<PlayerEntityDTO> playerEntityDTOList = playerServiceImpl.getWinnerPlayer();
        if (playerEntityDTOList == null){
            ResponseMessage<List<PlayerEntityDTO>> responseMessageOnePLayer = new ResponseMessage<>("There is " +
                    "just one registered single-player plays and he has lost. Therefore, there are no winners", playerEntityDTOList);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessageOnePLayer);
        } else {
            ResponseMessage<List<PlayerEntityDTO>> responseMessageWinners = new ResponseMessage<>("This is the " +
                    "winner/s list:", playerEntityDTOList);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessageWinners);
        }
    }
}
