package com.S1T2N123.conversions;

import com.S1T2N123.model.domain.GameEntity;
import com.S1T2N123.model.dto.GameEntityDTO;

public class GameEntityMapper {
    // Convertir GameEntity a GameEntityDTO
    public static GameEntityDTO mapGameEntitytoDTO(GameEntity gameEntity){
        return new GameEntityDTO(gameEntity.getDice1(), gameEntity.getDice2(),gameEntity.isGameWon());
    }
}
