package com.yagyesh.movie.movieproducer.controller;

import com.yagyesh.movie.domain.Genre;
import com.yagyesh.movie.movieproducer.service.SyncGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {

    // This controller will handle requests related to genres.
    // You can define methods here to handle CRUD operations for genres.
    // For example, you might have methods to get all genres, get a genre by ID, create a new genre, etc.

    @Autowired
    private SyncGenre syncGenre; // Assuming you have a GenreService to handle business logic
    // Example method to get all genres (to be implemented)
     @PostMapping("/sync")
     public void syncAllGenre() {
          syncGenre.syncGenres();
     }
}
