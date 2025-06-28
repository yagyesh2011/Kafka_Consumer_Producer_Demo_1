package com.yagyesh.movie.movieconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
public class MovieConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieConsumerApplication.class, args);
	}

}
