package com.S05T01N01.model.services.servicesInterfaces;

import com.S05T01N01.model.domain.BranchOffice;
import com.S05T01N01.model.dto.BranchOfficeDTO;

import java.util.List;

public interface BranchService {

    // BranchOffice methods
    List<BranchOffice> getAllBranches();
    BranchOffice saveBranchOffice(BranchOffice branchOffice);

    // BranchOfficeDTO methods
    List<BranchOfficeDTO> getAllBranchesDTO();
    void saveBranchOfficeDTO (BranchOfficeDTO branchOfficeDTO);
    void updateBranchOfficeDTO(BranchOfficeDTO branchOfficeDTO);

}
