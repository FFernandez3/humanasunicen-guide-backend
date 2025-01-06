package org.humanas.guia.controllers;

import org.humanas.guia.dtos.AphorismDTO;
import org.humanas.guia.dtos.MajorDTO;
import org.humanas.guia.helpers.MockedData;
import org.humanas.guia.services.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AIController {
    private AIService AIservice = new AIService();

    @GetMapping //Andando
    public ResponseEntity<?> getAphorisms() {
        List<AphorismDTO> aphs = new MockedData().getAphorismsMocked();
        if (aphs.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(aphs);
    }

    @GetMapping("/getAphorism") //Andando
    public ResponseEntity<?> getTextGeneratedByAIAgent() {
        AphorismDTO aph = AIservice.textGeneratedByAIAgent();
        return ResponseEntity.ok(aph);
//        List<AphorismDTO> aphs = new MockedData().getAphorismsMocked();
//        int random = (int) (Math.random() * aphs.size());
//        if (aphs.isEmpty()) {
//            return  ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(aphs.get(random));
    }
}
