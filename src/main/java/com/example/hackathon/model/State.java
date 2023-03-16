package com.example.hackathon.model;

import com.example.hackathon.dto.TrumpCardEnum;

import javax.persistence.*;

@Entity
@Table(name = "state")
public class State {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Enumerated(EnumType.STRING)
    private TrumpCardEnum type;

    @Enumerated(EnumType.STRING)
    private Camp camp;

}
