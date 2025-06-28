package com.yagyesh.movie.movieproducer;

import com.yagyesh.movie.movieproducer.contants.TMDBEndpoints;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.reactive.function.client.WebClient;

@EnableRetry
@Configuration
@SpringBootApplication
public class MovieProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieProducerApplication.class, args);
	}

	@Value("${rapid-api.host}")
	private String rapidApiHost;

	@Value("${rapid-api.key}")
	private String rapidApiKey;

	@Value("${spring.application.max-in-memory.size:524288}")
	private int maxInMemorySize;


	@Bean
	@Primary
	public WebClient getWebClient() {
		return WebClient.builder()
				.baseUrl("https://"+rapidApiHost)
				.defaultHeader("x-rapidapi-host", rapidApiHost)
				.defaultHeader("X-RapidAPI-Key", rapidApiKey)
				//This is to support larger responses, adjust as needed
				.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(maxInMemorySize))
				.defaultHeader("Content-Type", "application/json")
				.build();
	}

	@Bean
	@Qualifier("tmdbWebClient")
	public WebClient getWebClientTmdb() {
		return WebClient.builder()
				.baseUrl(TMDBEndpoints.BASE_URL)
				.defaultHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZjA1M2E1OTllYjA0MDQwMGVhYjkwZDExYjUzMTAwNyIsIm5iZiI6MTc0OTg5NjU0Ni4xMzMsInN1YiI6IjY4NGQ0ZDYyNjViZjU4MjM0ZDNmZTIwMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.vzhnLgOOxzOG3is-QkQBlU5YjFnQb4jFm-QGM62WN9U")
				//This is to support larger responses, adjust as needed
				.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(maxInMemorySize))
				.defaultHeader("accept", "application/json")
				.build();
	}
}
