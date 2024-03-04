package com.S04T02N01.model.repository;

import com.S04T02N01.model.domain.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
La persistencia de datos es un medio mediante el cual una aplicación puede recuperar información desde un sistema de
almacenamiento no volátil y hacer que esta persista. Java™ Persistence API (JPA) proporciona un mecanismo para gestionar
la persistencia y la correlación relacional de objetos y las funciones desde las especificaciones EJB 3.0

The Jakarta Persistence API (JPA) simplifies data persistence and object relational mapping for Java applications.
With JPA, applications can efficiently create, read, update, and delete objects from a relational database.

JPA provides a standard for applications to interact with relational databases so that developers don’t need to worry
 about vendor-specific differences among databases or writing time-consuming boilerplate code to persist data.
 */
@Repository
public interface FruitRepository extends JpaRepository<Fruit,Long> {
    /*
        - El Long representa el tipo de dato del ID en la entity.
        - Lo metodos de la interface JpaRepository se implementan a traves de las anotaciones, es logica de Spring Boot
     */
}

/*
As we make FruitRepository interface extends JpaRepository, we are able to use JpaRepository’s methods: save(),
findOne(), findById(), findAll(), count(), delete(), deleteById()… withoutimplementing these methods.

We could also define custom finder methods. The implementation would be plugged in by Spring Data JPA automatically.

Spring Data JPA: https://docs.spring.io/spring-data/jpa/reference/

More Derived queries at:
JPA Repository query example in Spring Boot:https://www.bezkoder.com/jpa-repository-query/
 */