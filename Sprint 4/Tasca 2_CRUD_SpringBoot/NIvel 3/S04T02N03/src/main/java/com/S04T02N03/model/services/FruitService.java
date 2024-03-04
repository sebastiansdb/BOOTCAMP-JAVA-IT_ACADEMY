package com.S04T02N03.model.services;

import com.S04T02N03.model.domain.Fruit;
import com.S04T02N03.model.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitService {
    private final FruitRepository fruitRepository;

    @Autowired
    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    // add
    public Fruit addFruit(Fruit fruitToAdd){
        return fruitRepository.save(fruitToAdd);
    }

    // get all
    public List<Fruit> getFruitList(){
        return fruitRepository.findAll();
    }

    // get by id
    public Optional<Fruit> getFruitById(String id){
        return fruitRepository.findById(id);

    }
    // delete by id
    public void deleteById(String id){
        fruitRepository.deleteById(id);
    }
    // update by id
    public Fruit updateFruit(String id, String name, double quantityKg){
        Optional<Fruit> fruitToUpdateOptional = fruitRepository.findById(id);
        Fruit updatedFruit = null;
        // DUDA: ES BUENA PRACTICA INICIALIZAR EN NULL, EN ESTE CASO?
        // No lo es.
        // Uno de los motivos es porque podemos olvidarnos de checkear los nulls y el programa
        // puede llegar a romper por dicho motivo
        // Lo ideal ees crear una clase HANDLER EXCEPTIONS y manejar todo desde alli.
        if(fruitToUpdateOptional.isPresent()){
            updatedFruit = fruitToUpdateOptional.get();
            updatedFruit.setName(name);
            updatedFruit.setQuantityKg(quantityKg);
            fruitRepository.save(updatedFruit);
        }
        return updatedFruit;
    }
    public static int longToIntCast(long varToVCast){
        return (int) varToVCast;
    }
}
