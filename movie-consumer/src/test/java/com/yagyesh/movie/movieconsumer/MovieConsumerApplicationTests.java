package com.yagyesh.movie.movieconsumer;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class MovieConsumerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testTmdbConnection() throws IOException {

		AsyncHttpClient client = new DefaultAsyncHttpClient();
        try {
			client.prepare("GET", "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1")
					.setHeader("accept", "application/json")
					.setHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZjA1M2E1OTllYjA0MDQwMGVhYjkwZDExYjUzMTAwNyIsIm5iZiI6MTc0OTg5NjU0Ni4xMzMsInN1YiI6IjY4NGQ0ZDYyNjViZjU4MjM0ZDNmZTIwMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.vzhnLgOOxzOG3is-QkQBlU5YjFnQb4jFm-QGM62WN9U")
					.execute()
					.toCompletableFuture()
					.thenAccept((response) -> {
						response.getHeaders().forEach(header -> {
							System.out.println(header.getKey() + ": " + header.getValue());
						});
						System.out.println("Response Body: " + response.getResponseBody());
					})
					.join();
        } finally {
			client.close();
		}

    }

}
