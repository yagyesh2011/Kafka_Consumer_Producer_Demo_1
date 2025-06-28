package com.yagyesh.movie.movieconsumer.listener;


import com.google.protobuf.DynamicMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.yagyesh.movie.domain.Movie;
import com.yagyesh.movie.movieconsumer.domain.entity.MovieEntity;
import com.yagyesh.movie.movieconsumer.domain.entity.PersonEntity;
import com.yagyesh.movie.movieconsumer.domain.entity.ProductionCompanyEntity;
import com.yagyesh.movie.movieconsumer.domain.entity.ThumbnailEntity;
import com.yagyesh.movie.movieconsumer.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class Top250Listener {

    private final MovieRepository movieRepository;

    @Autowired
    public Top250Listener(MovieRepository movieRepository) {
        // Constructor for dependency injection if needed
        this.movieRepository = movieRepository;
    }

    @KafkaListener(topics = "top-250", groupId = "movie-consumer-group")
    public void listenTop250(DynamicMessage message1) throws InvalidProtocolBufferException {
        Movie.Builder movieBuilder = Movie.newBuilder();
        JsonFormat.Parser parser = JsonFormat.parser();
        parser.merge(message1.toString(), movieBuilder);
        Movie message = movieBuilder.build();
        // Process the message for top 250 movies
        log.info("Received message from top-250 topic: {}", message);
        MovieEntity.MovieEntityBuilder movieEntityBuilder = MovieEntity.builder()
                .id(message.getId())
                .originalTitle(message.getOriginalTitle())
                .endYear(message.getEndYear())
                .primaryImage(message.getPrimaryImage())
                .genres(message.getGenresList())
                .contentRating(message.getContentRating())
                .averageRating(message.getAverageRating())
                .numVotes(message.getNumVotes())
                .budget(message.getBudget())
                .description(message.getDescription())
                .grossWorldwide(message.getGrossWorldwide())
                .isAdult(message.getIsAdult())
                .spokenLanguages(message.getSpokenLanguagesList())
                .runtimeMinutes(message.getRuntimeMinutes())
                .directors(message.getDirectorsList().stream()
                        .map(director -> PersonEntity.builder()
                                .id(director.getId())
                                .url(director.getUrl())
                                .fullName(director.getFullName())
                                .build())
                        .toList())
                .writers(message.getWritersList().stream()
                        .map(writer -> PersonEntity.builder()
                                .id(writer.getId())
                                .url(writer.getUrl())
                                .fullName(writer.getFullName())
                                .build())
                        .toList())
                .cast(message.getCastList().stream()
                        .map(castMember -> PersonEntity.builder()
                                .id(castMember.getId())
                                .url(castMember.getUrl())
                                .fullName(castMember.getFullName())
                                .job(castMember.getJob())
                                .thumbnails(castMember.getThumbnailsList().stream()
                                        .map(thumbnail -> ThumbnailEntity.builder()
                                                .url(thumbnail.getUrl())
                                                .width(thumbnail.getWidth())
                                                .height(thumbnail.getHeight())
                                                .build())
                                        .toList())
                                .characters(castMember.getCharactersList())
                                .build())
                        .toList())
                .metascore(message.getMetascore())
                .startYear(message.getStartYear())
                .type(message.getType())
                .url(message.getUrl())
                .primaryTitle(message.getPrimaryTitle())
                .thumbnails(message.getThumbnailsList().stream()
                        .map(thumbnail -> ThumbnailEntity.builder()
                                .url(thumbnail.getUrl())
                                .width(thumbnail.getWidth())
                                .height(thumbnail.getHeight())
                                .build())
                        .toList())
                .trailer(message.getTrailer())
                .countriesOfOrigin(message.getCountriesOfOriginList())
                .externalLinks(message.getExternalLinksList())
                .filmingLocations(message.getFilmingLocationsList())
                .interests(message.getInterestsList())
                .releaseDate(parseReleaseDate(message.getReleaseDate()))
                .productionCompanies(message.getProductionCompaniesList().stream()
                        .map(company -> ProductionCompanyEntity.builder()
                                .id(company.getId())
                                .name(company.getName())
                                .build())
                        .toList());

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


