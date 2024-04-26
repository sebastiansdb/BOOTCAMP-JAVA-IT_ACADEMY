package com.S1T2N1and2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
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


