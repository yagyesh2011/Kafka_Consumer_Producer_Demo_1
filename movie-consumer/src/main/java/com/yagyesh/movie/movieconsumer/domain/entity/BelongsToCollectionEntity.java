package com.yagyesh.movie.movieconsumer.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "belongs_to_collections")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BelongsToCollectionEntity {
    @Id
    int id;
    String name;
    String posterPath;
    String backdropPath;
}
