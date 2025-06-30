package com.yagyesh.movie.movieproducer.util;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface EntityFetcher<K,V> {

    K getEntityWithId(V v);

    List<K> getTopRatedEntities(int page) throws JsonProcessingException;

    List<String> getPopularEntities(int page);

    List<Integer> getEntitiesIds(int page) throws JsonProcessingException;
}
