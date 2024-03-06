package com.S05T01N01.model.services;

import com.S05T01N01.model.domain.BranchOffice;
import com.S05T01N01.model.dto.BranchOfficeDTO;

import java.util.List;

public interface BranchService {
    List<BranchOffice> getAllBranches();
    BranchOffice saveBranchOffice(BranchOffice branchOffice);

    List<BranchOfficeDTO> getAllBranchesDTO();
    BranchOfficeDTO getBranchOfficeDTO(BranchOffice branchOffice);


}
