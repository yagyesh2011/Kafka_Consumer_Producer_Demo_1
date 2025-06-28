package com.yagyesh.movie.movieconsumer.repository;

import com.yagyesh.movie.movieconsumer.domain.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreEntityRepository extends JpaRepository<GenreEntity, Integer> {
    // Additional query methods can be defined here if needed
}
