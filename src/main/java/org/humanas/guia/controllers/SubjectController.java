package org.humanas.guia.controllers;

import lombok.RequiredArgsConstructor;
import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;
import org.humanas.guia.dtos.SubjectYearDTO;
import org.humanas.guia.entities.Subject;
import org.humanas.guia.services.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService service;

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects(){
        List<Subject> subjects = service.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }
    @GetMapping("/major/{idMajor}")
    public ResponseEntity<List<SubjectResponseDTO>> getSubjectsByMajor( @PathVariable Long idMajor){
        List<SubjectResponseDTO> list = service.getSubjectsByMajorId(idMajor);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/years/{idSubject}")
    public ResponseEntity<List<SubjectYearDTO>> getAllYearsForSubject(@PathVariable Long idSubject){
        List<SubjectYearDTO> yearsOfSubject = service.getAllYearsForSubject(idSubject);
        return ResponseEntity.ok(yearsOfSubject);
    }
    @PostMapping
    public ResponseEntity<SubjectResponseDTO>save(@RequestBody SubjectRequestDTO requestDTO){
        return ResponseEntity.status(201).body(service.save(requestDTO));
    }
}
