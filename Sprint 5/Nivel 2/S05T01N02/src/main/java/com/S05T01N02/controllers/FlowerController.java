package com.S05T01N02.controllers;

import com.S05T01N02.message.ResponseMessage;
import com.S05T01N02.model.dto.FlowerEntityDTO;
import com.S05T01N02.model.services.FlowerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flower")
public class FlowerController {

    @Autowired
    FlowerServiceImpl flowerServiceImpl;
    // add
    @PostMapping("/add")
    public ResponseEntity<ResponseMessage<FlowerEntityDTO>> addFlower(@RequestBody FlowerEntityDTO flowerEntityDTO) {
            FlowerEntityDTO flowerEntityDTOAdded = flowerServiceImpl.addFlowerDTO(flowerEntityDTO);
            ResponseMessage<FlowerEntityDTO> responseMessage = new ResponseMessage<>("Flower added successfully"
                                                                                        ,flowerEntityDTOAdded);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    // Update by id

    // En esta implementacion, asumo que el ususario NO modificará el id, sólo actualizará los atributos distintos de ID.
    // si envia un ID distinto del que quiere actualizar, se actualizará la flor con el ID que envie.
    @PutMapping("/update")
    public ResponseEntity<ResponseMessage<FlowerEntityDTO>> updateFlowerDTO(@RequestBody FlowerEntityDTO flowerEntityDTOUpdatedValues){
        // flowerEntityDTOUpdatedValues CONTIENE valor NULL en su atributo "flowerType" porque el usuario no lo manda,
        // ya que se autogenera
        FlowerEntityDTO flowerEntityDTOUpdated = flowerServiceImpl.updateFlowerDTO(flowerEntityDTOUpdatedValues);
        ResponseMessage<FlowerEntityDTO> responseMessage = new ResponseMessage<>(
                "Flower updated successfully", flowerEntityDTOUpdated
        );
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseMessage);
    }

    // delete by id
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteDTOByID(@PathVariable(value = "id") Integer pk_flowerID){
        flowerServiceImpl.deleteDTOById(pk_flowerID);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                "The flower with id = " + pk_flowerID + " was successfully deleted"
        );
    }
    // get one by id
    @GetMapping("/getOne/{id}")
    public ResponseEntity<ResponseMessage<FlowerEntityDTO>> getFlowerDTOById(@PathVariable (value = "id") Integer pk_flowerID){
        FlowerEntityDTO flowerEntityDTOToShow = flowerServiceImpl.getFlowerDTOById(pk_flowerID);
        ResponseMessage<FlowerEntityDTO> responseMessage = new ResponseMessage<>(
                "Flower asked for:",flowerEntityDTOToShow
        );
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    // get all
    @GetMapping("/getAll")
    public ResponseEntity<ResponseMessage<List<FlowerEntityDTO>>> getAllFlowersDTO (){
        ResponseMessage<List<FlowerEntityDTO>> responseMessage = new ResponseMessage<>(
                "Flower´s List",flowerServiceImpl.getAllFlowersDTO()
        );
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}
