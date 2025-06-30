package com.yagyesh.movie.movieconsumer.repository;

import com.yagyesh.movie.movieconsumer.domain.entity.BelongsToCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BelongsToCollectionRepository extends JpaRepository<BelongsToCollectionEntity, Integer> {
    // Additional query methods can be defined here if needed
}
