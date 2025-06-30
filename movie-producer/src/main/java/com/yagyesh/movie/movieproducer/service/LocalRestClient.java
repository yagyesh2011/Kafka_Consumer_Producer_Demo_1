package com.yagyesh.movie.movieproducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class LocalRestClient {
    private WebClient client;

    public void setClient(WebClient client) {
        this.client = client;
    }

    public List<Integer> getTopRatedIds(String url) throws JsonProcessingException {
        String response = client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(Retry.backoff(5, java.time.Duration.ofSeconds(15))
                        .filter(throwable -> {
                            if (throwable instanceof WebClientRequestException ex) {
                                log.warn("Retrying due to RuntimeException: {}", ex.getMessage());
                                log.warn("Error Code: {}", ex.getMessage());
                            } else {
                                log.error("Unexpected error: {}", throwable.getMessage());
                                log.error("Error Type: {}", throwable.getClass().getName());
                                return false;
                            }
                            return true;
                        }))
                .onErrorResume((ex) -> {
                    log.error("Error fetching data from URL: {}. Error: {}", url, ex.getMessage());
                    return Mono.empty();
                })
                .block();
        if (response == null || response.isEmpty()) {
            log.warn("No data received from URL: {}", url);
            return List.of();
        }
        log.info("Response from URL {}: {}", url, response);
        List<Integer> ids = new ArrayList<>(250);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        jsonNode = jsonNode.get("results");
        if (jsonNode.isArray()) {
            for(JsonNode node : jsonNode) {
                if (node.has("id")) {
                    int id = node.get("id").asInt();
                    ids.add(id);
                } else {
                    log.warn("No 'id' field found in node: {}", node);
                }
            }
        } else {
            log.warn("Expected an array response but got: {}", jsonNode);
        }
        return ids;
    }

    public List<String> getTop250Ids(String url) throws JsonProcessingException {
        String response = client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        if (response == null || response.isEmpty()) {
            log.warn("No data received from URL: {}", url);
            return List.of();
        }
        log.info("Response from URL {}: {}", url, response);
        List<String> ids = new ArrayList<>(250);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        if (jsonNode.isArray()) {
            for(JsonNode node : jsonNode) {
                if (node.has("id")) {
                    String id = node.get("id").asText();
                    ids.add(id);
                } else {
                    log.warn("No 'id' field found in node: {}", node);
                }
            }
        } else {
            log.warn("Expected an array response but got: {}", jsonNode);
        }
        return ids;
    }

    public Message getWithId(String url, String id, Message.Builder builder, String params) {
        String jsonString = client.get()
                .uri(url + "/" + id + "?"+ params)
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(Retry.backoff(5, java.time.Duration.ofSeconds(15))
                        .filter(throwable -> {
                            if (throwable instanceof WebClientResponseException ex) {
                                log.warn("Retrying due to RuntimeException: {}", ex.getMessage());
                                log.warn("Error Code: {}", ex.getStatusCode());
                            } else {
                                log.error("Unexpected error: {}", throwable.getMessage());
                                log.error("Error Type: {}", throwable.getClass().getName());
                                return false;
                            }
                            return true;
                        }))
                .onErrorResume((ex) -> {
                    log.error("Error fetching data from URL: {} with ID: {}. Error: {}", url, id, ex.getMessage());
                    return Mono.empty();
                })
                .block();
        JsonFormat.Parser parser = JsonFormat.parser();
        if (jsonString == null || jsonString.isEmpty()) {
            log.warn("No data received from URL: {}", url + "/" + id);
            return null;
        }
        log.info("Response from URL {}: {}", url + "/" + id, jsonString);
        try {
            parser.merge(jsonString, builder);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
        return builder.build();
    }
}
