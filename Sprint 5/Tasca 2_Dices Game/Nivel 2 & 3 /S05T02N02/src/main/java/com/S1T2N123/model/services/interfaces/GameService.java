package com.S1T2N123.model.services.interfaces;

import com.S1T2N123.model.dto.GameEntityDTO;

import java.util.List;

public interface GameService {

    // new Game - roll dices
    GameEntityDTO newGame(Long idPlayer);
    // delete all games from a player
    void deleteAllGamesOfOnePlayer(Long playerId);
    // Get all games from one player
    List<GameEntityDTO> getAllGamesOfOnePlayer(Long playerId);
}
