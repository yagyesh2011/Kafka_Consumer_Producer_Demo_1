package com.yagyesh.movie.movieconsumer.repository;

import com.yagyesh.movie.movieconsumer.domain.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    // Additional query methods can be defined here if needed
}
