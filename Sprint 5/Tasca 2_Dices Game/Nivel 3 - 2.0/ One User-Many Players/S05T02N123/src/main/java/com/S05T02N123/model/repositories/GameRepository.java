package com.S05T02N123.model.repositories;

import com.S05T02N123.model.domain.GameEntity;
import com.S05T02N123.model.domain.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity,Long> {
    void deleteByPlayer(PlayerEntity player);

}
