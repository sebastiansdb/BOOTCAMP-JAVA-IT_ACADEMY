package com.S04T02N03.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "fruits")
public class Fruit {

    @Id
    private String id;
    @Field(name = "name")
    @Getter
    @Setter
    private String name;
    @Field(name = "quantity Kg")
    @Getter
    @Setter
    private double quantityKg;

    public Fruit(){

    }
    public Fruit(String name, double quantityKg) {
        this.name = name;
        this.quantityKg = quantityKg;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantityKg=" + quantityKg +
                '}';
    }
}
