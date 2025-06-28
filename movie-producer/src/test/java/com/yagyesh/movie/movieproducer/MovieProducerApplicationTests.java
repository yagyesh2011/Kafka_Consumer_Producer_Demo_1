package com.yagyesh.movie.movieproducer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
class MovieProducerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test_connection() {
		WebClient webClient = WebClient.builder().baseUrl("https://api.themoviedb.org")
				.defaultHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZjA1M2E1OTllYjA0MDQwMGVhYjkwZDExYjUzMTAwNyIsIm5iZiI6MTc0OTg5NjU0Ni4xMzMsInN1YiI6IjY4NGQ0ZDYyNjViZjU4MjM0ZDNmZTIwMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.vzhnLgOOxzOG3is-QkQBlU5YjFnQb4jFm-QGM62WN9U")
				.build();
		webClient.get()
				.uri("/3/movie/popular")
				.retrieve()
				.bodyToMono(String.class)
				.subscribe(response -> {
					System.out.println("Response: " + response);
				}, error -> {
					System.err.println("Error: " + error.getMessage());
				});

	}
}
