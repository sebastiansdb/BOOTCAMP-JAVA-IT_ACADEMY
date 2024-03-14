package com.S05T01N01.model.services.operations;

import com.S05T01N01.config.CountriesList;
import com.S05T01N01.model.domain.BranchOffice;
import com.S05T01N01.model.dto.BranchOfficeDTO;

public class DTOConversor {
    // convert existing Branch Office to DTO
    public static BranchOfficeDTO convertExistingBranchOfficeToDTO(BranchOffice branchOffice) {
        BranchOfficeDTO newDTO = new BranchOfficeDTO();
        newDTO.setPk_branchID(branchOffice.getPk_branchID());
        newDTO.setBranchName(branchOffice.getBranchName());
        newDTO.setBranchCountry(branchOffice.getBranchCountry());
        newDTO.setBranchType(CountriesList.ueCountry(branchOffice));
        return newDTO;
    }
    // Convert DTO to new Branch
    /*
        Cuando la sucursal no existe y recibo un DTO para crearla, debo instanciar una new BranchOffice sin "id" para que
        éste se genere automáticamente
     */
    public static BranchOffice convertDTOToNewBranchOffice(BranchOfficeDTO branchOfficeDTO){
        return new BranchOffice(branchOfficeDTO.getBranchName(), branchOfficeDTO.getBranchCountry());
    }

    // Convert DTO to existing Branch
    public static BranchOffice convertDTOToExistingBranchOffice(BranchOfficeDTO branchOfficeDTO){
        BranchOffice newBranchOffice = new BranchOffice();
        newBranchOffice.setPk_branchID(branchOfficeDTO.getPk_branchID());
        newBranchOffice.setBranchName(branchOfficeDTO.getBranchName());
        newBranchOffice.setBranchCountry(branchOfficeDTO.getBranchCountry());
        return newBranchOffice;
    }
}
