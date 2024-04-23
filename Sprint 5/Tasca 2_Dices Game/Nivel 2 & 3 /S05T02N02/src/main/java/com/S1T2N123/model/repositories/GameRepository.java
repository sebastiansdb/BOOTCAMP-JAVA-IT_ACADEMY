package com.S1T2N123.model.repositories;

import com.S1T2N123.model.domain.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity,Long> {

//    @Query ("DELETE FROM diceGame.games WHERE player_id = :playerId")
//    void deleteByPlayerId(Long playerId);

}
