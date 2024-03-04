package com.S04T02N02.model.repository;

import com.S04T02N02.model.domain.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
As we make FruitRepository interface extends JpaRepository, we are able to use JpaRepository’s methods: save(),
findOne(), findById(), findAll(), count(), delete(), deleteById()… without
implementing these methods.

We could also define custom finder methods. The implementation would be plugged in by Spring Data JPA automatically.

Spring Data JPA: https://docs.spring.io/spring-data/jpa/reference/

More Derived queries at:
JPA Repository query example in Spring Boot:https://www.bezkoder.com/jpa-repository-query/
 */
@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {
}
