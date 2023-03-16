package com.example.hackathon.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="Game")
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="game_id")
    private Long gameId;

    @Column(name="leader_id")
    private int leaderId;


    @OneToMany(mappedBy = "game")
    private Set<Player> player;


}
