package com.yagyesh.movie.movieconsumer.listener;


import com.google.protobuf.DynamicMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.yagyesh.movie.domain.Movie;
import com.yagyesh.movie.domain.ProductionCountry;
import com.yagyesh.movie.domain.SpokenLanguage;
import com.yagyesh.movie.movieconsumer.domain.entity.BelongsToCollectionEntity;
import com.yagyesh.movie.movieconsumer.domain.entity.GenreEntity;
import com.yagyesh.movie.movieconsumer.domain.entity.MovieEntity;
import com.yagyesh.movie.movieconsumer.domain.entity.MovieGenre;
import com.yagyesh.movie.movieconsumer.domain.entity.MovieGenreId;
import com.yagyesh.movie.movieconsumer.domain.entity.ProductionCompanyEntity;
import com.yagyesh.movie.movieconsumer.repository.BelongsToCollectionRepository;
import com.yagyesh.movie.movieconsumer.repository.MovieRepository;
import com.yagyesh.movie.movieconsumer.repository.ProductionCompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class Top250Listener {

    private final MovieRepository movieRepository;

    @Autowired
    private BelongsToCollectionRepository belongsToCollectionRepository;

    @Autowired
    private ProductionCompanyRepository productionCompanyRepository;

    @Autowired
    public Top250Listener(MovieRepository movieRepository) {
        // Constructor for dependency injection if needed
        this.movieRepository = movieRepository;
    }

    @KafkaListener(topics = "top-250", groupId = "movie-consumer-group")
    @Transactional
    public void listenTop250(DynamicMessage message1) throws InvalidProtocolBufferException {
        Movie message = Movie.parseFrom(message1.toByteArray());
        // Process the message for top 250 movies
        log.info("Received message from top-250 topic: {}", message);

        List<ProductionCompanyEntity> productionCompanyEntityList = message.getProductionCompaniesList().stream()
                .map(country -> ProductionCompanyEntity.builder()
                        .id(country.getId())
                        .logoPath(country.getLogoPath())
                        .originCountry(country.getOriginCountry())
                        .name(country.getName())
                        .build())
                .toList();
        BelongsToCollectionEntity belongsToCollectionEntity = null;
        if (message.getBelongsToCollection().getId() != 0) {
            belongsToCollectionEntity = BelongsToCollectionEntity.builder()
                    .id(message.getBelongsToCollection().getId())
                    .name(message.getBelongsToCollection().getName())
                    .build();
            belongsToCollectionRepository.save(belongsToCollectionEntity);
        }
        productionCompanyRepository.saveAll(productionCompanyEntityList);
        MovieEntity.MovieEntityBuilder movieEntityBuilder = MovieEntity.builder()
                .id(message.getId())
                .originalTitle(message.getOriginalTitle())
                .movieGenres(message.getGenresList().stream()
                        .map(genre -> MovieGenre.builder()
                                .id(MovieGenreId.builder()
                                        .movieId(message.getId())
                                        .genreId(genre.getId())
                                        .build())
                                .movie(MovieEntity.builder()
                                        .id(message.getId())
                                        .build())
                                .genre(GenreEntity.builder()
                                        .id(genre.getId())
                                        .build())
                                .build())
                        .toList())
                .voteAverage(message.getVoteAverage())
                .budget(message.getBudget())
                .voteCount(message.getVoteCount())
                .title(message.getTitle())
                .adult(message.getAdult())
                .video(message.getVideo())
                .spokenLanguages(message.getSpokenLanguagesList().stream().map(SpokenLanguage::getName).toList())
                .runtime(message.getRuntime())
                .status(message.getStatus())
                .tagline(message.getTagline())
                .overview(message.getOverview())
                .imdbId(message.getImdbId())
                .homepage(message.getHomepage())
                .backdropPath(message.getBackdropPath())
                .belongsToCollection(belongsToCollectionEntity)
                .posterPath(message.getPosterPath())
                .originalLanguage(message.getOriginalLanguage())
                .originalTitle(message.getOriginalTitle())
                .popularity(message.getPopularity())
                .productionCompanies(productionCompanyEntityList)
                .revenue(message.getRevenue())
                .originCountry(message.getOriginCountryList())
                .productionCountries(message.getProductionCountriesList().stream().map(ProductionCountry::getIso31661).toList())
                .releaseDate(parseReleaseDate(message.getReleaseDate()));
        movieRepository.save(movieEntityBuilder.build());
    }

    public LocalDate parseReleaseDate(String releaseDate) {
        if (releaseDate == null || releaseDate.isEmpty()) {
            return null;
        }
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateTimeFormatter.parse(releaseDate);
            return LocalDate.parse(releaseDate, dateTimeFormatter);
        } catch (Exception e) {
            log.error("Error parsing release date: {}", releaseDate, e);
            return null;
        }
    }
}


