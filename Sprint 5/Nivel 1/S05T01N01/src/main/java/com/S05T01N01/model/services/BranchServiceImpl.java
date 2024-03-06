package com.S05T01N01.model.services;

import com.S05T01N01.model.domain.BranchOffice;
import com.S05T01N01.model.dto.BranchOfficeDTO;
import com.S05T01N01.model.repository.BranchRepository;
import com.S05T01N01.model.services.operations.DTOConversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    BranchRepository branchRepository;

    // get all branches
    @Override
    public List<BranchOffice> getAllBranches() {
        return branchRepository.findAll();
    }
    // add one
    @Override
    public BranchOffice saveBranchOffice(BranchOffice branchOffice){
        return branchRepository.save(branchOffice);
    }
    public BranchOffice saveBranchOfficeDTO (BranchOfficeDTO branchOfficeDTO){
        return saveBranchOffice(DTOConversor.convertToBranchOffice(branchOfficeDTO));
//        return branchRepository.save(DTOConversor.convertToBranchOffice(branchOfficeDTO));
    }
    // DTO methods
    @Override
    public List<BranchOfficeDTO> getAllBranchesDTO(){
        List<BranchOffice> branchOfficeList = branchRepository.findAll();
        List<BranchOfficeDTO> branchOfficeDTOStream = branchOfficeList.stream().map(DTOConversor::convertToDTO).
                collect(Collectors.toList());
        return branchOfficeDTOStream;
    }

    @Override
    public BranchOfficeDTO getBranchOfficeDTO(BranchOffice branchOffice){
        return DTOConversor.convertToDTO(branchOffice);
    }


}
