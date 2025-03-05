package org.humanas.guia.services;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import org.humanas.guia.dtos.FileMonthDTO;
import org.humanas.guia.dtos.FileRequestDTO;
import org.humanas.guia.dtos.FileTableDTO;
import org.humanas.guia.dtos.FileTypeDTO;
import org.humanas.guia.entities.DocumentFile;
import org.humanas.guia.entities.Major;
import org.humanas.guia.enums.FileMonth;
import org.humanas.guia.enums.FileType;
import org.humanas.guia.repositories.FileRepository;
import org.humanas.guia.repositories.MajorRepository;
import org.humanas.guia.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.services.drive.DriveScopes;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public List<DocumentFile> getAllFiles(){
        return this.fileRepository.findAll();
    }

    public List<String> getTypesOfFiles() {
        //List<FileTypeDTO> fileTypes = new ArrayList<>();
        List<String> types = Arrays.stream(FileType.values()) // values() --> Devuelve un array con todos los valores del enum en el orden en que fueron declarados.
        // Arrays.stream --> convierte el array en un Stream para trabajar con él de forma funcional.
                .map(Enum::name) // Obtiene el nombre del enum como String
                .collect(Collectors.toList()); // Convierte el Stream resultante en una lista

//        for (String type : types){
//            FileTypeDTO fTD = new FileTypeDTO(type);
//            fileTypes.add(fTD);
//        }
        //return fileTypes;
        return types;
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

    public String saveFile(MultipartFile file, String carrera, String catedra, FileType tipo, Integer anio, String llamado) throws IOException {
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
                .createScoped(Arrays.asList(DriveScopes.DRIVE_FILE));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
                credentials);

        System.out.println("cred: " + credentials);
        System.out.println("req init: " + requestInitializer);
        System.out.println("file: " + file.getContentType());
        System.out.println("file: " + file.getName());
        System.out.println("file: " + file.getSize());
        System.out.println("file: " + file.getOriginalFilename());

        // Build a new authorized API client service.
        Drive service = new Drive.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer)
                .setApplicationName("Drive samples")
                .build();

        String uploadDir = "uploads/";
        String filePath = uploadDir + file.getOriginalFilename();

        File fileMetaData = new File();
        fileMetaData.setName(file.getOriginalFilename());

        FileContent mediaContent = new FileContent("application/pdf", new java.io.File(filePath));
        try {
            File newFile = service.files().create(fileMetaData, mediaContent)
                    .setFields("id")
                    .execute();
            System.out.println("File ID: " + newFile.getId());
            if (!newFile.isEmpty()){
                DocumentFile f = new DocumentFile();
                f.setName(newFile.getName());
                f.setUrl(filePath);
                fileRepository.save(f);
            }

            return newFile.getId();
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to upload file: " + e.getDetails());
            throw e;
        }
    }

    public List<FileTableDTO> getAllFilesForTable(){
        List<FileTableDTO> resp = this.fileRepository.getAllFilesForTable();
        for (FileTableDTO fDto : resp){
            List<Major> majorsBySubject = this.subjectRepository.getMajorsBy(fDto.getId());
            fDto.setMajors(majorsBySubject);
        }
        return resp;
    }
}
