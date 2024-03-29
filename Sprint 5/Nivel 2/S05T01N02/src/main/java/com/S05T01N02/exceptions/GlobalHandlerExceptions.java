package com.S05T01N02.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
    Con esta anotacion indicamos que esta clase detectará todos los errores que se produzcan dentro de nuestro controlador
 */
@RestControllerAdvice
public class GlobalHandlerExceptions {

    /*
        Esta anotacion lo que hace es traer el hilo de la ejecución aquí cuando se lanza una excepcion ( en tiempo de
        ejecucion, en este caso ) del tipo "ResourceNotFoundException"
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handlerResourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRquestException.class)
    public ResponseEntity<String> handlerBadRquestException(BadRquestException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
