package org.humanas.guia.repositories;

import org.humanas.guia.dtos.FileTableDTO;
import org.humanas.guia.entities.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    @Query("SELECT new org.humanas.guia.dtos.FileTableDTO(f.id, f.name, s.name, s.quarter, f.type, f.uploadDate, f.url, f.month) " +
            "FROM File f LEFT JOIN Subject s ON f.id = s.id")
    List<FileTableDTO> getAllFilesForTable();

}
