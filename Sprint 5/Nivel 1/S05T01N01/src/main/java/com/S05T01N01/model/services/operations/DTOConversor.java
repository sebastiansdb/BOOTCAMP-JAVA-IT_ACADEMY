package com.S05T01N01.model.services.operations;

import com.S05T01N01.config.CountriesList;
import com.S05T01N01.model.domain.BranchOffice;
import com.S05T01N01.model.dto.BranchOfficeDTO;

public class DTOConversor {

    // convert to DTO
    public static BranchOfficeDTO convertToDTO(BranchOffice branchOffice) {
        BranchOfficeDTO newDTO = new BranchOfficeDTO();
        newDTO.setBranchName(branchOffice.getBranchName());
        newDTO.setBranchCountry(branchOffice.getBranchCountry());
        newDTO.setBranchType(CountriesList.ueCountry(branchOffice));
        return newDTO;
    }

    public static BranchOffice convertToBranchOffice(BranchOfficeDTO branchOfficeDTO){
        BranchOffice newBranchOffice = new BranchOffice();
        newBranchOffice.setBranchName(branchOfficeDTO.getBranchName());
        newBranchOffice.setBranchCountry(branchOfficeDTO.getBranchCountry());
        return newBranchOffice;

    }
}
