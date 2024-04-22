package com.S1T2N123.controllers;

import com.S1T2N123.model.dto.PlayerEntityDTO;
import com.S1T2N123.model.services.implementations.DemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class DemoController {

    @Autowired
    DemoServiceImpl demoServiceImpl;

    @GetMapping("/demo")
    public ResponseEntity<String> sayHello (){
        return ResponseEntity.ok().body("Esto Anda");
    }
    @PostMapping("/demo/add")
    public ResponseEntity<PlayerEntityDTO> demoAddPLayer (
            @RequestBody PlayerEntityDTO playerEntityDTO, @RequestHeader (name = "Authorization") String jwtToken
    ){
        PlayerEntityDTO playerEntityDTOAdded = demoServiceImpl.demoMethodAddPlayer(playerEntityDTO, jwtToken.substring(7));
        return ResponseEntity.ok().body(playerEntityDTOAdded);
    }
}
