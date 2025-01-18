package org.humanas.guia.controllers;

import lombok.RequiredArgsConstructor;
import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;
import org.humanas.guia.services.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService service;

    @GetMapping("/major/{idMajor}")
    public ResponseEntity<List<SubjectResponseDTO>> getSubjectsByMajor( @PathVariable Long idMajor){
        List<SubjectResponseDTO> list=service.getSubjectsByMajorId(idMajor);
        return ResponseEntity.ok(list);
    }
    @PostMapping
    public ResponseEntity<SubjectResponseDTO>save(@RequestBody SubjectRequestDTO requestDTO){
        return ResponseEntity.status(201).body(service.save(requestDTO));
    }
}
