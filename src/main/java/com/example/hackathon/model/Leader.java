package com.example.hackathon.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
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
