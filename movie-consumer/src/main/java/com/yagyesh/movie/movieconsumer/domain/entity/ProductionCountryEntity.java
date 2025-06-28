package com.yagyesh.movie.movieconsumer.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "production_countries")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductionCountryEntity implements Serializable {
    @Id
    private String iso31661;
    private String name;
}

