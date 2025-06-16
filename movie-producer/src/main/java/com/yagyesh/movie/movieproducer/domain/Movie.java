package com.yagyesh.movie.movieproducer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    private String id;
    private String url;
    private String primaryTitle;
    private String originalTitle;
    private String type;
    private String description;
    private String primaryImage;
    private List<Thumbnail> thumbnails;
    private String trailer;
    private String contentRating;
    private Integer startYear;
    private Integer endYear;
    private String releaseDate;
    private List<String> interests;
    private List<String> countriesOfOrigin;
    private List<String> externalLinks;
    private List<String> spokenLanguages;
    private List<String> filmingLocations;
    private List<ProductionCompany> productionCompanies;
    private Long budget;
    private Long grossWorldwide;
    private List<String> genres;
    private Boolean isAdult;
    private Integer runtimeMinutes;
    private Double averageRating;
    private Long numVotes;
    private Integer metascore;
    private List<Person> directors;
    private List<Person> writers;
    private List<CastMember> cast;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Thumbnail {
        private String url;
        private Integer width;
        private Integer height;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProductionCompany {
        private String id;
        private String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Person {
        private String id;
        private String url;
        private String fullName;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CastMember {
        private String id;
        private String url;
        private String fullName;
        private String primaryImage;
        private List<Thumbnail> thumbnails;
        private String job;
        private List<String> characters;
    }
}


