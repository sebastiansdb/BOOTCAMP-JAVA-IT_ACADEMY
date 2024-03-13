package com.S05T01N02.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public class BadRquestException extends RuntimeException{

        // Todo lo que está comentado podria ser parte del código, pero no se si es necesario colocarlo en este caso
//        private String resourceName;
//        private String fieldName;
//        private Object fieldValue;
        public BadRquestException(String resourceName){
            super(String.format("`%s` musn´t be null.",resourceName));
//            this.resourceName = resourceName;
        }
    }

