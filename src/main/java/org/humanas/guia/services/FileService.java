package org.humanas.guia.services;

import org.humanas.guia.dtos.FileMonthDTO;
import org.humanas.guia.dtos.FileRequestDTO;
import org.humanas.guia.dtos.FileTypeDTO;
import org.humanas.guia.entities.File;
import org.humanas.guia.enums.FileMonth;
import org.humanas.guia.enums.FileType;
import org.humanas.guia.repositories.FileRepository;
import org.humanas.guia.repositories.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    @Autowired
    private FileRepository filerepo;

    public List<File> getAllFiles(){
        return this.filerepo.findAll();
    }

    public List<FileTypeDTO> getTypesOfFiles() {
        List<FileTypeDTO> fileTypes = new ArrayList<>();
        List<String> types = Arrays.stream(FileType.values()) // values() --> Devuelve un array con todos los valores del enum en el orden en que fueron declarados.
        // Arrays.stream --> convierte el array en un Stream para trabajar con él de forma funcional.
                .map(Enum::name) // Obtiene el nombre del enum como String
                .collect(Collectors.toList()); // Convierte el Stream resultante en una lista

        for (String type : types){
            FileTypeDTO fTD = new FileTypeDTO(type);
            fileTypes.add(fTD);
        }
        return fileTypes;
    }

    public List<FileMonthDTO> getMonthsOfFiles(){ //será necesario agregar validación para verificar que el file sea != RESUMEN?
        List<FileMonthDTO> fileMonths = new ArrayList<>();
        List<String> months = Arrays.stream(FileMonth.values()) // values() --> Devuelve un array con todos los valores del enum en el orden en que fueron declarados.
                // Arrays.stream --> convierte el array en un Stream para trabajar con él de forma funcional.
                .map(Enum::name) // Obtiene el nombre del enum como String
                .collect(Collectors.toList()); // Convierte el Stream resultante en una lista

        for (String m : months){
            FileMonthDTO fMD = new FileMonthDTO(m);
            fileMonths.add(fMD);
        }
        return fileMonths;
    }

    public String saveFile(MultipartFile file, String carrera, String catedra, FileType tipo, Integer anio, String llamado){
        System.out.println("el archivo subido: " + file.getName());
        if (!file.getName().equals("")) return "todo piolita";
        else return "nada salio como esperado";
    }
}
