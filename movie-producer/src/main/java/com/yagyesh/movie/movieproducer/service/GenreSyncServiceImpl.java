package com.yagyesh.movie.movieproducer.service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import com.yagyesh.movie.domain.GenreResponse;
import com.yagyesh.movie.movieproducer.contants.TMDBEndpoints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class GenreSyncServiceImpl implements SyncGenre {

    @Autowired
    public WebClient client;

    @Autowired
    KafkaTemplate<String, Message> kafkaTemplate;

    @Retryable(value = {RuntimeException.class}, maxAttempts = 3, backoff = @org.springframework.retry.annotation.Backoff(delay = 4000))
    @Override
    public void syncGenres() {
        try {
        String ratings = client.get()
                .uri(TMDBEndpoints.GENRE_LIST_WITH_API_KEY)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        if (ratings == null || ratings.isEmpty()) {
            throw new RuntimeException("No data received from ratings API");
        }
        log.info("Ratings data received: " + ratings);
        GenreResponse.Builder builder = GenreResponse.newBuilder();
        JsonFormat.Parser parser = JsonFormat.parser();

            parser.merge(ratings, builder);
            GenreResponse genreResponse = builder.build();
            log.info("Parsed GenreResponse: " + genreResponse);
            kafkaTemplate.send("movie-genre-topic", genreResponse).whenCompleteAsync(
                (result, ex) -> {
                    if (ex != null) {
                        System.err.println("Failed to send message to Kafka: " + ex.getMessage());
                    } else {
                        System.out.println("Message sent to Kafka successfully: " + result.getRecordMetadata());
                    }
                }
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
