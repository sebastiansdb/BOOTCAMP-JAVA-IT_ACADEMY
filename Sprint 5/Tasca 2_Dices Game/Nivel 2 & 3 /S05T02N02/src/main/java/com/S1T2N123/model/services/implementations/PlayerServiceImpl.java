package com.S1T2N123.model.services.implementations;

import com.S1T2N123.conversions.PlayerEntityMapper;
import com.S1T2N123.conversions.GameEntityMapper;
import com.S1T2N123.exceptions.EmptyListException;
import com.S1T2N123.exceptions.ResourceNotFoundException;
import com.S1T2N123.model.domain.PlayerEntity;
import com.S1T2N123.model.dto.GameEntityDTO;
import com.S1T2N123.model.dto.PlayerEntityDTO;
import com.S1T2N123.model.repositories.PlayerRepository;
import com.S1T2N123.model.services.interfaces.PlayerService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    // add new player
    public PlayerEntityDTO addNewPlayer(PlayerEntityDTO playerEntityDTOToAdd){
//        if (playerEntityDTOToAdd == null){
//            throw new IllegalArgumentException("The player´s name must not be null");
//        }
        // cargo fecha de creación del jugador
        PlayerEntity playerEntityToAdd = PlayerEntityMapper.mapPlayerEntityDTOToNewPlayerEntity(playerEntityDTOToAdd);
        if (playerEntityToAdd.getPlayerName().equals("null")) {
            playerEntityToAdd.setPlayerName("ANONYMOUS");
            return PlayerEntityMapper.mapNewPlayerEntityToDTO(playerRepository.save(playerEntityToAdd));
//        } else if (checkIfPlayerExists(playerEntityToAdd)) {
        } else if (playerRepository.existsByPlayerName(playerEntityToAdd.getPlayerName())) {
            throw new EntityExistsException("The player" + playerEntityDTOToAdd.getPlayerName() + "already exists");
        } else {
            return PlayerEntityMapper.mapNewPlayerEntityToDTO(playerRepository.save(playerEntityToAdd));
        }
    }

    // update player´s name

    // tengo que poner throws ResourceNotFoundException?? Por que?? (Por el global handler excpention????)
    // es mejor hacer asi como lo hice o con el try-catch comentado?

    // En el caso de PLAYER ANONIMO, actualizará el nombre del primer jugador con nombre "anonimo" que se encuentre en
    // la lista.
    @Override
    public PlayerEntityDTO updatePlayerName (String[] updatedPlayerNameArr){
//        try {
        // si el player no existe, se lanzará la excepcion en el metodo "findPlayerByName"

        Optional<PlayerEntity> playerToUpdate = playerRepository.findByPlayerName(updatedPlayerNameArr[0]);
        if (playerToUpdate.isPresent()) {
            // check que el nombre actualizado del jugador no exista previamente en la BBDD
            if (playerRepository.existsByPlayerName(updatedPlayerNameArr[1])){
                throw new EntityExistsException("The player with name " + updatedPlayerNameArr[1] + " already exists");
            }
            playerToUpdate.get().setPlayerName(updatedPlayerNameArr[1]);
            return PlayerEntityMapper.mapExistingPlayerEntityToDTO(playerRepository.save(playerToUpdate.get()));
        } else {
            // Esta bien lanzar exception aqui, verdad? es corrceto porque uso un GlobalHabdlerExcpetion? O podria lanzar
            // un IllegalArgumentException() tambien?
            throw new ResourceNotFoundException("The player", "Name", updatedPlayerNameArr[0]);
        }

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
                .map(PlayerEntityMapper::mapExistingPlayerEntityToDTO).collect(Collectors.toList());
    }

    // Get the average success rate of all players
    public Double getAverageSuccessRate(){
        // filtro los jugadores que han jugado al menos una vez con "checkGameListIsNotEmpty()" y luego los convierto a DTO
        return checkGameListIsNotEmpty().stream().map(PlayerEntityMapper::mapExistingPlayerEntityToDTO)
                .mapToDouble(PlayerEntityDTO::getAverageWin).average().getAsDouble();
    }

    // Get the player/s with the worst success rate, therefore, the loser player

    // Si hay mas de un jugador perdedor, devuelvo todos ellos.
    // DUDA : Esta bien devolver null cuando no hay losers????  (VER tambien esto en el de WINNERS)
    public List<PlayerEntityDTO> getLoserPlayer(){
        // first, check that the playlist of each player is not null and there are games, with "checkGameListIsNotEmpty()"
        List<PlayerEntity> playerEntityList = checkGameListIsNotEmpty();
        // si hay un solo jugador que jugó y éste ha ganado alguna partida:
        if (playerEntityList.size() == 1
                && PlayerEntityMapper.mapExistingPlayerEntityToDTO(playerEntityList.getFirst()).getAverageWin() > 0 ){
            return null;
        }
        // second, find the minimum average success rate from all players.
        double minimumSuccessRate = playerEntityList.stream().map(PlayerEntityMapper::mapExistingPlayerEntityToDTO)
                .mapToDouble(PlayerEntityDTO::getAverageWin).min().getAsDouble();
        // third, get the player or those players who have lost.
        return playerEntityList.stream()
                .map(PlayerEntityMapper::mapExistingPlayerEntityToDTO)
                .filter(playerEntityDTO -> playerEntityDTO.getAverageWin()==minimumSuccessRate)
                .collect(Collectors.toList());
    }

    // get the player with the best success rate
    public List<PlayerEntityDTO> getWinnerPlayer(){
        List<PlayerEntity> playerEntityList = checkGameListIsNotEmpty();
        // si hay un solo jugador que jugó y no ha ganado nada, entra aquí:
        if (playerEntityList.size() == 1
                && PlayerEntityMapper.mapExistingPlayerEntityToDTO(playerEntityList.getFirst()).getAverageWin() == 0 ){
            return null;
        } else {
            double maximumSuccesRate = playerEntityList.stream().map(PlayerEntityMapper::mapExistingPlayerEntityToDTO)
                    .mapToDouble(PlayerEntityDTO::getAverageWin).max().getAsDouble();
            return playerEntityList.stream().map(PlayerEntityMapper::mapExistingPlayerEntityToDTO)
                    .filter(playerEntityDTO -> playerEntityDTO.getAverageWin() == maximumSuccesRate)
                    .collect(Collectors.toList());
        }
    }

    //              Metodos de logica interna del service ( estos no son invocados desde el controlador )             //

    // find player by id
    // DUDA
    // por que no se queja si no pongo "throws ResourceNotFoundException"?
    public PlayerEntity getPlayerById(Long playerId){
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found","id",playerId));
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
