package com.yagyesh.movie.movieservice.controller;

import com.yagyesh.movie.movieservice.service.SyncMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieSyncController {

    @Autowired
    SyncMovieService syncMovieService;

    @PutMapping("/sync")
    public void syncMovies() {
        syncMovieService.syncMovies();
    }
}
