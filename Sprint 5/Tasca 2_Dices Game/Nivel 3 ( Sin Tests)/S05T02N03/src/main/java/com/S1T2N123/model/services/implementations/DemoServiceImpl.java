package com.S1T2N123.model.services.implementations;

import com.S1T2N123.conversions.PlayerEntityMapper;
import com.S1T2N123.exceptions.PlayerAlreadyExistsException;
import com.S1T2N123.model.domain.PlayerEntity;
import com.S1T2N123.model.dto.PlayerEntityDTO;
import com.S1T2N123.model.repositories.PlayerRepository;
import com.S1T2N123.model.repositories.UserRepository;
import com.S1T2N123.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DemoServiceImpl {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PlayerRepository playerRepository;

    public PlayerEntityDTO demoMethodAddPlayer (PlayerEntityDTO playerEntityDTOToAdd, String jwtToken){
        String userEmail = jwtService.extractUserEmail(jwtToken);
        User actualUser = userRepository.findById(userEmail).orElse(null);
        // cargo fecha de creación del jugador
        PlayerEntity playerEntityToAdd = PlayerEntityMapper.PlayerEntityDTOToNewPlayerEntity(playerEntityDTOToAdd);
        playerEntityToAdd.setUserEmail(userEmail);
        if (!checkIfPlayerExists(playerEntityToAdd)) {
            playerEntityToAdd = playerRepository.save(playerEntityToAdd);
            assert actualUser != null;
            actualUser.setPlayer(playerEntityToAdd);
            userRepository.save(actualUser);
            return PlayerEntityMapper.newPlayerEntityToDTO(playerEntityToAdd);
        } else {
            throw new PlayerAlreadyExistsException(playerEntityDTOToAdd.getPlayerName());
        }
    }

    // check if player exists
    public boolean checkIfPlayerExists (PlayerEntity playerEntityToCheck){
        List<PlayerEntity> playersEntityList = playerRepository.findAll();
        return playersEntityList.stream().anyMatch(playerEntity -> playerEntity.getPlayerName()
                .equalsIgnoreCase(playerEntityToCheck.getPlayerName()));
    }
}
