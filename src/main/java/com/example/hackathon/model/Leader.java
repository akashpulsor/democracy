package com.example.hackathon.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Leader")
public class Leader {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToMany(mappedBy = "leader")
    private Set<Event> event;

}
