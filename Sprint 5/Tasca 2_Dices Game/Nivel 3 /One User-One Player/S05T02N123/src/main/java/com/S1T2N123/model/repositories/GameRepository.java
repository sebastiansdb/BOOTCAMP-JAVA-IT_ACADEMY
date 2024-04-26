package com.S1T2N123.model.repositories;

import com.S1T2N123.model.domain.GameEntity;
import com.S1T2N123.model.domain.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity,Long> {
    void deleteByPlayer(PlayerEntity player);

}
