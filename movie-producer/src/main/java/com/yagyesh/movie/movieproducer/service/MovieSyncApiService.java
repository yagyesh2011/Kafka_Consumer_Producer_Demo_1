package com.yagyesh.movie.movieproducer.service;

import com.google.protobuf.Message;
import com.yagyesh.movie.domain.Movie;
import com.yagyesh.movie.movieproducer.util.EntityFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Qualifier("movieSyncApiService")
public class MovieSyncApiService implements SyncApiService {
    private final EntityFetcher<Movie, String> entityFetcher;
    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Value("${kafka.topic.top250}")
    private String top250Topic;

    public MovieSyncApiService(EntityFetcher<Movie, String> entityFetcher, KafkaTemplate<String, Message> kafkaTemplate) {
        this.entityFetcher = entityFetcher;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void syncTop250(String type) {
        // This is a placeholder implementation and should be replaced with actual logic
        Runnable runnable = () -> {
            try {
                entityFetcher.getTop250EntitiesIds()
                        .forEach(id -> {
                            Movie movie = entityFetcher.getEntityWithId(id);
                            if (movie != null) {
                                log.info("Publishing movie to Kafka: {}", movie);
                                kafkaTemplate.send(top250Topic, movie.getId(), movie).whenCompleteAsync((result, ex) -> {
                                    if (ex != null) {
                                        log.error("Failed to publish movie with ID {}: {}", id, ex.getMessage());
                                    } else {
                                        log.info("Successfully published movie with ID {}", id);
                                    }
                                });
                            } else {
                                log.warn("Movie with ID {} not found", id);
                            }
                        });
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        log.info("Event for fetching top 250 movies has started!");
    }

    @Override
    public void syncMostPopular(String type) {
        // Logic to synchronize most popular movies
        // This is a placeholder implementation and should be replaced with actual logic
        // For example, fetching the top N popular movies from the API
    }
}
