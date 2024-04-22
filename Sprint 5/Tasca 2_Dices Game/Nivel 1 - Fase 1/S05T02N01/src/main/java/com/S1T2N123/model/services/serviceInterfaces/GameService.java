package com.S1T2N123.model.services.serviceInterfaces;

import com.S1T2N123.model.dto.GameEntityDTO;

public interface GameService {

    // new Game - roll dices
    GameEntityDTO newGame(Long idPlayer);
    // delete all games from a player
    void deleteAllGamesOfOnePlayer(Long playerId);
}
