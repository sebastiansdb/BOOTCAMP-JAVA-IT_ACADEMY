package com.S1T2N123.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class ResponseMessage <T> {
    private String message;
    private T data;
    public ResponseMessage(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
