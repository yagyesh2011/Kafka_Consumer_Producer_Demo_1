package com.yagyesh.movie.movieservice.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SyncMovieService {
    @Autowired
    @Qualifier("mpWebClient")
    public WebClient webClient;

    public void syncMovies() {
        webClient.post()
                .uri("/genres/sync")
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        System.out.println("Genre sync request sent successfully");
    }
}
