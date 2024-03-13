package com.S05T01N02.model.dto;

import lombok.Data;

@Data
public class FlowerEntityDTO {
    private Integer pk_flowerID;
    private String flowerName;
    private String flowerCountry;
    private String flowerType;

    public FlowerEntityDTO (Integer pk_flowerID, String flowerName, String flowerCountry, String flowerType){
        this.pk_flowerID = pk_flowerID;
        this.flowerName = flowerName;
        this.flowerCountry = flowerCountry;
        this.flowerType = flowerType;
    }
}
