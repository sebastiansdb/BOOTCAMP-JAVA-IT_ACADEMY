package com.S04T02N02.model.domain;

import jakarta.persistence.*;

/*
– @Entity annotation indicates that the class is a persistent Java class.
– @Table annotation provides the table that maps this entity.
– @Id annotation is for the primary key.
– @GeneratedValue annotation is used to define generation strategy for the primary key. GenerationType.AUTO means
  Auto Increment field.
– @Column annotation is used to define the column in database that maps annotated field.
 */


@Entity
@Table(name = "fruits")
public class Fruit {

    /*
    @GeneratedValue(strategy = GenerationType.IDENTITY) is used in Java Persistence API (JPA) to specify how primary
    key values are generated for an entity. In this case, the strategy GenerationType.IDENTITY indicates that the
    database will automatically generate unique primary key values for the entity.

    strategy = GenerationType.IDENTITY: This parameter specifies the strategy to be used for generating primary key
    values. In the IDENTITY strategy, the database automatically assigns a unique primary key value to each new entity
    when it is inserted into the table. This typically relies on an auto-increment feature provided by the database,
    such as auto-increment columns in MySQL or PostgreSQL.

     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity Kg")
    private double quantityKg;

    public Fruit(){

    }
    public Fruit(String name, double quantityKg){
        this.name = name;
        this.quantityKg = quantityKg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantityKg() {
        return quantityKg;
    }

    public void setQuantityKg(double quantityKg) {
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
