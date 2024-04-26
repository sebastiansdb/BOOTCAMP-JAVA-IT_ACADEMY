package com.S1T2N1and2.model.repositories;

import com.S1T2N1and2.model.domain.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity,Long> {
}
