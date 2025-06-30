package com.yagyesh.movie.movieconsumer.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {
    @Id
    private int id;

    private boolean adult;

    private String backdropPath;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private BelongsToCollectionEntity belongsToCollection;

    private int budget;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieGenre> movieGenres;

    private String homepage;

    private String imdbId;

    @Convert(converter = StringListConverter.class)
    private List<String> originCountry;

    private String originalLanguage;

    private String originalTitle;

    @Column(length = 1000)
    private String overview;

    private double popularity;

    private String posterPath;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private List<ProductionCompanyEntity> productionCompanies;

    private LocalDate releaseDate;

    private long revenue;

    private int runtime;

    @Convert(converter = StringListConverter.class)
    @Column(name = "spoken_languages")
    private List<String> spokenLanguages;

    private String status;

    private String tagline;

    private String title;

    private boolean video;

    private double voteAverage;

    private int voteCount;

    @Convert(converter = StringListConverter.class)
    private List<String> productionCountries;


}

@Converter
class StringListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> list) {
        return list != null ? String.join(",", list) : "";
    }

    @Override
    public List<String> convertToEntityAttribute(String joined) {
        return (joined == null || joined.isEmpty()) ? new ArrayList<>() : Arrays.asList(joined.split(","));
    }
}


