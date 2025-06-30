package com.yagyesh.movie.movieconsumer.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MovieGenreId implements Serializable {
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "genre_id")
    private Integer genreId;

    // equals() and hashCode() must be overridden
}
