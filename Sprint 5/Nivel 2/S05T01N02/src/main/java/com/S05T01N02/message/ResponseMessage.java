package com.S05T01N02.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMessage<T> {
    private String message;
    private T data;

    public ResponseMessage(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
