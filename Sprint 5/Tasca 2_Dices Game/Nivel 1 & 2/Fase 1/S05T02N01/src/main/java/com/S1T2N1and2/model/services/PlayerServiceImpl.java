package com.S1T2N1and2.model.services;

import com.S1T2N1and2.conversions.EntitiesConversor;
import com.S1T2N1and2.exceptions.EmptyListException;
import com.S1T2N1and2.exceptions.PlayerAlreadyExistsException;
import com.S1T2N1and2.exceptions.ResourceNotFoundException;
import com.S1T2N1and2.model.domain.PlayerEntity;
import com.S1T2N1and2.model.dto.GameEntityDTO;
import com.S1T2N1and2.model.dto.PlayerEntityDTO;
import com.S1T2N1and2.model.repositories.PlayerRepository;
import com.S1T2N1and2.model.services.serviceInterfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    // add new player
    public PlayerEntityDTO addNewPlayer(PlayerEntityDTO playerEntityDTOToAdd){
        // cargo fecha de creación del jugador
        PlayerEntity playerEntityToAdd = EntitiesConversor.PlayerEntityDTOToNewPlayerEntity(playerEntityDTOToAdd);
        if (playerEntityToAdd.getPlayerName().equals("null")) {
            playerEntityToAdd.setPlayerName("ANONYMOUS");
            return EntitiesConversor.newPlayerEntityToDTO(playerRepository.save(playerEntityToAdd));
        } else if (!checkIfPlayerExists(playerEntityToAdd)) {
            return EntitiesConversor.newPlayerEntityToDTO(playerRepository.save(playerEntityToAdd));
        } else {
            throw new PlayerAlreadyExistsException(playerEntityDTOToAdd.getPlayerName());
        }
    }

    // update player´s name

    @Override
    // En el caso de PLAYER ANONIMO, actualizará el nombre del primer jugador con nombre "anonimo" que se encuentre en
    // la lista
    public PlayerEntity updatePlayerName (String[] updatedPlayerNameArr){
        // si el player no existe, se lanzará la excepcion en el metodo "findPlayerByName"
        PlayerEntity playerEntityToUpdate = findPlayerByName(updatedPlayerNameArr[0]);
        playerEntityToUpdate.setPlayerName(updatedPlayerNameArr[1]);
        return playerRepository.save(playerEntityToUpdate);
        /*
        DataAccessException: Esta excepción generalmente indica un error de acceso a datos, como problemas de
        conectividad con la base de datos, errores de sintaxis SQL, etc.

ConstraintViolationException: Esta excepción ocurre si se violan restricciones de integridad de la base de datos al
intentar guardar el objeto. Por ejemplo, si el objeto viola una restricción de clave única o una restricción de clave
externa.

DataIntegrityViolationException: Similar a ConstraintViolationException, esta excepción indica que se ha violado la
integridad de los datos. Puede ocurrir si hay problemas como la violación de restricciones de clave única o de
restricciones de clave externa.

InvalidDataAccessApiUsageException: Esta excepción indica un uso incorrecto de las API de acceso a datos, como intentar
guardar un objeto que no es válido o que falta información necesaria.

Es importante manejar estas excepciones de manera adecuada para garantizar la integridad y confiabilidad de las
operaciones de persistencia de datos.
         */
    }

    // get all players and each average success rate
    @Override
    public List<PlayerEntityDTO> getAllPlayers() {
        List<PlayerEntity> playerEntitiesList = playerRepository.findAll();
        return playerEntitiesList.stream()
                .map(EntitiesConversor::existingPlayerEntityToDTO).collect(Collectors.toList());
    }

    // Get all games from one player
    public List<GameEntityDTO> getAllGamesOfOnePlayer(Long playerId){
        PlayerEntity playerEntityToShow = getPlayerById(playerId);
        return playerEntityToShow.getGames().stream().map(EntitiesConversor::GameEntitytoDTO).collect(Collectors.toList());
    }

    // Get the average success rate of all players
    public Double getAverageSuccessRate(){
        // filtro los jugadores que han jugado al menos una vez con "checkGameListIsNotEmpty()" y luego los convierto a DTO
        return checkGameListIsNotEmpty().stream().map(EntitiesConversor::existingPlayerEntityToDTO)
                .mapToDouble(PlayerEntityDTO::getAverageWin).average().getAsDouble();
    }

    // Get the player/s with the worst success rate, therefore, the loser player
    public List<PlayerEntityDTO> getLoserPlayer(){
        // first, check that the playlist of each player is not null and there are games, with "checkGameListIsNotEmpty()"
        List<PlayerEntity> playerEntityList = checkGameListIsNotEmpty();
        // si hay un solo jugador que jugó y éste ha ganado alguna partida:
        if (playerEntityList.size() == 1
                && EntitiesConversor.existingPlayerEntityToDTO(playerEntityList.get(0)).getAverageWin() > 0 ){
            // NO es buena práctica devolver un null. En la version completa, el nivel 3, está implementado lanzando
            // una excepcion, en lugar de retornar un null.
            return null;
        }
        // second, find the minimum average success rate from all players.
        double minimumSuccessRate = playerEntityList.stream().map(EntitiesConversor::existingPlayerEntityToDTO)
                .mapToDouble(PlayerEntityDTO::getAverageWin).min().getAsDouble();
        // third, get the player or those players who have lost.
        return playerEntityList.stream()
                .map(EntitiesConversor::existingPlayerEntityToDTO)
                .filter(playerEntityDTO -> playerEntityDTO.getAverageWin()==minimumSuccessRate)
                .collect(Collectors.toList());
    }

    // get the player with the best success rate
    public List<PlayerEntityDTO> getWinnerPlayer(){
        List<PlayerEntity> playerEntityList = checkGameListIsNotEmpty();
        // si hay un solo jugador que jugó y no ha ganado nada, entra aquí:
        if (playerEntityList.size() == 1
                && EntitiesConversor.existingPlayerEntityToDTO(playerEntityList.get(0)).getAverageWin() == 0 ){
            // NO es buena práctica devolver un null. En la version completa, el nivel 3, está implementado lanzando
            // una excepcion, en lugar de retornar un null.
            return null;
        } else {
            double maximumSuccesRate = playerEntityList.stream().map(EntitiesConversor::existingPlayerEntityToDTO)
                    .mapToDouble(PlayerEntityDTO::getAverageWin).max().getAsDouble();
            return playerEntityList.stream().map(EntitiesConversor::existingPlayerEntityToDTO)
                    .filter(playerEntityDTO -> playerEntityDTO.getAverageWin() == maximumSuccesRate)
                    .collect(Collectors.toList());
        }
    }

    /**               Metodos de logica interna del service ( estos no son invocados desde el controlador )          */

    // find player by id

    public PlayerEntity getPlayerById(Long playerId){
        return playerRepository.findById(playerId)
                .orElseThrow(()->new ResourceNotFoundException("Player not found","id",playerId));
    }

    // find player by name

    // - Sé de antemano que no habrá dos jugadores con el mismo nombre, a menos que sea "ANONIMO"
    public PlayerEntity findPlayerByName(String playerName){
        List<PlayerEntity> playersList = playerRepository.findAll();
        // filter().findAny() devuelve un Optional. Puedo trabajar con Optional o agregar el orElse(other:).
        // Este último devuelve el valor encontrado si es que hay un match y, si no lo encuentra, devuelve "other",
        // que en este caso en un "null" porque yo lo especifico asi
        PlayerEntity playerEntityFound = playersList.stream().
                filter(playerEntity -> playerEntity.getPlayerName().equalsIgnoreCase(playerName)).findAny()
                .orElse(null);
        if (playerEntityFound != null) {
            return playerEntityFound;
        } else {
            throw new ResourceNotFoundException("The player", "Name", playerName);
        }
    }

    // check if player exists
    public boolean checkIfPlayerExists (PlayerEntity playerEntityToCheck){
        List<PlayerEntity> playersEntityList = playerRepository.findAll();
        return playersEntityList.stream().anyMatch(playerEntity -> playerEntity.getPlayerName()
                .equalsIgnoreCase(playerEntityToCheck.getPlayerName()));
    }

    // checkear que la lista de jugadas de un jugador no esté vacia
    public List<PlayerEntity> checkGameListIsNotEmpty(){
        List<PlayerEntity> playerEntityList = playerRepository.findAll().stream()
                .filter(playerEntity -> !playerEntity.getGames().isEmpty()).collect(Collectors.toList());
        // si la lista está vacía, se lanza excepcion
        if(playerEntityList.isEmpty()){
                throw new EmptyListException("There are no players who have played yet");
        } else{
            return playerEntityList;
        }
    }
}
