package com.yagyesh.movie.movieproducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class LocalRestClient {
    private final WebClient client;

    @Autowired
    public LocalRestClient(WebClient client){
        this.client = client;
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

    public <K> K getWithId(String url, String id, Class<K> response) {
        String jsonString = client.get()
                .uri(url + "/" + id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
