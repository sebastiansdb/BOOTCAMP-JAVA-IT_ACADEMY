package com.S1T2N123.model.services.implementations;

import com.S1T2N123.conversions.PlayerEntityMapper;
import com.S1T2N123.conversions.GameEntityMapper;
import com.S1T2N123.exceptions.EmptyListException;
import com.S1T2N123.exceptions.PlayerAlreadyExistsException;
import com.S1T2N123.exceptions.ResourceNotFoundException;
import com.S1T2N123.model.domain.PlayerEntity;
import com.S1T2N123.model.dto.GameEntityDTO;
import com.S1T2N123.model.dto.PlayerEntityDTO;
import com.S1T2N123.model.repositories.PlayerRepository;
import com.S1T2N123.model.repositories.UserRepository;
import com.S1T2N123.model.services.interfaces.PlayerService;
import com.S1T2N123.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepository userRepository;

    // add new player
//    public PlayerEntityDTO addNewPlayer(PlayerEntityDTO playerEntityDTOToAdd){
//        // cargo fecha de creación del jugador
//        PlayerEntity playerEntityToAdd = PlayerEntityMapper.PlayerEntityDTOToNewPlayerEntity(playerEntityDTOToAdd);
//        if (playerEntityToAdd.getPlayerName().equals("null")) {
//            playerEntityToAdd.setPlayerName("ANONYMOUS");
//            return PlayerEntityMapper.newPlayerEntityToDTO(playerRepository.save(playerEntityToAdd));
//        } else if (!checkIfPlayerExists(playerEntityToAdd)) {
//            return PlayerEntityMapper.newPlayerEntityToDTO(playerRepository.save(playerEntityToAdd));
//        } else {
//            throw new PlayerAlreadyExistsException(playerEntityDTOToAdd.getPlayerName());
//        }
//    }

    // USER - add new player
    public PlayerEntityDTO addNewPlayer(PlayerEntityDTO playerEntityDTOToAdd, String jwtToken){
        // DUDA: Tengo que validar algo aquí o ya con la validacion previa hecha es suficiente??
        String userEmail = jwtService.extractUserEmail(jwtToken);
        // Si la la linea de ejecución llegó hasta aquí, es porque el usuario está en la BBDD
        User actualUser = userRepository.findById(userEmail).orElse(null);
        // cargo fecha de creación del jugador
        PlayerEntity playerEntityToAdd = PlayerEntityMapper.PlayerEntityDTOToNewPlayerEntity(playerEntityDTOToAdd);
        playerEntityToAdd.setUserEmail(userEmail);
        if (playerEntityToAdd.getPlayerName().equals("null")) {
            playerEntityToAdd.setPlayerName("ANONYMOUS");
            assert actualUser != null;
            actualUser.setPlayer(playerEntityToAdd);
            userRepository.save(actualUser);
            return PlayerEntityMapper.newPlayerEntityToDTO(playerRepository.save(playerEntityToAdd));
        } else if (!checkIfPlayerExists(playerEntityToAdd)) {
            playerEntityToAdd = playerRepository.save(playerEntityToAdd);
            assert actualUser != null;
            actualUser.setPlayer(playerEntityToAdd);
            userRepository.save(actualUser);
//            return PlayerEntityMapper.newPlayerEntityToDTO(playerRepository.save(playerEntityToAdd));
            return PlayerEntityMapper.newPlayerEntityToDTO(playerEntityToAdd);
        } else {
            throw new PlayerAlreadyExistsException(playerEntityDTOToAdd.getPlayerName());
        }
    }

    // update player´s name

    // tengo que poner throws ResourceNotFoundException?? Por que?? (Por el global handler excpention????)
    // es mejor hacer asi como lo hice o con el try-catch comentado?

    // En el caso de PLAYER ANONIMO, actualizará el nombre del primer jugador con nombre "anonimo" que se encuentre en
    // la lista.
    @Override
    public PlayerEntity updatePlayerName (String[] updatedPlayerNameArr){
//        try {
        // si el player no existe, se lanzará la excepcion en el metodo "findPlayerByName"
        PlayerEntity playerEntityToUpdate = findPlayerByName(updatedPlayerNameArr[0]);
        playerEntityToUpdate.setPlayerName(updatedPlayerNameArr[1]);
        return playerRepository.save(playerEntityToUpdate);
//        } catch (ResourceNotFoundException ex){
//            throw new ResourceNotFoundException("The player ", "PlayerEntity´s name",updatedPlayerNameArr);
//        }
        // VER ESTO::

//        catch (DataAccessException ex){}
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
        // FALTA MANEJO EXCEPCIONES
        /*
        de Chat GPT:
        DataAccessException: Esta excepción generalmente indica un error de acceso a datos, como problemas de
        conectividad con la base de datos, errores de sintaxis SQL, etc.

        InvalidDataAccessResourceUsageException: Esta excepción se produce si hay un uso incorrecto de los recursos
        de acceso a datos, como problemas con la consulta SQL generada.
         */
        List<PlayerEntity> playerEntitiesList = playerRepository.findAll();
        return playerEntitiesList.stream()
                .map(PlayerEntityMapper::existingPlayerEntityToDTO).collect(Collectors.toList());
    }

    // Get all games from one player
    public List<GameEntityDTO> getAllGamesOfOnePlayer(Long playerId){
        PlayerEntity playerEntityToShow = getPlayerById(playerId);
        return playerEntityToShow.getGames().stream().map(GameEntityMapper::mapGameEntitytoDTO).collect(Collectors.toList());
    }

    // Get the average success rate of all players
    public Double getAverageSuccessRate(){
        // filtro los jugadores que han jugado al menos una vez con "checkGameListIsNotEmpty()" y luego los convierto a DTO
        return checkGameListIsNotEmpty().stream().map(PlayerEntityMapper::existingPlayerEntityToDTO)
                .mapToDouble(PlayerEntityDTO::getAverageWin).average().getAsDouble();
    }

    // Get the player/s with the worst success rate, therefore, the loser player

    // DUDA: Si hay mas de un jugador con perdedor, devuelvo todos ellos.
    // DUDA : Esta bien devolver null cuando no hay losers????  (VER tambien esto en el de WINNERS)
    public List<PlayerEntityDTO> getLoserPlayer(){
        // first, check that the playlist of each player is not null and there are games, with "checkGameListIsNotEmpty()"
        List<PlayerEntity> playerEntityList = checkGameListIsNotEmpty();
        // si hay un solo jugador que jugó y éste ha ganado alguna partida:
        if (playerEntityList.size() == 1
                && PlayerEntityMapper.existingPlayerEntityToDTO(playerEntityList.get(0)).getAverageWin() > 0 ){
            return null;
        }
        // second, find the minimum average success rate from all players.
        double minimumSuccessRate = playerEntityList.stream().map(PlayerEntityMapper::existingPlayerEntityToDTO)
                .mapToDouble(PlayerEntityDTO::getAverageWin).min().getAsDouble();
        // third, get the player or those players who have lost.
        return playerEntityList.stream()
                .map(PlayerEntityMapper::existingPlayerEntityToDTO)
                .filter(playerEntityDTO -> playerEntityDTO.getAverageWin()==minimumSuccessRate)
                .collect(Collectors.toList());
    }

    // get the player with the best success rate
    public List<PlayerEntityDTO> getWinnerPlayer(){
        List<PlayerEntity> playerEntityList = checkGameListIsNotEmpty();
        // si hay un solo jugador que jugó y no ha ganado nada, entra aquí:
        if (playerEntityList.size() == 1
                && PlayerEntityMapper.existingPlayerEntityToDTO(playerEntityList.get(0)).getAverageWin() == 0 ){
            return null;
        } else {
            double maximumSuccesRate = playerEntityList.stream().map(PlayerEntityMapper::existingPlayerEntityToDTO)
                    .mapToDouble(PlayerEntityDTO::getAverageWin).max().getAsDouble();
            return playerEntityList.stream().map(PlayerEntityMapper::existingPlayerEntityToDTO)
                    .filter(playerEntityDTO -> playerEntityDTO.getAverageWin() == maximumSuccesRate)
                    .collect(Collectors.toList());
        }
    }

    // metodos de logica interna del service ( estos no son invocados desde el controlador )

    // find player by id
    // DUDA
    // por que no se queja si no pongo "throws ResourceNotFoundException"?
    public PlayerEntity getPlayerById(Long playerId){
        return playerRepository.findById(playerId)
                .orElseThrow(()->new ResourceNotFoundException("Player not found","id",playerId));
    }

    // find player by name

    // - En el caso de consultar un PLAYER ANONIMO, se devolverá el nombre del primer jugador con nombre "anonimo" que se
    // encuentre en la lista.
    // - Sé de antemano que no habrá dos jugadores con el mismo nombre, a menos que sea "ANONIMO"
    public PlayerEntity findPlayerByName(String playerName){
        List<PlayerEntity> playersList = playerRepository.findAll();
        // filter().findAny() devuelve un Optional. Puedo trabajar con Optional o agregar el orElse(other:).
        // Este último devuelve el valor encontrado si es que hay un match y, si no lo encuentra, devuelve "other", que en
        // este caso en un "null" porque yo lo especifico asi
        PlayerEntity playerEntityFound = playersList.stream().
                filter(playerEntity -> playerEntity.getPlayerName().equalsIgnoreCase(playerName)).findAny()
                .orElse(null);
        if (playerEntityFound != null) {
            return playerEntityFound;
        } else {
            // Esta bien lanzar exception aqui, verdad? es correto porque uso un GlobalHabdlerExcpetion? O podria lanzar
            // un IllegalArgumentException() tambien?
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
        // DUDA: Puede devolver un null el findAll() ???? VER SI SACO EL OR DEL IF
        /*
            No devolverá null a menos que el método findAll() del repositorio retorne null, lo cual es poco probable si el
            repositorio está correctamente implementado según los estándares de Spring Data JPA. Si no hay entidades en la
            base de datos, el método findAll() debería devolver una lista vacía ([]), pero no null.
         */
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
