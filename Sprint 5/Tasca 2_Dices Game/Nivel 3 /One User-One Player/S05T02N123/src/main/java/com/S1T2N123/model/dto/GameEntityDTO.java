package com.S1T2N123.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEntityDTO {
    private int dice1;
    private int dice2;
    private boolean gameWon;
}
