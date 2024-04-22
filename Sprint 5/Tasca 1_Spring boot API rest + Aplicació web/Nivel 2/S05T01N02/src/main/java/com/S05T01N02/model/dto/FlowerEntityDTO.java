package com.S05T01N02.model.dto;

import lombok.Data;

@Data
public class FlowerEntityDTO {
    private Integer pk_flowerDTOID;
    private String flowerName;
    private String flowerCountry;
    private String flowerType;

    public FlowerEntityDTO (Integer pk_flowerDTOID, String flowerName, String flowerCountry, String flowerType){
        this.pk_flowerDTOID = pk_flowerDTOID;
        this.flowerName = flowerName;
        this.flowerCountry = flowerCountry;
        this.flowerType = flowerType;
    }
}
