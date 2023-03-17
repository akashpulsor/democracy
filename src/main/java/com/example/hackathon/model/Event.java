package com.example.hackathon.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
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




    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "event")
    private List<State> mediaList;

    @OneToOne
    @JoinColumn(name="report_state_id", nullable=false, referencedColumnName = "id")
    private State reportState;

    @ManyToOne
    @JoinColumn(name="leader_id", nullable=false)
    private Leader leader;

}
