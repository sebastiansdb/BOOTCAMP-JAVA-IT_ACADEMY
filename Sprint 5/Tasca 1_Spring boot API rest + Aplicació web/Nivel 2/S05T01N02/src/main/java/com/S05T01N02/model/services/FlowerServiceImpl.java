package com.S05T01N02.model.services;

import com.S05T01N02.exceptions.BadRquestException;
import com.S05T01N02.exceptions.ResourceNotFoundException;
import com.S05T01N02.model.domain.FlowerEntity;
import com.S05T01N02.model.dto.FlowerEntityDTO;
import com.S05T01N02.model.repository.FlowerRepository;
import com.S05T01N02.model.services.serviceInterfaces.FlowerService;
import com.S05T01N02.operatios.DTOConversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlowerServiceImpl implements FlowerService {

    @Autowired
    FlowerRepository flowerRepository;

                                                // FlowerEntity Methods //

    // save
    public FlowerEntity addFlower(FlowerEntity flowerEntityToAdd) {
        return flowerRepository.save(flowerEntityToAdd);
    }

    // get one by id
    @Override
    public FlowerEntity getFlowerById(Integer id) {
        // "()"" denotes an empty parameter list because the lambda function does not take any parameters.
        // orElseThrow():  This part handles the case where the findById operation does not find a flower entity with
        // the given ID. If the Optional is empty (meaning no flower was found), the orElseThrow method is called. This
        // method expects a Supplier functional interface, which provides the logic to create the exception that will
        // be thrown

        // VER EXCEPCIONES conexion BBDD
        if (id == null) {
            throw new BadRquestException("id");
        } else {
            return flowerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                    "The flower", "id", id));
        }
    }

    // get all by id
    @Override
    public List<FlowerEntity> getAllFlowers(){
        return flowerRepository.findAll();
    }

    // delete by id
    @Override
    public void deleteFlowerById(Integer pk_flowerID) {
        // si el id es null, el metodo existsById() lanza una excepcion IllegalArgumentException("The given id must not be null")
        if(flowerRepository.existsById(pk_flowerID)) {
            flowerRepository.deleteById(pk_flowerID);
        } else{
            throw new ResourceNotFoundException("The flower","id",pk_flowerID);
        }
    }

                                                // DTO Methods //

    // add DTO
    @Override
    public FlowerEntityDTO addFlowerDTO(FlowerEntityDTO flowerEntityDTOToAdd) {
        FlowerEntity flowerEntityToAdd = DTOConversor.convertDTOToNewFlowerEntity(flowerEntityDTOToAdd);
        return DTOConversor.convertFlowerEntityToDTO(addFlower(flowerEntityToAdd));
    }

    // get one DTO by id
    @Override
    public FlowerEntityDTO getFlowerDTOById(Integer id) {
        return DTOConversor.convertFlowerEntityToDTO(getFlowerById(id));
    }

    // get all DTO
    @Override
    public List<FlowerEntityDTO> getAllFlowersDTO (){
        List<FlowerEntity> flowerEntityList = getAllFlowers();
        // Convierto cada elemento de la lista a DTO
        return flowerEntityList.stream().map(DTOConversor::convertFlowerEntityToDTO).collect(Collectors.toList());
    }

    // update DTO
    @Override
    public FlowerEntityDTO updateFlowerDTO (FlowerEntityDTO flowerEntityDTOUpdated){
        FlowerEntityDTO flowerEntityDTOToUpdate = getFlowerDTOById(flowerEntityDTOUpdated.getPk_flowerDTOID());
        flowerEntityDTOToUpdate.setFlowerName(flowerEntityDTOUpdated.getFlowerName()); // actualizo atributos de la flowerEntity
        flowerEntityDTOToUpdate.setFlowerCountry(flowerEntityDTOUpdated.getFlowerCountry());
        addFlower(DTOConversor.convertDTOToExistingFlowerEntity(flowerEntityDTOToUpdate)); // guardo flowerEntity actualizada
        return flowerEntityDTOToUpdate;
    }

    // delete DTO by id
    @Override
    public void deleteDTOById (Integer pk_flowerID){
        // podria usar directamente le metodo "deleteFlowerById" sin crear este metodo (deleteDTOById). Lo hago así para
        // tener todo organizado: lo que envio desde controlador a service, lo trabajo con los metods DTO
        deleteFlowerById(pk_flowerID);
    }
}
