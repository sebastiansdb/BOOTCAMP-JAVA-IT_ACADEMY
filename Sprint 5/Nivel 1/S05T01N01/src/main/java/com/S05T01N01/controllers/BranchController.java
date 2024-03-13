package com.S05T01N01.controllers;

import com.S05T01N01.model.dto.BranchOfficeDTO;
import com.S05T01N01.model.services.BranchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/* TO DO
    VALIDACIONES: https://www.youtube.com/watch?v=Ef2Y0YsQJCE
 */
@Controller
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchServiceImpl branchServiceImpl;

    // DUDA:
    // 1- creo que el get one by id no hace falta porque ya tengo implementada la vista thymeleaf con la lista de todas
    // las sucursales
    // 2- Try catch. Metodo : "BranchOfficeDTO". Si la base de datos esta desactivada, por qué no recibo mensaje de la excepcion??
    // Hacen falta los try catch por no conexxion a la BBDD ? CUnado se lanzarian las excepciones?


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
