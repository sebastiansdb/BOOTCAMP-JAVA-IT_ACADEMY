package com.S1T2N1and2.model.services.serviceInterfaces;

import com.S1T2N1and2.model.dto.GameEntityDTO;

public interface GameService {

    // new Game - roll dices
    GameEntityDTO newGame(Long idPlayer);
    // delete all games from a player
    void deleteAllGamesOfOnePlayer(Long playerId);
}
