package org.humanas.guia.controllers;

import lombok.RequiredArgsConstructor;
import org.humanas.guia.dtos.MajorDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;
import org.humanas.guia.entities.Major;
import org.humanas.guia.helpers.MockedData;
import org.humanas.guia.services.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/majors")
public class MajorController {
    private final MajorService majorService;

    @GetMapping //Andando
    public ResponseEntity<List<Major>> getAllMajors() {
        //List<Major> majors = majorService.getAll();
        List<Major> majors = majorService.getAllMajors();
        if (majors.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(majors);
    }
    @GetMapping("subjects/{majorId}")
    public ResponseEntity<List<SubjectResponseDTO>>getSubjectsByMajorId(@PathVariable Long majorId){
        return ResponseEntity.ok(majorService.getSubjectsByMajorId(majorId)) ;
    }
}
