package com.example.hackathon.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name="Media")
public class Media {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotBlank
    @Column(name="source_url")
    private String sourceUrl;

    @NotBlank
    @Column(name="thumbnail_url")
    private String thumbnailUrl;

    @Column(name="media_source")
    private MediaSource mediaSource;

    @Column(name="view_count")
    private long viewCount;

    @Column(name="like_count")
    private long likeCount;

    @Column(name="user_id")
    private long userId;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
