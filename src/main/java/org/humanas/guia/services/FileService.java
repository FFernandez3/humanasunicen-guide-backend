package org.humanas.guia.services;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;
import org.humanas.guia.dtos.FileMonthDTO;
import org.humanas.guia.dtos.FileTableDTO;
import org.humanas.guia.entities.DocumentFile;
import org.humanas.guia.entities.Major;
import org.humanas.guia.enums.FileMonth;
import org.humanas.guia.enums.FileType;
import org.humanas.guia.repositories.FileRepository;
import org.humanas.guia.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.services.drive.DriveScopes;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.util.*;
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

    private Drive generateDriveService() throws IOException {
        // Solicitar el scope DRIVE en lugar de DRIVE_FILE
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
                .createScoped(Collections.singletonList(DriveScopes.DRIVE));

        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
        // Construir el servicio Drive
        Drive serviceDrive = new Drive.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer)
                .setApplicationName("guiahumanas")
                .build();
        return serviceDrive;
    }

    private File createDriveFile(MultipartFile file, Drive serviceDrive) throws IOException {
        // archivo File temporal necesario para la signatura de los servicios posteriores
        java.io.File localFile = convertMultipartFileToFile(file);

        // Metadata para el archivo
        File fileMetadata = new File();
        fileMetadata.setName(file.getOriginalFilename());
        System.out.println("file content type: "+file.getContentType());
        FileContent content = new FileContent(file.getContentType(), localFile);

        try {
            //creamos el archivo de Drive
            File newFile = serviceDrive.files().create(fileMetadata, content)
                    .setFields("id, webViewLink")
                    .execute();

            System.out.println("File ID: " + newFile.getId());

            //Hacemos un fetch para asegurarnos que exista
            File fetchedFile = serviceDrive.files().get(newFile.getId())
                    .setFields("id, name, mimeType, webViewLink, permissions")
                    .execute();

            //preparamos el scope de los permisos
            Permission permission = new Permission()
                    .setType("anyone")
                    .setRole("reader");

            //seteamos los permisos al archivo recientemente creado y fetcheado
            serviceDrive.permissions().create(fetchedFile.getId(), permission)
                    .execute();
            System.out.println(fetchedFile.getWebViewLink());
            return fetchedFile;
        } catch (GoogleJsonResponseException e) {
            System.err.println("Unable to upload file: " + e.getDetails());
            throw e;
        }

    }

    public DocumentFile saveFile(MultipartFile file, String carrera, String catedra, FileType tipo, Integer anio, String llamado) throws IOException {
        Drive serviceDrive = generateDriveService();
        try {
            File driveFile = createDriveFile(file, serviceDrive);

            // persistimos en la base relacional con nuestros parametros
            DocumentFile f = new DocumentFile();
            f.setName(file.getOriginalFilename());
            f.setUrl(driveFile.getWebViewLink());
            f.setType(tipo);
            f.setSubjectId(null); //hay que traer el id de la catedra
            f.setUploadDate(null);
            f.setMonth(llamado);
           return fileRepository.save(f);
        } catch (Error e) {
            System.err.println("Unable to create file Drive");
            throw e;
        }
    }

    private java.io.File convertMultipartFileToFile(MultipartFile file) throws IOException {
        java.io.File convFile = new java.io.File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);
        return convFile;
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
