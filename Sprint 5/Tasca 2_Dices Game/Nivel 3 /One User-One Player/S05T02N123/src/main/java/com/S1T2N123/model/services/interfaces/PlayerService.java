package com.S1T2N123.model.services.interfaces;

import com.S1T2N123.model.dto.PlayerEntityDTO;
import java.util.List;

public interface PlayerService {
    // add new player
    PlayerEntityDTO addNewPlayer(PlayerEntityDTO playerEntityToAdd);
    // update player´s name
    PlayerEntityDTO updatePlayerName(String[] updatedPlayerArr);
    // get all players
    List<PlayerEntityDTO> getAllPlayers();
    // Get the average success rate of all players
    Double getAverageSuccessRate();
    // Get the the loser player ( player/s with the worst success rate )
    List<PlayerEntityDTO> getLoserPlayer();
    // get the player with the best success rate
    List<PlayerEntityDTO> getWinnerPlayer();

}
