package com.S1T2N123.exceptions;

public class PlayerAlreadyExistsException extends RuntimeException {

    public PlayerAlreadyExistsException (String playerName){
        super (String.format("The player with name '%s' already exists", playerName));
    }
}
