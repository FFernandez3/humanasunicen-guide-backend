package org.humanas.guia.services;

import lombok.RequiredArgsConstructor;
import org.humanas.guia.dtos.AphorismDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AIService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8000";

    public AphorismDTO textGeneratedByAIAgent() {
        // GET request
        ResponseEntity<?> response = restTemplate.getForEntity(
                baseUrl + "/getAphorism", // Full URL
                Object.class          // Response type
        );
        LinkedHashMap resp = (LinkedHashMap) response.getBody();
        AphorismDTO aph = new AphorismDTO((String) resp.get("author"), (String) resp.get("aphorism"));
        //AphorismDTO aph = new AphorismDTO(resp.author, resp.aphorism);
        return aph;
    }

}
