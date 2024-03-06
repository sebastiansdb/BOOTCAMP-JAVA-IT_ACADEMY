package com.S05T01N01.controllers;

import com.S05T01N01.model.domain.BranchOffice;
import com.S05T01N01.model.dto.BranchOfficeDTO;
import com.S05T01N01.model.services.BranchServiceImpl;
import com.S05T01N01.model.services.operations.DTOConversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    BranchServiceImpl branchServiceImpl;

    // Home View
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("branchesList", branchServiceImpl.getAllBranchesDTO());
        return "index";
    }

    @GetMapping("/showNewBranchForm")
    public String showNewBranchForm(Model model) {
        BranchOfficeDTO branchOfficeDTO = new BranchOfficeDTO();
        // DUDA : Que hace el model attribute??
        model.addAttribute("BranchOfficeDTO", branchOfficeDTO);
        return "newBranchOffice";
    }
    @PostMapping("/saveBranchOffice")
    public String saveBranchOffice(@ModelAttribute("BranchOfficeDTO") BranchOfficeDTO branchOfficeDTO){
        // DUDA: Esta bien convertir de DTO a Branch office aqui en converor o mejor en service?
//        branchServiceImpl.saveBranchOffice(DTOConversor.convertToBranchOffice(branchOfficeDTO));
        branchServiceImpl.saveBranchOfficeDTO(branchOfficeDTO);
        return "redirect:branch/";
    }
//    @GetMapping("/getOne{id}/")
//    public String getOneById(Model model) {
//
//    }
}
