package com.S05T02N123.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    @Column(name = "dice_1")
    private int dice1;
    @Column(name = "dice_2")
    private int dice2;
    @Column(name = "game_won")
    private boolean gameWon;

    // Database relations

    // Entidad Hija
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_Id")
    @JsonBackReference
    @JsonIgnore
    private PlayerEntity player;

    public GameEntity(int dice1, int dice2, boolean gameWon) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.gameWon = gameWon;
    }
}
    /**
                                                         * Aclaraciones teorícas

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
