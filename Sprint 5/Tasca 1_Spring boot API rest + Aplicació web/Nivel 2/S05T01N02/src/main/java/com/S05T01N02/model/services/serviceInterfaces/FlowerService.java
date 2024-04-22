package com.S05T01N02.model.services.serviceInterfaces;

import com.S05T01N02.model.domain.FlowerEntity;
import com.S05T01N02.model.dto.FlowerEntityDTO;

import java.util.List;

public interface FlowerService {

    // FlowerEntity methods
    FlowerEntity getFlowerById(Integer id);
    FlowerEntity addFlower(FlowerEntity flowerEntityToAdd);
    List<FlowerEntity> getAllFlowers();
    void deleteFlowerById(Integer pk_flowerID);

    // FlowerEntityDTO methods
    FlowerEntityDTO addFlowerDTO(FlowerEntityDTO flowerEntityDTOToAdd);
    FlowerEntityDTO getFlowerDTOById(Integer id);
    List<FlowerEntityDTO> getAllFlowersDTO ();
    FlowerEntityDTO updateFlowerDTO(FlowerEntityDTO flowerEntityDTOUpdated);
    void deleteDTOById (Integer pk_flowerID);
}
