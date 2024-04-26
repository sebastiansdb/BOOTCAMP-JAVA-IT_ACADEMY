package com.S1T2N1and2.model.repositories;

import com.S1T2N1and2.model.domain.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity,Long> {
}
