package com.example.hackathon.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="current_game_instance")
public class CurrentGameInstance {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="game_id")
    private long gameId;

    @Column(name="player_id")
    private long playerId;

    @Column(name="current_yes_count")
    private long currentYesCount;

    @Column(name="current_no_count")
    private long currentNoCount;

}
