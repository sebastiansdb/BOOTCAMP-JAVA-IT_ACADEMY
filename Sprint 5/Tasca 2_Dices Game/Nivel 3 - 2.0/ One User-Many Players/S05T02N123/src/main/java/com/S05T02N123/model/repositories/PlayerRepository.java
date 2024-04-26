package com.S05T02N123.model.repositories;

import com.S05T02N123.model.domain.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity,Long> {
    Optional<PlayerEntity> findByPlayerName(String playerName);
    boolean existsByPlayerName(String playerName);
}
