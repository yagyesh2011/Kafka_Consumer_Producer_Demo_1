package com.yagyesh.movie.movieproducer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

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
}
