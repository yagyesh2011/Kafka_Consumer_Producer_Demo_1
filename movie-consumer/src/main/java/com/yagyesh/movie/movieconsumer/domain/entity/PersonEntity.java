package com.yagyesh.movie.movieconsumer.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "persons")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {
    @Id
    private String id;
    private String url;
    private String fullName;
    private String primaryImage;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "person_thumbnails",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "thumbnail_id"))
    private List<ThumbnailEntity> thumbnails;
    private String job;
    @ElementCollection
    private List<String> characters;
}
