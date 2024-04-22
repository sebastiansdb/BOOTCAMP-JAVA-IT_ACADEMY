package com.S1T2N123.conversions;

import com.S1T2N123.model.domain.GameEntity;
import com.S1T2N123.model.domain.PlayerEntity;
import com.S1T2N123.model.dto.PlayerEntityDTO;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PlayerEntityMapperTest {

    @Test
    void playerEntityDTOToNewPlayerEntity() {
        // given
        PlayerEntityDTO playerEntityDTO = new PlayerEntityDTO("PlayerEntityDTO Test");

        // when
        PlayerEntity response = PlayerEntityMapper.mapPlayerEntityDTOToNewPlayerEntity(playerEntityDTO);

        // then
        assertNotNull(response.getPlayerName());
        // DUDA: ¿Cómo hago para que se autogenere el id?? ¿¿ MOckito ?
//        assertNotNull(response.getPlayerId());
        assertEquals(playerEntityDTO.getPlayerName(), response.getPlayerName());
        assertEquals(playerEntityDTO.getRegisterDate(), response.getRegisterDate());
    }

    @Test
    void existingPlayerEntityToDTO() {
        // given
        PlayerEntity existingPlayerEntity = new PlayerEntity("PlayerEntityDTO Test",new Date(2024-3-3));
        // agrego una partida jugada
        existingPlayerEntity.addNewGame(new GameEntity(3,4,true));

        // when
        PlayerEntityDTO response = PlayerEntityMapper.mapExistingPlayerEntityToDTO(existingPlayerEntity);

        // then
        assertNotNull(response.getPlayerName());
        assertNotNull(response.getRegisterDate());
        assertEquals(existingPlayerEntity.getPlayerName(), response.getPlayerName());
        assertEquals(existingPlayerEntity.getRegisterDate(), response.getRegisterDate());
        assertEquals(existingPlayerEntity.calculateAverageWin(), response.getAverageWin());
    }

    @Test
    void newPlayerEntityToDTO() {
        // given
        PlayerEntity existingPlayerEntity = new PlayerEntity("PlayerEntityDTO Test",new Date(2024-3-3));

        // when
        PlayerEntityDTO response = PlayerEntityMapper.mapNewPlayerEntityToDTO(existingPlayerEntity);

        // then
        assertNotNull(response.getPlayerName());
        assertNotNull(response.getRegisterDate());
        assertEquals(response.getPlayerName(),existingPlayerEntity.getPlayerName());
        assertEquals(response.getRegisterDate(), existingPlayerEntity.getRegisterDate());
        assertEquals(response.getAverageWin(), 0);

    }
}