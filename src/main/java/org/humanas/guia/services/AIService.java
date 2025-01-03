package org.humanas.guia.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8000";

    public String textGeneratedByAIAgent() {
        // GET request
        ResponseEntity<String> response = restTemplate.getForEntity(
                baseUrl + "/test", // Full URL
                String.class          // Response type
        );

        // Return the response body
        return response.getBody();
    }

}
