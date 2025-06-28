package com.yagyesh.movie.movieconsumer.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEntity {
    @Id
    private String id;
    private String url;
    private String primaryTitle;
    private String originalTitle;
    private String type;
    private String description;
    private String primaryImage;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_thumbnails",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "thumbnail_id"))
    private List<ThumbnailEntity> thumbnails;
    private String trailer;
    private String contentRating;
    private Integer startYear;
    private Integer endYear;
    private LocalDate releaseDate;
    @ElementCollection
    private List<String> interests;
    @ElementCollection
    private List<String> countriesOfOrigin;
    @ElementCollection
    private List<String> externalLinks;
    @ElementCollection
    private List<String> spokenLanguages;
    @ElementCollection
    private List<String> filmingLocations;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "movie_production_companies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "production_company_id"))
    private List<ProductionCompanyEntity> productionCompanies;
    private Long budget;
    private Long grossWorldwide;
    @ElementCollection
    private List<String> genres;
    private Boolean isAdult;
    private Integer runtimeMinutes;
    private Double averageRating;
    private Long numVotes;
    private Integer metascore;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "movie_directors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<PersonEntity> directors;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany
    @JoinTable(name = "movie_writers",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<PersonEntity> writers;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "movie_cast",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<PersonEntity> cast;

}
