package com.S1T2N123.model.services.interfaces;

import com.S1T2N123.model.domain.PlayerEntity;
import com.S1T2N123.model.dto.GameEntityDTO;
import com.S1T2N123.model.dto.PlayerEntityDTO;

import java.util.List;

public interface PlayerService {
    // add new player
//    PlayerEntityDTO addNewPlayer(PlayerEntityDTO playerEntityToAdd);
    // update player´s name
    PlayerEntity updatePlayerName(String[] updatedPlayerArr);
    // get all players
    List<PlayerEntityDTO> getAllPlayers();
    // Get all games from one player
    List<GameEntityDTO> getAllGamesOfOnePlayer(Long playerId);
    // Get the average success rate of all players
    Double getAverageSuccessRate();
    // Get the player/s with the worst success rate, therefore, the loser player
    List<PlayerEntityDTO> getLoserPlayer();
    // get the player with the best success rate
    public List<PlayerEntityDTO> getWinnerPlayer();

    PlayerEntityDTO addNewPlayer(PlayerEntityDTO playerEntityDTO, String jwtToken);
}
