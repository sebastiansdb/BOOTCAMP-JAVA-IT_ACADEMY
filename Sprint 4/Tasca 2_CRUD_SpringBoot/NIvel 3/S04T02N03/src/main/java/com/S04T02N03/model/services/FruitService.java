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
    public Optional<Fruit> getFruitById(long id){
        return fruitRepository.findById(longToIntCast(id));

    }
    // delete by id
    public void deleteById(long id){
        fruitRepository.deleteById(longToIntCast(id));
    }
    // update by id
    public Fruit updateFruit(long id, String name, double quantityKg){
        Optional<Fruit> fruitToUpdateOptional = fruitRepository.findById(longToIntCast(id));
        Fruit updatedFruit = null;
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
