package com.S05T01N01.config;

import com.S05T01N01.model.domain.BranchOffice;

import java.util.List;

public class CountriesList {
    private static final List<String> ueCountries = List.of("Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic",
            "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary",
            "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands",
            "Poland", "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "Sweden");

    // check UE country
    public static String ueCountry(BranchOffice branchOffice){
        boolean isUeCountry = ueCountries.stream().
                anyMatch(country->country.equalsIgnoreCase(branchOffice.getBranchCountry()));
        return (isUeCountry)?"UE" : "Out of UE";
    }
}
