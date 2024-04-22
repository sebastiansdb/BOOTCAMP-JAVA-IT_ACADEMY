package com.S1T2N123.exceptions;

import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handlerResourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<> (exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<String> handlerResourceAlreadyExistsException(EntityExistsException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<String> handlerEmptyListException(EmptyListException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(EntityAlreadyExistsException.class)
//    public ResponseEntity<String> handlerEntityAlreadyExistsException(EntityAlreadyExistsException exception){
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.ALREADY_REPORTED);
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerEntityAlreadyExistsException(IllegalArgumentException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
