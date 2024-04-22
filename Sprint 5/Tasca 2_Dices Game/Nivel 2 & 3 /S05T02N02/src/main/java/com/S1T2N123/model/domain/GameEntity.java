package com.S1T2N123.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    @Column (name = "dice 1")
    private int dice1;
    @Column (name = "dice 2")
    private int dice2;
    @Column (name = "game won")
    private boolean gameWon;

    // Database relations

    /*

    @JsonBackReference es parte de la biblioteca Jackson en Java.

    Se utiliza en el contexto de relaciones bidireccionales entre entidades. Esta anotación se coloca sobre el lado
    "hijo" de la relación para indicar que debe ignorarse durante la serialización, evitando así un bucle infinito.

    Cuando tienes una relación bidireccional entre dos entidades (como PlayerEntity y GameEntity), si no
    manejas correctamente las referencias, puedes terminar en un bucle infinito de serialización. Por ejemplo:
    si intentas serializar un objeto PlayerEntity, que tiene una lista de GameEntity, y cada GameEntity a su vez hace
    referencia al PlayerEntity padre, terminarías con una estructura JSON infinitamente anidada.

    La anotación @JsonBackReference resuelve este problema al indicar que la relación debe ser ignorada durante la
    serialización en el lado "inverso" de la relación.

    Con @JsonBackReference en GameEntity, cuando serializo un objeto PlayerEntity, la relación con GameEntity será
    ignorada, evitando el bucle infinito y problemas de serialización. Es importante tener en cuenta que la anotación
    @JsonBackReference funciona junto con @JsonManagedReference, que se coloca en el lado "padre" o "principal" de la
    relación para mantener la referencia y permitir la serialización completa cuando es necesario.

     */

    // Entidad Hija
    @ManyToOne                                              //(fetch = FetchType.LAZY) ¿¿Hace falta est0??
    @JoinColumn(name = "player_Id")
    @JsonBackReference                                      // ver explicación de esta anotación en comentario de arriba
    private PlayerEntity player;

    public GameEntity(int dice1, int dice2, boolean gameWon) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.gameWon = gameWon;
    }

    public void rollDices(){
        // atributos del nuevo juego
        this.dice1 = (int) (Math.random() * 6) + 1;
        this.dice2 = (int) (Math.random() * 6) + 1;
        this.gameWon = (dice1 + dice2) == 7;
    }
}
