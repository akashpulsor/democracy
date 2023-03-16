package com.example.hackathon.model;

import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne
    @JoinColumn(name="event_state_id", nullable=false, referencedColumnName = "id")
    private State eventState;

    @OneToOne
    @JoinColumn(name="propaganda_free_media_id", nullable=false, referencedColumnName = "id")
    private PropagandaFreeMedia propagandaFreeMediaMap;

    @OneToOne
    @JoinColumn(name="report_state_id", nullable=false, referencedColumnName = "id")
    private State reportState;

    @ManyToOne
    @JoinColumn(name="leader_id", nullable=false)
    private Leader leader;

}
