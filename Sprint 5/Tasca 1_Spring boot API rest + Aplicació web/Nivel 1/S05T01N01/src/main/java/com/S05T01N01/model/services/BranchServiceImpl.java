package com.S05T01N01.model.services;

import com.S05T01N01.exceptions.BadRquestException;
import com.S05T01N01.exceptions.ResourceNotFoundException;
import com.S05T01N01.model.domain.BranchOffice;
import com.S05T01N01.model.dto.BranchOfficeDTO;
import com.S05T01N01.model.repository.BranchRepository;
import com.S05T01N01.model.services.operations.DTOConversor;
import com.S05T01N01.model.services.servicesInterfaces.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    // delete by id
    public void deleteById(Long pk_branchID){
        if(pk_branchID == null){
            throw new BadRquestException("id must not be null");
        } else {
            Optional<BranchOffice> branchOfficeToDeleteOptional = branchRepository.findById(pk_branchID);
            if (branchOfficeToDeleteOptional.isPresent()) {
                branchRepository.delete(branchOfficeToDeleteOptional.get());
            } else {
                throw new ResourceNotFoundException("branch office to delete", "id", pk_branchID);
            }
        }
    }

                                                                // DTO methods

    // get one by id
    public BranchOfficeDTO getDTOById(Long pk_branchID) {
        if(pk_branchID == null) {
            throw new BadRquestException("id must not be null");
        } else {
            Optional<BranchOffice> branchOfficeOptional = branchRepository.findById(pk_branchID);
            BranchOfficeDTO branchOfficeDTO;
            if (branchOfficeOptional.isPresent()) {
                branchOfficeDTO = DTOConversor.convertExistingBranchOfficeToDTO(branchOfficeOptional.get());
            } else {
                throw new ResourceNotFoundException("branch office", "id", pk_branchID);
            }

            return branchOfficeDTO;
        }
    }
    // get all
    @Override
    public List<BranchOfficeDTO> getAllBranchesDTO(){
        List<BranchOffice> branchOfficeList = branchRepository.findAll();
        // Convierto cada elemento de la lista a DTO
        return branchOfficeList.stream().map(DTOConversor::convertExistingBranchOfficeToDTO).collect(Collectors.toList());
    }
    // save
    @Override
    public void saveBranchOfficeDTO (BranchOfficeDTO branchOfficeDTO){
        saveBranchOffice(DTOConversor.convertDTOToNewBranchOffice(branchOfficeDTO));
    }

    // update
    @Override
    public void updateBranchOfficeDTO(BranchOfficeDTO branchOfficeDTOToUpdate){
        // Se ha checkeado previamente que la sucursal ya esté creada en la base de datos (en el metodo "getDTOById")
        saveBranchOffice(DTOConversor.convertDTOToExistingBranchOffice(branchOfficeDTOToUpdate));
    }

}
