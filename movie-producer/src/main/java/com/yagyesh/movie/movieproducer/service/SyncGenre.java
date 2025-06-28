package com.yagyesh.movie.movieproducer.service;

import org.springframework.retry.annotation.Retryable;

public interface SyncGenre {
    void syncGenres();
}
