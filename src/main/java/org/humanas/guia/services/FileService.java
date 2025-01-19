package org.humanas.guia.services;

import org.humanas.guia.entities.File;
import org.humanas.guia.repositories.FileRepository;
import org.humanas.guia.repositories.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileRepository filerepo;

    public List<File> getAllFiles(){
        return this.filerepo.findAll();
    }
}
