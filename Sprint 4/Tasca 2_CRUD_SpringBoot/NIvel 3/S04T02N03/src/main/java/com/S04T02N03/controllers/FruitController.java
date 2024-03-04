package com.S04T02N03.controllers;

import com.S04T02N03.model.domain.Fruit;
import com.S04T02N03.model.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fruit")
public class FruitController {
    @Autowired
    private FruitService fruitService;

    @PostMapping("/add")
    public ResponseEntity<?> addFruit(@RequestBody Fruit fruitToAdd){
        Fruit addedFruit = fruitService.addFruit(fruitToAdd);
        return ResponseEntity.status(HttpStatus.CREATED).body("Fruit added: \n" + addedFruit);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllFruits() {
        List<Fruit> fruitList = fruitService.getFruitList();
        return ResponseEntity.ok(fruitList);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getFruitById(@PathVariable long id){
        Optional<Fruit> fruitOptional = fruitService.getFruitById(id);
        if(fruitOptional.isPresent()){
            return ResponseEntity.ok(fruitOptional.get());
            /*
                El metodo get() de la clase Optional podria arrojar una expecion "NoSuchElementException" en el caso de que el
                contenido del objeto de dicha clase (un objeto Fruit, en este caso) se NULL. Aquí asumo que cuando se agrega una
                fruta, está tendrá todos sus atributos distintos de NULL. Para solucionar estop, se podria usar un
                HANDLE EXCEPTIONS
            */
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This fruit is not at the data base");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFruit(@PathVariable long id){
        Optional<Fruit> fruitOptional = fruitService.getFruitById(id);
        if(fruitOptional.isPresent()){
            fruitService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("The " + fruitOptional.get() + "\nwas deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The given fruit is not at the data base");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFruit(@PathVariable long id,
                                             @RequestParam String name,
                                             @RequestParam double quantityKg
    ){

        Fruit updatedFruit = fruitService.updateFruit(id, name, quantityKg);
        return (updatedFruit==null) ?  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fruit not found") :
                ResponseEntity.status(HttpStatus.ACCEPTED).body("The fruit parameters were updated");
    }
}
