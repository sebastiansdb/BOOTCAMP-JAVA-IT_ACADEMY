package com.S05T01N02.config;

import com.S05T01N02.model.domain.FlowerEntity;
import com.S05T01N02.model.dto.FlowerEntityDTO;

import java.util.List;

public class CountriesList {
    private static final List<String> ueCountries = List.of("Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic",
            "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary",
            "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands",
            "Poland", "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "Sweden");

    public static String checkUECountry (FlowerEntity flowerEntity){
        boolean isUECountry;
        isUECountry = ueCountries.stream().anyMatch(country -> country.equalsIgnoreCase(flowerEntity.getFlowerCountry()));
        return (isUECountry)? "UE" : "Out of UE";
    }
}
