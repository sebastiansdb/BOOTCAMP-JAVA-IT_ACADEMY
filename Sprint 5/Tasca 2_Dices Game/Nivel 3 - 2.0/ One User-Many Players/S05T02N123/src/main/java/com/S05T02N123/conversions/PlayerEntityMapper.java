package com.S05T02N123.conversions;

import com.S05T02N123.model.domain.PlayerEntity;
import com.S05T02N123.model.dto.PlayerEntityDTO;
import com.S05T02N123.model.services.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerEntityMapper {
    private static GameService gameService;
    @Autowired
    public PlayerEntityMapper(GameService gameService) {
        this.gameService = gameService;
    }
    // Convertir PLayerEntityDTO a new PlayerEntity
    public static PlayerEntity mapPlayerEntityDTOToNewPlayerEntity(PlayerEntityDTO playerEntityDTO){
        playerEntityDTO.setPlayerActualRegisterDate();                  // seteo la fecha actual (la fecha de creación)
        return new PlayerEntity(playerEntityDTO.getPlayerName(), playerEntityDTO.getRegisterDate());
    }
    // Convertir PlayerEntity existente a PLayerEntityDTO
    public static PlayerEntityDTO mapExistingPlayerEntityToDTO(PlayerEntity playerEntity){
        return new PlayerEntityDTO(playerEntity.getPlayerName(), playerEntity.getRegisterDate(),
                gameService.calculateAverageWin(playerEntity));
    }

    // Convertir new PlayerEntity a PLayerEntityDTO
    public static PlayerEntityDTO mapNewPlayerEntityToDTO(PlayerEntity playerEntity) {
        return new PlayerEntityDTO(playerEntity.getPlayerName(), playerEntity.getRegisterDate(),0.0);
    }
}
