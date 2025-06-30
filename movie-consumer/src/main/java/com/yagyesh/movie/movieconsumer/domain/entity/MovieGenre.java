package com.yagyesh.movie.movieconsumer.domain.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie_genre")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieGenre {

    @EmbeddedId
    private MovieGenreId id;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @ManyToOne
    @MapsId("genreId")
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    // Optionally, you can add timestamp, audit info, etc.
}
