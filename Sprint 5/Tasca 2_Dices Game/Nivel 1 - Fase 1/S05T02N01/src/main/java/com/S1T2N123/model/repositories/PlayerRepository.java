package com.S1T2N123.model.repositories;

import com.S1T2N123.model.domain.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity,Long> {
}
