package com.S04T02N01.model.services;

import com.S04T02N01.model.domain.Fruit;
import com.S04T02N01.model.repository.FruitRepository;
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
    public List<Fruit> getAllFruits(){
        return fruitRepository.findAll();
    }
    // get by id
    public Optional<Fruit> getFruitById(Long id) {
        return fruitRepository.findById(id);
    }
    // delete by id
    public void deleteFruitById(Long id) {
        fruitRepository.deleteById(id);
    }

    // update by id
    public Fruit updateFruit(Long id, String name, int quantityKg){
        // si no existe la fruita con el id proporcionado, el metodo "findById" devuelve un null
        Optional<Fruit> fruitToUpdateOptional = fruitRepository.findById(id);

        // DUDA: ES BUENA PRACTICA INICIALIZAR EN NULL, EN ESTE CASO?
        // No lo es.
        // Uno de los motivos es porque podemos olvidarnos de checkear los nulls y el programa
        // puede llegar a romper por dicho motivo
        // Lo ideal ees crear una clase HANDLER EXCEPTIONS y manejar todo desde alli.
        Fruit updatedFruit = null;
        if(fruitToUpdateOptional.isPresent()){
            updatedFruit = fruitToUpdateOptional.get();
            updatedFruit.setName(name);
            updatedFruit.setQuantityKg(quantityKg);
            // La fruta a actualizar seguirá teniendo el mismo "id" ya que este atributo se obtiene de la flor "antigua"
            // ( updatedFruit = fruitToUpdateOptional.get() ) y no se cambia su valor
            fruitRepository.save(updatedFruit);
        }
        return updatedFruit;
    }
}
