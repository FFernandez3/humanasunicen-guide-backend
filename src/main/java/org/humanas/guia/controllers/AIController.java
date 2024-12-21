package org.humanas.guia.controllers;

import org.humanas.guia.dtos.AphorismDTO;
import org.humanas.guia.dtos.MajorDTO;
import org.humanas.guia.helpers.MockedData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ai")
public class AIController {

    @GetMapping //Andando
    public ResponseEntity<?> getAphorisms() {
        List<AphorismDTO> aphs = new MockedData().getAphorismsMocked();
        if (aphs.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(aphs);
    }

    @GetMapping("/aph") //Andando
    public ResponseEntity<?> getAphorism() {
        List<AphorismDTO> aphs = new MockedData().getAphorismsMocked();
        int random = (int) (Math.random() * aphs.size());
        if (aphs.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(aphs.get(random));
    }
}
