package com.example.hackathon.model;

import javax.persistence.*;

@Entity
@Table(name = "propaganda_free_media")
public class PropagandaFreeMedia {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name="propaganda_media_id", nullable=false, referencedColumnName = "id")
    private State propagandaMedia;

    @ManyToOne
    @JoinColumn(name="free_media_id", nullable=false, referencedColumnName = "id")
    private State freeMedia;

}
