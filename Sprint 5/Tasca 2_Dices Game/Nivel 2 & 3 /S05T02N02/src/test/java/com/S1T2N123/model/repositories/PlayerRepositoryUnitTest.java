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

        JdbcTestUtils.deleteFromTables(jdbcTemplate,"players");

        jdbcTemplate.execute(SQL_INSERT_PLAYER + " VALUES (4, 'player test', '" + LocalDateTime.now() + "','user4@gm.com')" );
    }
    @Test
    @DisplayName("PlayerRepository - TEST findByPlayerName()")
    void findByPlayerName() {
        PlayerEntity playerFound = playerRepository.findByPlayerName("player test").get();

        assertNotNull(playerFound);
        assertEquals(playerFound.getPlayerName(),"player test");

    }

    @Test
    @DisplayName("PlayerRepository - TEST existsByPlayerName()")
    void existsByPlayerName() {
        boolean existsPlayer = playerRepository.existsByPlayerName("player test");
        assertTrue(existsPlayer);

        existsPlayer = playerRepository.existsByPlayerName("player");
        assertFalse(existsPlayer);
    }
}