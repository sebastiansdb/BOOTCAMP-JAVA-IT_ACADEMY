package com.S05T01N02.model.repository;

import com.S05T01N02.model.domain.FlowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowerRepository extends JpaRepository<FlowerEntity,Integer> {
}
