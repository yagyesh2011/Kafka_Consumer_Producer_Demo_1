package com.yagyesh.movie.movieproducer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yagyesh.movie.domain.Movie;
import com.yagyesh.movie.movieproducer.exceptions.MovieNotFoundException;
import com.yagyesh.movie.movieproducer.service.LocalRestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RapidEntityFetcherImpl implements EntityFetcher<Movie, String> {

    private final LocalRestClient webClient;

    @Autowired
    public RapidEntityFetcherImpl(LocalRestClient localRestClient) {
        // Constructor can be used for any initialization if needed
        this.webClient = localRestClient;
    }

    @Override
    public Movie getEntityWithId(String movieId) {
        // Implementation to fetch movie details from the RapidAPI service
        // This is a placeholder implementation and should be replaced with actual logic
        Movie movie = (Movie) webClient.getWithId("/api/imdb/", movieId, Movie.newBuilder(), "language=en-US");
        if (movie == null) {
            throw new MovieNotFoundException("Movie not found for ID: " + movieId);
        }
        log.info("Fetched movie details: {}", movie);
        return movie;
    }

    @Override
    public List<Movie> getTopRatedEntities(int page) {
        return List.of();
    }


    @Override
    public List<String> getPopularEntities(int page) {
        try {
            List<String> ids = webClient.getTop250Ids("/api/imdb/top250-movies");
            log.info("Fetched top 250 movie IDs: {}", ids);
            return ids;
        } catch (Exception e) {
            log.error("Error fetching top 250 movies: {}", e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<Integer> getEntitiesIds(int page) throws JsonProcessingException {
        return List.of();
    }
}
