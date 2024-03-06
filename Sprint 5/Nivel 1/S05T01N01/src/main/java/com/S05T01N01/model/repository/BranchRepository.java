package com.S05T01N01.model.repository;

import com.S05T01N01.model.domain.BranchOffice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<BranchOffice,Long> {
}
