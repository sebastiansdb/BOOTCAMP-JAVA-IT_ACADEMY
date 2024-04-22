package com.S05T01N01.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class BranchOfficeDTO {
    private Long pk_branchID    ;
    private String branchName;
    private String branchCountry;
    private String branchType;

    public BranchOfficeDTO(String branchName, String branchCountry, String branchType) {
        this.branchName = branchName;
        this.branchCountry = branchCountry;
        this.branchType = branchType;
    }
}
