package com.yagyesh.movie.movieconsumer.listener;

import com.google.protobuf.DynamicMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.yagyesh.movie.domain.GenreResponse;
import com.yagyesh.movie.movieconsumer.domain.entity.GenreEntity;
import com.yagyesh.movie.movieconsumer.repository.GenreEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GenreListener {

    private final GenreEntityRepository genreEntityRepository;

    @Autowired
    private GenreListener(GenreEntityRepository genreEntityRepository) {
        this.genreEntityRepository = genreEntityRepository;
        // Constructor for dependency injection if needed
    }

    @KafkaListener(topics = "movie-genre-topic", groupId = "genre-consumer-group")
    public void onGenreEvent(DynamicMessage genreEvent) throws InvalidProtocolBufferException {
        // Process the genre event
        log.info("Received genre event: " + genreEvent);
        byte[] bytes = genreEvent.toByteArray();
        GenreResponse genreResponse = GenreResponse.parseFrom(bytes);
        log.info("Parsed GenreResponse: " + genreResponse);
        List<GenreEntity> genreEntityList = genreResponse.getGenresList().stream().map(
            genre -> GenreEntity.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build()
        ).toList();
        genreEntityRepository.saveAll(genreEntityList);
        // Here you can add logic to handle the genre event, such as saving it to a database
    }

}
