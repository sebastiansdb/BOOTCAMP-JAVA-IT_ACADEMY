package com.S05T01N01.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Branches")
public class BranchOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String branchName;
    private String branchCountry;
}
