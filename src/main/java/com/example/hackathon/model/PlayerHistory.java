package com.example.hackathon.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="player_history")
public class PlayerHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="player_id")
    private long playerId;

    @Column(name="watched_event")
    private long watchedEvent;

    @Column(name="watched_state")
    private long watchedState;

    @Column(name="current_game_instance_id")
    private long currentGameInstanceId;
}
