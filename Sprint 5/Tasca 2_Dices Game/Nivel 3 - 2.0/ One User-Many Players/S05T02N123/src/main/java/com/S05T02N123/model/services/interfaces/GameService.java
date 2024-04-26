package com.S05T02N123.model.services.interfaces;

import com.S05T02N123.model.domain.PlayerEntity;
import com.S05T02N123.model.dto.GameEntityDTO;
import com.S05T02N123.security.User;

import java.util.List;

public interface GameService {
    // new Game - roll dices
    GameEntityDTO newGame(Long idPlayer, String jwtToken);
    // delete all games from a player
    void deleteAllGamesOfOnePlayer(Long playerId, String jwtToken);
    // Get all games from one player
    List<GameEntityDTO> getAllGamesOfOnePlayer(Long playerId, String jwtToken);
    boolean checkIfAnyRegisteredGames(User user);
    double calculateAverageWin(PlayerEntity playerToCalculateSuccess);
}
