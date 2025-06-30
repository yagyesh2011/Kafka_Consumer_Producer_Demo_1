package com.yagyesh.movie.movieconsumer.repository;

import com.yagyesh.movie.movieconsumer.domain.entity.ProductionCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionCompanyRepository extends JpaRepository<ProductionCompanyEntity, Integer> {
    // Additional query methods can be defined here if needed
}
