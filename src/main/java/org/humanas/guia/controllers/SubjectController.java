package org.humanas.guia.controllers;

import lombok.RequiredArgsConstructor;
import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;
import org.humanas.guia.services.impl.SubjectServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectServiceImpl service;

  /*  @GetMapping("/major/{idMajor}")
    public ResponseEntity<List<SubjectResponseDTO>> getSubjectByMajor(){
        List<SubjectResponseDTO>list=service.getSubjectByMajor();
        return ResponseEntity.ok(list);
    }*/
    @PostMapping
    public ResponseEntity<SubjectResponseDTO>save(@RequestBody SubjectRequestDTO requestDTO){
        return ResponseEntity.status(201).body(service.save(requestDTO));
    }
}
