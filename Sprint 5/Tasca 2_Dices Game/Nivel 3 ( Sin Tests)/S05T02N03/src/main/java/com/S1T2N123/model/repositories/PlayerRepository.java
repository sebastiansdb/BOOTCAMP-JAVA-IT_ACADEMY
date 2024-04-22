package com.S1T2N123.model.repositories;

import com.S1T2N123.model.domain.PlayerEntity;
import com.S1T2N123.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity,Long> {
}
