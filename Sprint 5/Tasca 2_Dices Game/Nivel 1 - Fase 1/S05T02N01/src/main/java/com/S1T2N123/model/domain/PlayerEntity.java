package com.S1T2N123.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_Id")
    private Long playerId;
    @Column (name = "player")
    private String playerName;
    @Column (name = "register data")
    private Date registerDate;

    // Database relations
    // Entidad Padre
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL) //, fetch = FetchType.LAZY) // VER SI LO PONGO O NO
    @JsonManagedReference
//    @JsonIgnore
    private List<GameEntity> games = new ArrayList<>();

    // Aclaraciones teoricas:

    /*
        se usa un List porque un jugador puede tener mas de un juego asignado. Las listas son la manera que tenemos para
        indicar las relaciones uno a muchos o muchos a muchos
     */

    /*

    mappedBy = "player" es una característica fundamental en las relaciones bidireccionales en JPA (Java Persistence
    API). En una relación bidireccional, ambas entidades tienen conocimiento de la relación entre ellas, y una de ellas
    actúa como el "dueño" de la relación, mientras que la otra entidad es simplemente "la parte inversa" de esa relación.

    En el contexto del código:

    PlayerEntity y GameEntity tienen una relación OneToMany. PlayerEntity se define como la entidad principal en esta
    relación, y GameEntity es la entidad secundaria o "inversa"

    Usar mappedBy = "player" en "GameEntity game;" indica que la entidad GameEntity no es la dueña de la relación.
    En otras palabras, el control de la relación recae en PlayerEntity. Por lo tanto, cualquier cambio que se realice
    en la relación (como la inserción, actualización o eliminación de registros relacionados) debe hacerse a través de
    PlayerEntity y no directamente desde Ranking.

    Esto significa que si quiero establecer una relación entre un PlayerEntity y un GameEntity, debería hacerlo a través
    del campo game en PlayerEntity, y no a través del campo player en GameEntity.


    * cascade = CascadeType.ALL"

    La anotación cascade en JPA define el comportamiento de las operaciones de base de datos en relación con las
    entidades asociadas. Cuando se establece CascadeType.ALL, significa que cualquier operación que se realice en la
    entidad PlayerEntity se propagará automáticamente a la entidad GameEntity. Esto incluye operaciones como guardar
    (persistir), actualizar y eliminar.

    Por ejemplo, si guardo un nuevo PlayerEntity en la base de datos, y ese PlayerEntity tiene una relación con un
    GameEntity, éste también se guardará automáticamente. Del mismo modo, si elimino un PlayerEntity,
    el GameEntity relacionado también se eliminará.

    * "fetch = FetchType.LAZY"

    Cuando hablamos de "carga diferida" o "lazy loading" en el contexto de las bases de datos y los sistemas de
    persistencia como JPA (Java Persistence API), nos referimos a una estrategia de recuperación de datos.
    La anotación fetch en JPA determina cómo se recuperarán los datos de las entidades asociadas cuando se realiza una
    consulta.

    FetchType.LAZY: Cuando una relación se carga de forma diferida (lazy), los datos de la entidad relacionada
    (GameEntity en este caso) no se recuperan inmediatamente al consultar PlayerEntity. En lugar de eso, estos datos
    se cargarán solo cuando se acceda explícitamente a ellos. Esto es útil para mejorar el rendimiento y evitar la carga
    innecesaria de datos cuando no son requeridos.

    * Carga Diferida (Lazy Loading)

    La carga diferida o lazy loading se refiere a la práctica de cargar los datos de una entidad (o una relación
    asociada a esa entidad) solo cuando realmente se necesitan.

    Si tengo una entidad PlayerEntity que tiene una relación con otra entidad GameEntity. Si la relación entre PlayerEntity
    y GameEntity se define como de carga diferida (lazy), cuando recupere un objeto PlayerEntity de la base de datos, la
    información sobre sus jugadas no se cargará automáticamente. En su lugar, se cargará sólo cuando intente acceder
    explícitamente a esa información, como cuando llamo a un método para obtener la lista de jugadas de un PlayerEntity.

    En resumen:

    cascade = CascadeType.ALL asegura que cualquier cambio en PlayerEntity se refleje en GameEntity.

    fetch = FetchType.LAZY asegura que los datos de GameEntity solo se recuperen cuando sean necesarios y no de forma
    automática cada vez que consulte PlayerEntity.

    Ambas anotaciones trabajan juntas para definir y optimizar la forma en que se manejan y recuperan las relaciones
    entre PlayerEntity y GameEntity en tu aplicación JPA.

     */

    // mappedBy lo que hace es indicar el nombre de la variable, en la clase GameEntity, que voy a utilizar para
    // relacionar las clases como si fuesen tablas en una base de datos (de hecho, las clases en este caso se
    // transfieren a tablas en BBDD )

    public PlayerEntity(String playerName, Date registerDate) {
        this.playerName = playerName;
        this.registerDate = registerDate;
    }

    // DUDA: Por qué instancia el objeto sin escribir explicitamente  "= new ArrayList<>();"  ?????
    public void addNewGame(GameEntity newGame) {
        games.add(newGame);
        newGame.setPlayer(this);
    }

    public void deleteAllGames() {
        games.clear();
    }
    // si bien el atributo averageWin está sólo en GameEntityDTO, para calcular el mismo, necesito acceder a la lista
    // de games (la cual está sólo en esta clase, GameEntity). Por lo tanto, implemento el siguiente metodo aquí.
    public double calculateAverageWin(){
        double wonGamesCount;
        if (!games.isEmpty()){
            wonGamesCount = (double) (games.stream().filter(GameEntity::isGameWon).count());
            return (wonGamesCount / games.size()) * 100;
        } else {
            return 0.0;
        }

    }
}
