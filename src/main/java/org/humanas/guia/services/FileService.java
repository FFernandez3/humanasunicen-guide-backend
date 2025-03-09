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

    public void saveFile(MultipartFile file, String carrera, String catedra, FileType tipo, Integer anio, String llamado) throws IOException {
        // Solicitar el scope DRIVE en lugar de DRIVE_FILE
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
                .createScoped(Collections.singletonList(DriveScopes.DRIVE));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

        System.out.println("Credenciales: " + credentials);
        System.out.println("Request initializer: " + requestInitializer);
        System.out.println("File content type: " + file.getContentType());
        System.out.println("File name: " + file.getOriginalFilename());
        System.out.println("File size: " + file.getSize());

        // Construir el servicio Drive
        Drive service = new Drive.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer)
                .setApplicationName("Drive samples")
                .build();

        // Suponiendo que el archivo ya se subió a un directorio temporal o se ha preparado de alguna forma
        // Si deseas subir directamente el archivo sin guardarlo localmente, ajusta esta parte.
        String uploadDir = "uploads/";
        String filePath = uploadDir + file.getOriginalFilename();

        // Para este ejemplo, se asume que el archivo se ha guardado previamente en filePath.
        java.io.File localFile = new java.io.File(filePath);
        if (!localFile.exists()) {
            // Si el archivo no existe localmente, podrías crearlo temporalmente:
            localFile = convertMultipartFileToFile(file);
        }

        // Metadata para el archivo
        File fileMetadata = new File();
        fileMetadata.setName(file.getOriginalFilename());
        //fileMetadata.setWebViewLink(filePath);

        FileContent mediaContent = new FileContent(file.getContentType(), localFile);
        try {
            File newFile = service.files().create(fileMetadata, mediaContent)
                    .setFields("id, webViewLink")
                    .execute();
            System.out.println("File ID: " + newFile.getId());
            // Aquí puedes guardar la URL en la base de datos
            DocumentFile f = new DocumentFile();
            f.setName(newFile.getName());
            f.setUrl(newFile.getWebViewLink());
            f.setType(FileType.PARCIAL);
            f.setSubjectId(null);
            f.setUploadDate(null);
            f.setMonth("abril");
            fileRepository.save(f);

            //chequeamos que haya salido ok la cosa
            File fetchedFile = service.files().get("1aFBxRJeQctYPBqCvuS4jkVYbKiyD2r1Z")
                    .setFields("id, name, mimeType, webViewLink, permissions")
                    .execute();

            Permission permission = new Permission()
                    .setType("anyone")
                    .setRole("reader");

            service.permissions().create(fetchedFile.getId(), permission)
                    .execute();

            System.out.println("File ID: " + fetchedFile.getId());
            System.out.println("File Name: " + fetchedFile.getName());
            System.out.println("File MIME Type: " + fetchedFile.getMimeType());
            System.out.println("File Web View Link: " + fetchedFile.getWebViewLink());
            //return newFile.getId();
        } catch (GoogleJsonResponseException e) {
            System.err.println("Unable to upload file: " + e.getDetails());
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

    public void uploadBasic() throws IOException {
        // Load pre-authorized user credentials from the environment.
        // TODO(developer) - See https://developers.google.com/identity for
        // guides on implementing OAuth2 for your application.
        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault()
                .createScoped(Arrays.asList(DriveScopes.DRIVE));
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(
                credentials);

        System.out.println("Credenciales: " + credentials);

        // Build a new authorized API client service.
        Drive service = new Drive.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer)
                .setApplicationName("Drive samples")
                .build();
        // Upload file photo.jpg on drive.
        File fileMetadata = new File();
        fileMetadata.setName("photo.jpg");
        // File's content.
        java.io.File filePath = new java.io.File("files/photo.jpg");
        // Specify media type and file-path for file.
        FileContent mediaContent = new FileContent("image/jpeg", filePath);
        try {
            File file = service.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();
            System.out.println("File ID: " + file.getId());
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to upload file: " + e.getDetails());
            throw e;
        }
    }
}
