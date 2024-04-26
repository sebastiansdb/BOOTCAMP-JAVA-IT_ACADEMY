package com.S05T02N123.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class PlayerEntityDTO {
    private String playerName;
    private Date registerDate;
    private double averageWin;
    public PlayerEntityDTO (String playerName){
        this.playerName = playerName;
    }

    public void setPlayerActualRegisterDate(){
        this.registerDate = new Date();
    }
}


