package com.S05T02N123.model.services.interfaces;

import com.S05T02N123.model.dto.PlayerEntityDTO;
import java.util.List;

public interface PlayerService {
    // add new player
    PlayerEntityDTO addNewPlayer(PlayerEntityDTO playerEntityDTO, String jwtToken);
    // update player´s name
    PlayerEntityDTO updatePlayerName(String[] updatedPlayerArr, String jwtToken);
    // get all players
    List<PlayerEntityDTO> getAllPlayers(String jwtToken);
    // Get the average success rate of all players
    Double getAverageSuccessRate(String jwtToken);
    // Get the the loser player ( player/s with the worst success rate )
    List<PlayerEntityDTO> getLoserPlayer(String jwtToken);
    // get the player with the best success rate
    List<PlayerEntityDTO> getWinnerPlayer(String jwtToken);
}
