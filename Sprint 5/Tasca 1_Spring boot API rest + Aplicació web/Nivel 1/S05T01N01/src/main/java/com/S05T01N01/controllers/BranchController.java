package com.S05T01N01.controllers;

import com.S05T01N01.model.dto.BranchOfficeDTO;
import com.S05T01N01.model.services.BranchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchServiceImpl branchServiceImpl;

    // Comentarios:
    // Creo que el enpoint "get one by id" no hace falta implementarlo porque ya tengo implementada la vista thymeleaf
    // con la lista de todas las sucursales


    // Home View - Get All
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("branchesList", branchServiceImpl.getAllBranchesDTO());
        return "index";
    }
    // add one
    @PostMapping("/saveBranchOffice")
    public String saveBranchOffice(@ModelAttribute("branchOfficeDTO") BranchOfficeDTO branchOfficeDTO){
        branchServiceImpl.saveBranchOfficeDTO(branchOfficeDTO);
        return "redirect:/branch/";
    }
    // update
    @PostMapping("/update")
    public String updateDTO(@ModelAttribute("branchOfficeDTOToUpdate") BranchOfficeDTO branchOfficeDTOToUpdate){
        branchServiceImpl.updateBranchOfficeDTO(branchOfficeDTOToUpdate);
        return "redirect:/branch/";
    }

    // delete
    @GetMapping("/delete/{id}")
    public String deleteBranchOfficeDTO(@PathVariable( value = "id" ) Long pk_branchID){
        branchServiceImpl.deleteById(pk_branchID);
        return "redirect:/branch/";
    }

                                                        // Formularios
    @GetMapping("/showNewBranchForm")
    public String showNewBranchForm(Model model) {
        model.addAttribute("branchOfficeDTO", new BranchOfficeDTO());
        return "newBranchOffice";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id" ) Long pk_branchID, Model model){
        BranchOfficeDTO branchOfficeDTOToUpdate = branchServiceImpl.getDTOById(pk_branchID);
        model.addAttribute( "branchOfficeDTOToUpdate", branchOfficeDTOToUpdate);
        return "branchOfficeFormForUpdate";
    }

}
