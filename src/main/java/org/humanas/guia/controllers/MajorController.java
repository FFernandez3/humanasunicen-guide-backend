package org.humanas.guia.controllers;

import org.humanas.guia.dtos.MajorDTO;
import org.humanas.guia.entities.Major;
import org.humanas.guia.helpers.MockedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/majors")
public class MajorController {

    //@Autowired
    //MajorService majorService;

    @GetMapping //Andando
    public ResponseEntity<List<MajorDTO>> getAllMajors() {
        //List<Major> majors = majorService.getAll();
        List<MajorDTO> majors = new MockedData().getMajorsMocked();
        if (majors.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(majors);
    }
}