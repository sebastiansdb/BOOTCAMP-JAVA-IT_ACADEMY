package com.S1T2N123.model.repositories;

import com.S1T2N123.model.domain.PlayerEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlayerRepositoryUnitTest {
    @Autowired
    PlayerRepository playerRepository;
    private static final String SQL_INSERT_PLAYER = "insert into players (player_id, player_name, register_data, user_email)";

    @BeforeAll
    static void setAndReset(@Autowired JdbcTemplate jdbcTemplate){
        // @param jdbcTemplate the JdbcTemplate used to interact with the database
        // Delete all entries from the 'players' table
        JdbcTestUtils.deleteFromTables(jdbcTemplate,"players");
        // Insert a new player record for testing
        jdbcTemplate.execute(SQL_INSERT_PLAYER + " VALUES (4, 'player test', '" + LocalDateTime.now() +
                "','user4@gm.com')" );
    }
    @Test
    @DisplayName("PlayerRepository - TEST findByPlayerName()")
    void findByPlayerName() {
        // given
        String playerName = "playerTest";
        // when
        PlayerEntity playerFound = playerRepository.findByPlayerName(playerName).get();
        //then
        assertNotNull(playerFound);
        assertEquals(playerFound.getPlayerName(),playerName);

    }

    @Test
    @DisplayName("PlayerRepository - TEST existsByPlayerName()")
    void existsByPlayerName() {
        // given
        boolean existsPlayer;

        existsPlayer = playerRepository.existsByPlayerName("player test");
        assertTrue(existsPlayer);

        existsPlayer = playerRepository.existsByPlayerName("player");
        assertFalse(existsPlayer);
    }
}