package com.yagyesh.movie.movieproducer.service;

public interface SyncApiService {

    void syncTop250(String type);

    void syncMostPopular(String type);
}
