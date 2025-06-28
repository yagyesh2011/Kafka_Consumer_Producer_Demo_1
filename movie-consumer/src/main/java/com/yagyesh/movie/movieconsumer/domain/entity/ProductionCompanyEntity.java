package com.yagyesh.movie.movieconsumer.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import lombok.Data;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "production_companies")
@Data
public class ProductionCompanyEntity {
    @Id
    private int id;

    private String logoPath;

    private String name;

    private String originCountry;
}
