package org.humanas.guia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.humanas.guia.enums.FileType;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileTableDTO {
    private Long id;
    private String name;
    private String subject;
    private Integer quarter;
    private FileType type;
    private LocalDate uploadDate;
    private String url;
    private String month;
}
