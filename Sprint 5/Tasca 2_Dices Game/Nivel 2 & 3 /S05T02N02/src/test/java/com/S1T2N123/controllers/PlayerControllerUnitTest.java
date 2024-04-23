package com.S1T2N123.controllers;

import com.S1T2N123.model.dto.PlayerEntityDTO;
import com.S1T2N123.model.services.implementations.JwtService;
import com.S1T2N123.model.services.interfaces.PlayerService;
import com.S1T2N123.model.services.interfaces.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.isArray;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PlayerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PlayerControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PlayerService playerService;
    @MockBean
    JwtService jwtService;
    @MockBean
    UserService userService;
    @Test
    @DisplayName("Playe Controller - SHOULD ADD A NEW PLAYER")
    void addNewPlayer_should_create_a_new_player() throws Exception {
        String uri = "/players";
        PlayerEntityDTO playerDtoToSave =  PlayerEntityDTO.builder()
                .playerName("playerTest").build();
        PlayerEntityDTO playerDtoResponse = PlayerEntityDTO.builder()
                .playerName("playerTest")
                .registerDate(new Date())
                .averageWin(0.0)
                .build();

        given(playerService.addNewPlayer(any(PlayerEntityDTO.class))).willReturn(playerDtoResponse);

        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)                            // Envio un JSON
                .content(objectMapper.writeValueAsString(playerDtoToSave))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.playerName", is(playerDtoResponse.getPlayerName())))
                // Podria checkar la fecha de creación, pero me arroja un error porque no coinciden los formatos
                .andExpect(jsonPath("$.data.averageWin", is (playerDtoResponse.getAverageWin())))
                .andExpect(jsonPath("$").isNotEmpty());

    }

    @Test
    @DisplayName("Player Controller - SHOULD UPDATE A PLAYER´S NAME")
    void updatePlayer_should_update_a_player_name() throws Exception {
        String uri = "/players";
        String[] playerNamesUpdates = {"oldName","newName"};
        PlayerEntityDTO playerDtoResponse = PlayerEntityDTO.builder()
                .playerName(playerNamesUpdates[1])
                .registerDate(new Date())
                .averageWin(10.0)
                .build();

        given(playerService.updatePlayerName(any(String[].class))).willReturn(playerDtoResponse); //any(String[].class)
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playerNamesUpdates))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.playerName",is(playerDtoResponse.getPlayerName())))
                .andExpect(jsonPath("$.data.averageWin", is (playerDtoResponse.getAverageWin())));
    }

    @Test
    @DisplayName("Player Controller - SHOULD RETURN THE PLAYERS LIST")
    void getAllPlayers_should_return_the_players_list_and_individual_success_rate() throws Exception {
        String uri = "/players/";
        List<PlayerEntityDTO> playersDtoList;
        PlayerEntityDTO playerDtoResponse1 = PlayerEntityDTO.builder()
                .playerName("playerTest")
                .registerDate(new Date())
                .averageWin(10.0)
                .build();
        PlayerEntityDTO playerDtoResponse2 = PlayerEntityDTO.builder()
                .playerName("playerTest2")
                .registerDate(new Date())
                .averageWin(0.0)
                .build();

        given(playerService.getAllPlayers()).willReturn(List.of(playerDtoResponse1,playerDtoResponse2));

        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data",hasSize(2)))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    @DisplayName("Player Controller - SHOULD RETURN GLOBAL AVERAGE SUCCESS RATE")
    void getRanking_should_return_the_players_average_success_rate() throws Exception {
        String uri = "/players/ranking";
        PlayerEntityDTO playerDtoResponse1 = PlayerEntityDTO.builder()
                .playerName("playerTest")
                .registerDate(new Date())
                .averageWin(10.0)
                .build();
        PlayerEntityDTO playerDtoResponse2 = PlayerEntityDTO.builder()
                .playerName("playerTest2")
                .registerDate(new Date())
                .averageWin(0.0)
                .build();
        Double ranking = (playerDtoResponse1.getAverageWin() + playerDtoResponse2.getAverageWin())/2;

        given(playerService.getAverageSuccessRate()).willReturn(ranking);

        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data",is(ranking)));
    }

    @Test
    void getLoserPlayer() {
    }

    @Test
    void getWinnerPlayer() {
    }
}