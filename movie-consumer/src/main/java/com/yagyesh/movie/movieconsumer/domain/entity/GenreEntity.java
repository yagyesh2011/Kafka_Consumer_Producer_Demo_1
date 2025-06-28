package com.yagyesh.movie.movieconsumer.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "genres")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreEntity {
    @Id
    private int id;
    private String name;
}
