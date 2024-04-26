package com.S05T02N123.controllers;


import com.S05T02N123.message.ResponseMessage;
import com.S05T02N123.model.dto.PlayerEntityDTO;
import com.S05T02N123.model.services.interfaces.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerServiceImpl;

    // "CREATE A NEW PLAYER"
    @PostMapping("")
    @Operation(summary = "CREATE A NEW PLAYER", description =
            "Insert the playerEntity name or insert nothing for registering yourself an 'Anonymous' playerEntity ")
    public ResponseEntity<ResponseMessage<PlayerEntityDTO>> addNewPlayer(
            @RequestBody PlayerEntityDTO playerEntityDTO,
            @RequestHeader(name = "Authorization") String jwtToken) {

        jwtToken = jwtToken.substring(7);
        ResponseMessage<PlayerEntityDTO> playerResponseMessage =
                new ResponseMessage<>("Player created", playerServiceImpl.addNewPlayer(playerEntityDTO, jwtToken));
        return ResponseEntity.status(HttpStatus.OK).body(playerResponseMessage);
    }

    // update player´s name
    @PutMapping("")
    @Operation(summary = "UPDATE PLAYER", description = "Insert the player´s old name and the player´s new name")
    public ResponseEntity<ResponseMessage<PlayerEntityDTO>> updatePlayer(
            @RequestBody String[] updatedPlayerArr,
            @RequestHeader (name = "Authorization") String jwtToken) {

        jwtToken = jwtToken.substring(7);
        ResponseMessage<PlayerEntityDTO> playerResponseMessage =
                new ResponseMessage<>("PlayerEntity´s name updated succesfully",
                        playerServiceImpl.updatePlayerName(updatedPlayerArr, jwtToken));

        return ResponseEntity.status(HttpStatus.OK).body(playerResponseMessage);
    }

    // get all players and their individual Average Success Rate
    @GetMapping("/")
    @Operation(summary = "GET THE PLAYER´S LIST AND EACH AVERAGE SUCCESS RATE")
    public ResponseEntity<?> getAllPlayers(@RequestHeader (name = "Authorization") String jwtToken) {

        jwtToken = jwtToken.substring(7);
        List<PlayerEntityDTO> playersEntityDTOList = playerServiceImpl.getAllPlayers(jwtToken);
        if(playersEntityDTOList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("There are no registered players");
        } else{
            ResponseMessage<List<PlayerEntityDTO>> playersResponseMessage =
                    new ResponseMessage<>("Player´s List:", playersEntityDTOList);
            return ResponseEntity.status(HttpStatus.OK).body(playersResponseMessage);
        }
    }

    // Get the average success rate of all players
    @GetMapping("/ranking")
    @Operation(summary = "GET THE AVERAGE SUCCESS RATE OF ALL PLAYERS")
    public ResponseEntity<ResponseMessage<Double>> getRanking(@RequestHeader (name = "Authorization") String jwtToken){

        jwtToken = jwtToken.substring(7);
        ResponseMessage<Double> rankingResponseMessage = new ResponseMessage<>("Average success rate of all players:",
                playerServiceImpl.getAverageSuccessRate(jwtToken));

        return ResponseEntity.status(HttpStatus.OK).body(rankingResponseMessage);
    }

    // Get the player/s with the worst success rate, therefore, the loser player/s
    @GetMapping("/ranking/loser")
    @Operation(summary = "GET THE PLAYER/S WITH THE WORST SUCCESS RATE", description = "THE LOSER/S PLAYER/S")
    public ResponseEntity<ResponseMessage<List<PlayerEntityDTO>>> getLoserPlayer(
            @RequestHeader (name = "Authorization") String jwtToken){

        jwtToken = jwtToken.substring(7);
        List<PlayerEntityDTO> playerEntityDTOList = playerServiceImpl.getLoserPlayer(jwtToken);
            ResponseMessage<List<PlayerEntityDTO>> responseMessageLoser = new ResponseMessage<>("Loser player/s:",
                    playerEntityDTOList);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessageLoser);
    }

    // get the player with the best success rate
    @GetMapping("/ranking/winner")
    @Operation(summary = "GET THE PLAYER/S WITH THE BEST SUCCESS RATE", description = "THE WINNER/S PLAYER/S")
    public ResponseEntity<ResponseMessage<List<PlayerEntityDTO>>> getWinnerPlayer(
            @RequestHeader (name = "Authorization") String jwtToken){

        jwtToken = jwtToken.substring(7);
        List<PlayerEntityDTO> playerEntityDTOList = playerServiceImpl.getWinnerPlayer(jwtToken);
        ResponseMessage<List<PlayerEntityDTO>> responseMessageWinners = new ResponseMessage<>("This is the " +
                "winner/s list:", playerEntityDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessageWinners);
    }
}
