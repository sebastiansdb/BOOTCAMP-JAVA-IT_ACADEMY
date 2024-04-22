package com.S1T2N123.model.repositories;

import com.S1T2N123.model.domain.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity,Long> {
}
