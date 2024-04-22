package com.S05T01N02.operatios;

import com.S05T01N02.config.CountriesList;
import com.S05T01N02.model.domain.FlowerEntity;
import com.S05T01N02.model.dto.FlowerEntityDTO;

public class DTOConversor {

    // convert FlowerEntity to DTO

    public static FlowerEntityDTO convertFlowerEntityToDTO (FlowerEntity flowerEntity){
        return new FlowerEntityDTO(flowerEntity.getPk_flowerID(),flowerEntity.getFlowerName(),
                flowerEntity.getFlowerCountry(), CountriesList.checkUECountry(flowerEntity));
    }

    // convert DTO to new FlowerEntity
    public static FlowerEntity convertDTOToNewFlowerEntity(FlowerEntityDTO flowerEntityDTO){
        return new FlowerEntity(flowerEntityDTO.getFlowerName(), flowerEntityDTO.getFlowerCountry());
    }

    // convert DTO to Existing FlowerEntity
    public static FlowerEntity convertDTOToExistingFlowerEntity(FlowerEntityDTO flowerEntityDTO){
        FlowerEntity newFlowerEntity = new FlowerEntity();
        newFlowerEntity.setPk_flowerID(flowerEntityDTO.getPk_flowerDTOID());
        newFlowerEntity.setFlowerName(flowerEntityDTO.getFlowerName());
        newFlowerEntity.setFlowerCountry(flowerEntityDTO.getFlowerCountry());
        return newFlowerEntity;
    }
}
