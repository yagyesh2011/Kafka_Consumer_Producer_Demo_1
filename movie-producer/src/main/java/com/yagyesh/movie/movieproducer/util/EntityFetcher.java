package com.yagyesh.movie.movieproducer.util;

import java.util.List;

public interface EntityFetcher<K,V> {

    K getEntityWithId(V v);

    List<K> getTop250Entities();

    List<String> getTop250EntitiesIds();

}
