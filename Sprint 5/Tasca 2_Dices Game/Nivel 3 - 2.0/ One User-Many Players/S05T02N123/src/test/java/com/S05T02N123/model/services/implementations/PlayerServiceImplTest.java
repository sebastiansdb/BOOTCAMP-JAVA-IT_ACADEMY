//package com.S1T2N123.model.services.implementations;
//
//import com.S1T2N123.exceptions.EmptyListException;
//import com.S1T2N123.exceptions.ResourceNotFoundException;
//import com.S1T2N123.model.domain.GameEntity;
//import com.S1T2N123.model.domain.PlayerEntity;
//import com.S1T2N123.model.dto.PlayerEntityDTO;
//import com.S1T2N123.model.repositories.PlayerRepository;
//import jakarta.persistence.EntityExistsException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
////@ExtendWith(MockitoExtension.class)
//class PlayerServiceImplTest {
//
//    // service i want to test
//    @InjectMocks
//    private PlayerServiceImpl playerServiceImpl;
//    /*
//        La anotacion @InjectMocks indica que aquí se inyectarán las dependecias marcadas con la anotacion @Mock.
//        Lo que hace es buscar qué dependencias están anotados con @Mock y ver cuáles de ellas son compatibles
//        con las dependencias que necesita la clase PlayerServiceImpl
//     */
//    // declare the dependencies
//    @Mock
//    private PlayerRepository playerRepository;
//
//    // variables
//    PlayerEntityDTO playerDtoTest;
//    PlayerEntity playerEntityTest;
//    PlayerEntity playerEntityUpdatedTest;
//
//    @BeforeEach
//    void setUp(){
//        // para indicar que quiero abrir o inicializar los mocks para esta clase
//        MockitoAnnotations.openMocks(this);
//
//        playerDtoTest = PlayerEntityDTO.builder()
//                .playerName("playerTest")
//                .build();
//
//        playerEntityTest = new PlayerEntity ("playerTest",new Date());
//        playerEntityTest.addNewGame(new GameEntity(3,4,true));
////        playerEntityTest.setPlayerId(1L);
//    }
//    @Test
//    @DisplayName("PlayerServiceImplUnitTest - 'addNewPlayer()' should insert a player ")
//    public void should_successfully_add_a_player(){
//        // given
//        // playerDtoTest
//        // playerEntityTest
//
//        // mock the calls
//        when(playerRepository.save(any(PlayerEntity.class))).thenReturn(playerEntityTest);
//        // when ( Ejecución del método a testear )
//        PlayerEntityDTO dtoTestResponse = playerServiceImpl.addNewPlayer(playerDtoTest);
//        // then
//        assertEquals(dtoTestResponse.getPlayerName(),playerEntityTest.getPlayerName());
//        assertEquals(dtoTestResponse.getRegisterDate(),playerEntityTest.getRegisterDate());
//        assertEquals(dtoTestResponse.getAverageWin(),0.0);
//        verify(playerRepository, times(1)).save(any(PlayerEntity.class));
//    }
//    @Test
//    @DisplayName("PlayerServiceImplUnitTest - 'addNewPlayer()' - creating an ANONYMOUS player ")
//    public void should_successfully_add_an_anonymous_player(){
//        // given
//        playerDtoTest.setPlayerName("null");
//        playerEntityTest.setPlayerName("ANONYMOUS");
//        // mock calls
//        when (playerRepository.save(any(PlayerEntity.class))).thenReturn(playerEntityTest);
//        // when
//        PlayerEntityDTO dtoTestResponse = playerServiceImpl.addNewPlayer(playerDtoTest);
//        //then
//        assertEquals(dtoTestResponse.getPlayerName(),"ANONYMOUS");
//        assertEquals(dtoTestResponse.getRegisterDate(),playerEntityTest.getRegisterDate());
//        assertEquals(dtoTestResponse.getAverageWin(),0.0);
//        verify(playerRepository, times(1)).save(any(PlayerEntity.class));
//    }
//    @Test
//    @DisplayName("PlayerServiceImplUnitTest - 'addNewPlayer()' should throw an Exception if the player already exists ) ")
//    public void save_should_return_exception_when_usedName() {
//        when(playerRepository.existsByPlayerName(playerEntityTest.getPlayerName())).thenReturn(true);
//        assertThrows(EntityExistsException.class, () -> playerServiceImpl.addNewPlayer(playerDtoTest));
//    }
//    @Test
//    @DisplayName("PlayerServiceImplUnitTest - 'updatePlayerName()' should update the player´s name")
//    public void should_successfully_update_the_player_name(){
//        // given
//        String[] updatedPlayerNameArr = {"playerTest", "new One"};
//        // el jugador actualizado es el mismo que el que ya está guardado, pero con distinto nombre
//        playerEntityUpdatedTest = playerEntityTest;
//        playerEntityUpdatedTest.setPlayerName(updatedPlayerNameArr[1]);
//        // mock calls
//        when(playerRepository.findByPlayerName(updatedPlayerNameArr[0])).thenReturn(Optional.of(playerEntityTest));
//        when(playerRepository.save(playerEntityUpdatedTest)).thenReturn(playerEntityUpdatedTest);
//        // when
//        PlayerEntityDTO dtoTest = playerServiceImpl.updatePlayerName(updatedPlayerNameArr);
//        // then
//        assertEquals(dtoTest.getPlayerName(),updatedPlayerNameArr[1]);
//        assertEquals(dtoTest.getRegisterDate(), playerEntityUpdatedTest.getRegisterDate());
//        verify(playerRepository, times(1)).save(playerEntityUpdatedTest);
//    }
//    @Test
//    @DisplayName("PlayerServiceImplUnitTest - 'updatePlayerName()' should throw an Exception if the updated player´s name " +
//            "already exists) ")
//    public void should_return_an_exception_when_updated_player_name_already_exists(){
//        // given
//        String[] updatedPlayerNameArr = {"playerTest", "new One"};
//        // mock calls ( when )
//        when(playerRepository.findByPlayerName(updatedPlayerNameArr[0])).thenReturn(Optional.of(playerEntityTest));
//        when(playerRepository.existsByPlayerName(updatedPlayerNameArr[1])).thenReturn(true);
//        // then
//        assertThrows(EntityExistsException.class, () -> playerServiceImpl.updatePlayerName(updatedPlayerNameArr));
//        verify(playerRepository,times(0)).save(any(PlayerEntity.class));
//    }
//    @Test
//    @DisplayName("PlayerServiceImplUnitTest - 'updatePlayerName()' should throw an Exception if the player´s name " +
//            "to update doesn´t exist) ")
//    public void should_return_an_exception_when_player_name_already_exists(){
//        // given
//        String[] updatedPlayerNameArr = {"playerTest", "new One"};
//        // mock calls ( when )
//        // the player´s name doesn´t exist
//        when(playerRepository.findByPlayerName(updatedPlayerNameArr[0])).thenReturn(Optional.ofNullable(null));
//        // then
//        assertThrows(ResourceNotFoundException.class, () -> playerServiceImpl.updatePlayerName(updatedPlayerNameArr));
//    }
//    @Test
//    @DisplayName("PlayerServiceImplUnitTest - Show all players and each average success rate ")
//    public void should_return_all_players_and_their_average_success_win(){
//        // given
//        List<PlayerEntityDTO> playersListDto;
//        PlayerEntity playerEntityTest2 = new PlayerEntity ("playerTest",new Date());
//        // mock calls
//        when (playerRepository.findAll()).thenReturn(List.of(playerEntityTest,playerEntityTest2));
//        // when
//        playersListDto = playerServiceImpl.getAllPlayers();
//        // then
//        assertEquals(playersListDto.size(),2);
//    }
//    @Test
//    @DisplayName("PlayerServiceImplUnitTest - Shows the average number of games won by all players ")
//    public void should_return_average_success_rate_of_all_players(){
//        // given
//        playerEntityTest.addNewGame(new GameEntity(3,4,true));
//        PlayerEntity playerEntityTest2 = new PlayerEntity ("playerTest",new Date());
//        playerEntityTest2.addNewGame(new GameEntity(1,2,false));
//        // mocks
//        when (playerRepository.findAll()).thenReturn(List.of(playerEntityTest, playerEntityTest2));
//        // when
//        Double successRate = playerServiceImpl.getAverageSuccessRate();
//        // then
//        assertEquals(successRate,50.0);
//    }
//    @Test
//    @DisplayName("PlayerServiceImplUnitTest - Shows the loser player ")
//    public void should_return_the_loser_player(){
//        // given (Just one loser player)
//        PlayerEntity playerEntityTest2 = new PlayerEntity ("playerTest",new Date());
//        playerEntityTest2.addNewGame(new GameEntity(1,2,false));
//        // mock calls
//        when(playerRepository.findAll()).thenReturn(List.of(playerEntityTest,playerEntityTest2));
//        // when
//        List<PlayerEntityDTO> playersList = playerServiceImpl.getLoserPlayer();
//        // then
//        assertEquals(playersList.size(),1);
//    }
//    @Test
//    @DisplayName("PlayerServiceImplUnitTest - Shows the losers players ")
//    public void should_return_the_loser_s_player_s() {
//        // given (More than one loser player)
//        PlayerEntity playerEntityTest2 = new PlayerEntity("playerTest", new Date());
//        playerEntityTest2.addNewGame(new GameEntity(1, 2, false));
//        PlayerEntity playerEntityTest3 = new PlayerEntity("playerTest", new Date());
//        playerEntityTest3.addNewGame(new GameEntity(1, 2, false));
//        // mock calls
//        when(playerRepository.findAll()).thenReturn(List.of(playerEntityTest, playerEntityTest2, playerEntityTest3));
//        // when
//        List<PlayerEntityDTO> playersList = playerServiceImpl.getLoserPlayer();
//        // then
//        assertEquals(playersList.size(), 2);
//    }
//    @Test
//    @DisplayName("PlayerServiceImplUnitTest - Should throw an 'EmptyListException' ")
//    public void should_throw_EmptyListException() {                         // cuando ningun jugador ha jugado partidas
//        // mock calls
//        when(playerRepository.findAll()).thenReturn(new ArrayList<>());
//        // when
//        assertThrows(EmptyListException.class, () -> playerServiceImpl.getLoserPlayer());
//    }
//    @Test
//    @DisplayName("PlayerServiceImplUnitTest - Shows the winner player ")
//    public void should_return_the_winner_player(){
//        // given (Just one winner player)
//        PlayerEntity playerEntityTest2 = new PlayerEntity ("playerTest",new Date());
//        playerEntityTest2.addNewGame(new GameEntity(1,2,false));
//        // mock calls
//        when (playerRepository.findAll()).thenReturn(List.of(playerEntityTest,playerEntityTest2));
//        // when
//        List<PlayerEntityDTO> winnersList = playerServiceImpl.getWinnerPlayer();
//        // then
//        assertEquals(winnersList.size(),1);
//    }
//    @Test
//    @DisplayName("PlayerServiceImplUnitTest - Shows the loserS playerS ")
//    public void should_return_the_winnerS_playerS(){
//        // given (More than one winner player)
//        PlayerEntity playerEntityTest2 = new PlayerEntity("playerTest", new Date());
//        playerEntityTest2.addNewGame(new GameEntity(1, 2, false));
//        PlayerEntity playerEntityTest3 = new PlayerEntity("playerTest", new Date());
//        playerEntityTest3.addNewGame(new GameEntity(5, 2, true));
//        // mock calls
//        when(playerRepository.findAll()).thenReturn(List.of(playerEntityTest,playerEntityTest2,playerEntityTest3));
//        // when
//        List<PlayerEntityDTO> winnersList = playerServiceImpl.getWinnerPlayer();
//        // then
//        assertEquals(winnersList.size(),2);
//    }
//}
