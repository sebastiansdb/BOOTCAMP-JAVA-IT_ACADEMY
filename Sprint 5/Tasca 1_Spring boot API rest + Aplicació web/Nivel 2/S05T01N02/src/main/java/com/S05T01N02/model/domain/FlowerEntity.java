package com.S05T01N02.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flowers")
@Data
@NoArgsConstructor
public class FlowerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk_flowerID;
    @Column(name = "flower name")
    private String flowerName;
    @Column(name = "flower country")
    private String flowerCountry;

    public FlowerEntity (String flowerName, String flowerCountry){
        this.flowerName = flowerName;
        this.flowerCountry = flowerCountry;
    }
}
