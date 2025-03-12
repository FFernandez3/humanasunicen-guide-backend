package org.humanas.guia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.humanas.guia.entities.Major;
import org.humanas.guia.enums.FileMonth;
import org.humanas.guia.enums.FileType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FileTableDTO {
    private Long id;
    private Long subjectId;
    private List<Major> majors;
    private String name;
    private String subject;
    private Integer quarter;
    private FileType type;
    private LocalDate uploadDate;
    private String url;
    private FileMonth month;

    public FileTableDTO(Long id, Long subjectId, String name, String subject, Integer quarter, FileType type, LocalDate uploadDate, String url, FileMonth month) {
        this.id = id;
        this.subjectId = subjectId;
        this.name = name;
        this.subject = subject;
        this.quarter = quarter;
        this.type = type;
        this.uploadDate = uploadDate;
        this.url = url;
        this.month = month;
    }
}
