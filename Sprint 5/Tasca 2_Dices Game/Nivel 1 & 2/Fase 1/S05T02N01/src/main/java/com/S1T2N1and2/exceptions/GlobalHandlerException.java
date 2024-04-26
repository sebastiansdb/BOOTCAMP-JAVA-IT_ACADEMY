package com.S1T2N1and2.exceptions;

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
    @ExceptionHandler(PlayerAlreadyExistsException.class)
    public ResponseEntity<String> handlerResourceAlreadyExistsException(PlayerAlreadyExistsException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<String> handlerEmptyListException(EmptyListException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }
}
