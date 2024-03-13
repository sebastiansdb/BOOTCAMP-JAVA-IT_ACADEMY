package com.S05T01N01.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRquestException extends RuntimeException{

    public BadRquestException (String message){
        super(message);
    }
}
