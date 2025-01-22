package org.humanas.guia.controllers;

import lombok.RequiredArgsConstructor;
import org.humanas.guia.dtos.FileMonthDTO;
import org.humanas.guia.dtos.FileTypeDTO;
import org.humanas.guia.entities.File;
import org.humanas.guia.services.FileService;
import org.humanas.guia.services.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping
    public ResponseEntity<List<File>> getAllFiles(){
        List<File> files = this.fileService.getAllFiles();
        return ResponseEntity.ok(files);
    }

    @GetMapping("/types")
    public ResponseEntity<List<FileTypeDTO>> getTypesOfFiles(){
        List<FileTypeDTO> types = this.fileService.getTypesOfFiles();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/months")
    public ResponseEntity<List<FileMonthDTO>> getMonthsOfFiles(){
        List<FileMonthDTO> months = this.fileService.getMonthsOfFiles();
        return ResponseEntity.ok(months);
    }
}
