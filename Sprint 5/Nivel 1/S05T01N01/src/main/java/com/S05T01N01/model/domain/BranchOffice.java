package com.S05T01N01.model.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Branches")
@ToString
@Getter
@Setter
@NoArgsConstructor
public class BranchOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk_branchID;
    @Column(name = "branch name")
    private String branchName;
    @Column(name = "branch country")
    private String branchCountry;

    public BranchOffice(String branchName, String branchCountry){
        this.branchName = branchName;
        this.branchCountry = branchCountry;
    }

}
