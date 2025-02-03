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
    @GetMapping("/years/{idSubject}")
    public ResponseEntity<List<SubjectYearDTO>> getAllYearsForSubject(@PathVariable Long idSubject){
        List<SubjectYearDTO> yearsOfSubject = service.getAllYearsForSubject(idSubject);
        return ResponseEntity.ok(yearsOfSubject);
    }
    @PostMapping
    public ResponseEntity<SubjectResponseDTO>save(@RequestBody SubjectRequestDTO requestDTO){
        return ResponseEntity.status(201).body(service.save(requestDTO));
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllSubjectsNames(){
        return ResponseEntity.ok(this.service.getAllSubjectsNames());
    }

    @GetMapping("/{idSubject}")
    public ResponseEntity<String> getSubjectNameById(@PathVariable Long idSubject){
        return ResponseEntity.ok(this.service.getSubjectNameById(idSubject));
    }
}
