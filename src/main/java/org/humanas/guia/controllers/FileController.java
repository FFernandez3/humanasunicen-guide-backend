package org.humanas.guia.controllers;

import lombok.RequiredArgsConstructor;
import org.humanas.guia.dtos.FileMonthDTO;
import org.humanas.guia.dtos.FileTableDTO;
import org.humanas.guia.dtos.FileTypeDTO;
import org.humanas.guia.entities.DocumentFile;
import org.humanas.guia.enums.FileMonth;
import org.humanas.guia.enums.FileType;
import org.humanas.guia.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
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

    @PostMapping("/save")
    public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile file,
                                      @RequestParam("catedra") String catedraId,
                                      @RequestParam("tipo") FileType tipo,
                                      @RequestParam(value = "anio", required = false) Integer anio,
                                      @RequestParam(value = "llamado", required = false) FileMonth llamado) throws IOException {
        System.out.println(file);
        System.out.println(catedraId);
        System.out.println(tipo);
        System.out.println(anio);
        System.out.println(llamado);


        //DocumentFile f = this.fileService.saveFile(file, catedraId, tipo, anio, llamado);
//        if (f.getId() != null) {
//            return ResponseEntity.ok("archivo creado y persistido exitosamente");
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
        return ResponseEntity.ok("si");
    }

    @GetMapping("/table")
    public ResponseEntity<List<FileTableDTO>> getAllFilesForTable(){
        return ResponseEntity.ok(this.fileService.getAllFilesForTable());
    }
}
