package com.S1T2N123.conversions;

import com.S1T2N123.model.domain.PlayerEntity;
import com.S1T2N123.model.dto.PlayerEntityDTO;

public class PlayerEntityMapper {

    // Convertir PLayerEntityDTO a new PlayerEntity
    public static PlayerEntity mapPlayerEntityDTOToNewPlayerEntity(PlayerEntityDTO playerEntityDTO){
        playerEntityDTO.setPlayerActualRegisterDate();                  // seteo la fecha actual (la fecha de creación)
        return new PlayerEntity(playerEntityDTO.getPlayerName(), playerEntityDTO.getRegisterDate());
    }
    // Convertir PlayerEntity existente a PLayerEntityDTO
    public static PlayerEntityDTO mapExistingPlayerEntityToDTO(PlayerEntity playerEntity){
        return new PlayerEntityDTO(playerEntity.getPlayerName(), playerEntity.getRegisterDate(),
                playerEntity.calculateAverageWin());
    }

    // Convertir new PlayerEntity a PLayerEntityDTO
    public static PlayerEntityDTO mapNewPlayerEntityToDTO(PlayerEntity playerEntity) {
        return new PlayerEntityDTO(playerEntity.getPlayerName(), playerEntity.getRegisterDate(),0.0);
    }

    // Convertir GameEntity a GameEntityDTO
//    public static GameEntityDTO GameEntitytoDTO(GameEntity gameEntity){
//        return new GameEntityDTO(gameEntity.getDice1(), gameEntity.getDice2(),gameEntity.isGameWon());
//    }
}
