package com.yagyesh.movie.movieproducer.controller;

import com.yagyesh.movie.movieproducer.service.SyncApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movie/sync")
public class MovieController {

    private final SyncApiService syncApiService;

    @Autowired
    public MovieController(SyncApiService syncApiService) {
        // Constructor can be used for any initialization if needed
        this.syncApiService = syncApiService;
    }
    // Define endpoints for movie-related operations here
    // For example:
     @PostMapping ("/topRated/")
     public void sync250Movies() {
            // Call the synchronizer to start the synchronization process
         syncApiService.syncTopRated();
     }

}
