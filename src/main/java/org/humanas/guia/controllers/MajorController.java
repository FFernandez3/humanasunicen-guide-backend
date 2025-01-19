package org.humanas.guia.controllers;

import org.humanas.guia.dtos.MajorDTO;
import org.humanas.guia.entities.Major;
import org.humanas.guia.helpers.MockedData;
import org.humanas.guia.services.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/majors")
public class MajorController {
    @Autowired
    MajorService majorService;

    @GetMapping //Andando
    public ResponseEntity<List<Major>> getAllMajors() {
        //List<Major> majors = majorService.getAll();
        List<Major> majors = majorService.getAllMajors();
        if (majors.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(majors);
    }
}
