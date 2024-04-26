package com.S1T2N123.controllers;

import com.S1T2N123.model.dto.PlayerEntityDTO;
import com.S1T2N123.model.services.interfaces.JwtService;
import com.S1T2N123.model.services.interfaces.PlayerService;
import com.S1T2N123.model.services.interfaces.UserService;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    private PlayerEntityDTO playerDtoResponse0;
    private PlayerEntityDTO playerDtoResponse5;
    private PlayerEntityDTO playerDtoResponse10;

    @BeforeEach
    void setUp(){
        playerDtoResponse0 = PlayerEntityDTO.builder()
                .playerName("playerTest0")
                .registerDate(new Date())
                .averageWin(0.0)
                .build();
        playerDtoResponse10 = PlayerEntityDTO.builder()
                .playerName("playerTest10")
                .registerDate(new Date())
                .averageWin(10.0)
                .build();
        playerDtoResponse5 = PlayerEntityDTO.builder()
                .playerName("playerTest5")
                .registerDate(new Date())
                .averageWin(5.0)
                .build();
    }
    @Test
    @DisplayName("Playe Controller - SHOULD ADD A NEW PLAYER")
    void addNewPlayer_should_create_a_new_player() throws Exception {
        String uri = "/players";
        String message = "Player created";
        PlayerEntityDTO playerDtoToSave =  PlayerEntityDTO.builder()
                .playerName("playerTest").build();

        given(playerService.addNewPlayer(any(PlayerEntityDTO.class))).willReturn(playerDtoResponse0);

        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)                            // Envio un JSON
                .content(objectMapper.writeValueAsString(playerDtoToSave))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.playerName", is(playerDtoResponse0.getPlayerName())))
                // Podria checkar la fecha de creación, pero me arroja un error porque no coinciden los formatos
                .andExpect(jsonPath("$.data.averageWin", is (playerDtoResponse0.getAverageWin())))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.message", is(message)));

    }

    @Test
    @DisplayName("Player Controller - SHOULD UPDATE A PLAYER´S NAME")
    void updatePlayer_should_update_a_player_name() throws Exception {
        String uri = "/players";
        String message = "PlayerEntity´s name updated succesfully";
        String[] playerNamesUpdates = {"oldName","newName"};

        playerDtoResponse10.setPlayerName(playerNamesUpdates[1]);

        given(playerService.updatePlayerName(any(String[].class))).willReturn(playerDtoResponse10);
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(playerNamesUpdates))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.playerName",is(playerDtoResponse10.getPlayerName())))
                .andExpect(jsonPath("$.data.averageWin", is (playerDtoResponse10.getAverageWin())))
                .andExpect(jsonPath("$.message", is(message)));
    }

    @Test
    @DisplayName("Player Controller - SHOULD RETURN THE PLAYERS LIST")
    void getAllPlayers_should_return_the_players_list_and_individual_success_rate() throws Exception {
        String uri = "/players/";
        String message = "Player´s List:";

        given(playerService.getAllPlayers()).willReturn(List.of(playerDtoResponse10,playerDtoResponse0));

        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data",hasSize(2)))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.message", is(message)));
    }

    @Test
    @DisplayName("Player Controller - SHOULD RETURN GLOBAL AVERAGE SUCCESS RATE")
    void getRanking_should_return_the_players_average_success_rate() throws Exception {
        String uri = "/players/ranking";
        String message = "Average success rate of all players:";

        Double ranking = (playerDtoResponse10.getAverageWin() + playerDtoResponse0.getAverageWin())/2;

        given(playerService.getAverageSuccessRate()).willReturn(ranking);

        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data",is(ranking)))
                .andExpect(jsonPath("$.message", is(message)));
    }

    @Test
    @DisplayName("Player Controller - SHOULD RETURN THE LOSER PLAYER")
    void getLoserPlayer_should_return_the_loser_player() throws Exception {
        String uri = "/players/ranking/loser";
        String message = "Loser player/s:";

        given(playerService.getLoserPlayer()).willReturn(List.of(playerDtoResponse0));

        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data",hasSize(1)))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.message", is(message)));
    }

    @Test
    @DisplayName("Player Controller - SHOULD RETURN THE LOSERS PLAYERS")
    void getLoserPlayer_should_return_the_loserS_playerS() throws Exception {
        // hay mas de un jugador perdedor
        String uri = "/players/ranking/loser";
        String message = "Loser player/s:";

        // DUDA: esto no se si es mejor ponerlo en before all, por clean code, o no ( ya que no se usa en todos los tests)
        PlayerEntityDTO playerDtoResponse5B = PlayerEntityDTO.builder()
                .playerName("playerTest5B")
                .registerDate(new Date())
                .averageWin(5.0)
                .build();
        given(playerService.getLoserPlayer()).willReturn(List.of(playerDtoResponse5,playerDtoResponse5B));

        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data",hasSize(2)))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.message", is(message)));
    }
    @Test
    @DisplayName("Player Controller - SHOULD RETURN NO LOSER PLAYERS")
    void getLoserPlayer_should_return_no_loser_player() throws Exception {
        // Cuando no hay ningun jugador perdedor
        String uri = "/players/ranking/loser";
        String message = "There are registered single-player plays and he has won. Therefore, there are no losers";

        given(playerService.getLoserPlayer())
                .willThrow( new EntityNotFoundException(message));

        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isNotFound())  // Esperamos un error 500
                // Verificamos que se lance la excepción específica
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFoundException))
                .andExpect(result -> assertEquals(
                        message, result.getResolvedException().getMessage()));
    }

    @Test
    @DisplayName("Player Controller - SHOULD RETURN THE WINNER PLAYER")
    void getLoserPlayer_should_return_the_winner_player() throws Exception {
        String uri = "/players/ranking/winner";
        String message = "This is the winner/s list:";

        given(playerService.getWinnerPlayer()).willReturn(List.of(playerDtoResponse10));

        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data",hasSize(1)))
                .andExpect(jsonPath("$.message",is(message)));

    }

    @Test
    @DisplayName("Player Controller - SHOULD RETURN THE WINNERS PLAYERS")
    void getLoserPlayer_should_return_the_winnerS_playerS() throws Exception {
        String uri = "/players/ranking/winner";
        String message = "This is the winner/s list:";

        // DUDA: esto no se si es mejor ponerlo en before all, por clean code, o no ( ya que no se usa en todos los tests)
        PlayerEntityDTO playerDtoResponse10B = PlayerEntityDTO.builder()
                .playerName("playerTest2")
                .registerDate(new Date())
                .averageWin(10.0)
                .build();

        given(playerService.getWinnerPlayer()).willReturn(List.of(playerDtoResponse10,playerDtoResponse10B));

        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data",hasSize(2)))
                .andExpect(jsonPath("$.message",is(message)));

    }
    @Test
    @DisplayName("Player Controller - SHOULD RETURN NO WINNERS PLAYERS")
    void getLoserPlayer_should_return_no_winnerS_playerS() throws Exception {
        String uri = "/players/ranking/winner";
        String message = "There is just one registered single-player plays and he has lost. Therefore, there are " +
                "no winners";

        given(playerService.getWinnerPlayer()).willThrow(new EntityNotFoundException(message));

        mockMvc.perform(get(uri))
                .andDo(print())
                // Verificamos que se lance la excepción específica
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFoundException))
                .andExpect(result -> assertEquals(
                        message, result.getResolvedException().getMessage()));

    }
}