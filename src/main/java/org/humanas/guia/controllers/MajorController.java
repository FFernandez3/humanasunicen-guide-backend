package org.humanas.guia.controllers;

import org.humanas.guia.dtos.MajorDTO;
import org.humanas.guia.entities.Major;
import org.humanas.guia.helpers.MockedData;
import org.humanas.guia.services.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/name/{majorName}")
    public ResponseEntity<Major> getMajorName(@PathVariable String majorName){
        Major major = majorService.getMajorName(majorName);
        return ResponseEntity.ok(major);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getMajorsNames(){
        List<String> majors = majorService.getMajorsNames();
        return ResponseEntity.ok(majors);
    }

}
