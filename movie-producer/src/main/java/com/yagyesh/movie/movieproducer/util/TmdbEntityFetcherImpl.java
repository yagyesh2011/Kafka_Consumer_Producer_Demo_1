package com.yagyesh.movie.movieproducer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yagyesh.movie.domain.Movie;
import com.yagyesh.movie.movieproducer.service.LocalRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

import static com.yagyesh.movie.movieproducer.contants.TMDBEndpoints.MOVIE_DETAILS;
import static com.yagyesh.movie.movieproducer.contants.TMDBEndpoints.MOVIE_TOP_RATED;

@Component
public class TmdbEntityFetcherImpl implements EntityFetcher<Movie, Integer>{

    private final LocalRestClient localRestClient;

    @Autowired
    public TmdbEntityFetcherImpl(LocalRestClient localRestClient, WebClient webClient){
        this.localRestClient = localRestClient;
        localRestClient.setClient(webClient);
    }

    @Override
    public Movie getEntityWithId(Integer integer) {
        // Implementation to fetch movie details from TMDB service
        return (Movie) localRestClient.getWithId(MOVIE_DETAILS, integer.toString(), Movie.newBuilder(), "language=en-US");
    }

    @Override
    public List<Movie> getTopRatedEntities(int page) throws JsonProcessingException {
        return localRestClient.getTopRatedIds(MOVIE_TOP_RATED + page)
                .stream()
                .map(this::getEntityWithId)
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public List<String> getPopularEntities(int page) {
        return List.of();
    }

    @Override
    public List<Integer> getEntitiesIds(int page) throws JsonProcessingException {
        return localRestClient.getTopRatedIds(MOVIE_TOP_RATED + page);
    }
}
