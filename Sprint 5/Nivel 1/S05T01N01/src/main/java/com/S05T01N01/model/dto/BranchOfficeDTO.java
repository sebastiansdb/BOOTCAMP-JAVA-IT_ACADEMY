package com.S05T01N01.model.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class BranchOfficeDTO {
    private Long id;
    @Getter
    @Setter
    private String branchName;
    @Getter
    @Setter
    private String branchCountry;
    @Getter
    @Setter
    private String branchType;

    public BranchOfficeDTO(String branchName, String branchCountry, String branchType) {
        this.branchName = branchName;
        this.branchCountry = branchCountry;
        this.branchType = branchType;
    }
}
