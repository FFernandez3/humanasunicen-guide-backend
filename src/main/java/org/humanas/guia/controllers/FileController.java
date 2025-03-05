package org.humanas.guia.controllers;

import lombok.RequiredArgsConstructor;
import org.humanas.guia.dtos.FileMonthDTO;
import org.humanas.guia.dtos.FileTableDTO;
import org.humanas.guia.dtos.FileTypeDTO;
import org.humanas.guia.entities.DocumentFile;
import org.humanas.guia.enums.FileType;
import org.humanas.guia.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping
    public ResponseEntity<List<DocumentFile>> getAllFiles(){
        List<DocumentFile> files = this.fileService.getAllFiles();
        return ResponseEntity.ok(files);
    }

    @GetMapping("/types")
    public ResponseEntity<List<String>> getTypesOfFiles(){
        List<String> types = this.fileService.getTypesOfFiles();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/months")
    public ResponseEntity<List<FileMonthDTO>> getMonthsOfFiles(){
        List<FileMonthDTO> months = this.fileService.getMonthsOfFiles();
        return ResponseEntity.ok(months);
    }

    @PostMapping
    public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile file,
                                      @RequestParam("carrera") String carrera,
                                      @RequestParam("catedra") String catedra,
                                      @RequestParam("tipo") FileType tipo,
                                      @RequestParam(value = "anio", required = false) Integer anio,
                                      @RequestParam(value = "llamado", required = false) String llamado) throws IOException {
        String response = this.fileService.saveFile(file, carrera, catedra, tipo, anio, llamado);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/table")
    public ResponseEntity<List<FileTableDTO>> getAllFilesForTable(){
        return ResponseEntity.ok(this.fileService.getAllFilesForTable());
    }
}
