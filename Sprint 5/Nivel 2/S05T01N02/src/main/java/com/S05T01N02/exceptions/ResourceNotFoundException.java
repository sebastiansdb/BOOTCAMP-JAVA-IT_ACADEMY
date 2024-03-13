package com.S05T01N02.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    // Todo lo que está comentado podria ser parte del código, pero no se si es necesario colocarlo en este caso
//    private String resourceName;
//    private String fieldName;
//    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue){
        super(String.format("%s was not found with: %s = %s",resourceName,fieldName,fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
    }
}
