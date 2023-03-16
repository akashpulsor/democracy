package com.example.hackathon.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Player")
public class Player {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="player_name")
    private String playerName;

    @ManyToOne
    @JoinColumn(name="game_id", nullable=false)
    private Game game;
}
