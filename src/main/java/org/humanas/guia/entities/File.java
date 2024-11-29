package org.humanas.guia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.humanas.guia.enums.FileType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Document(collection= "File")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class File {
    @MongoId
    private String id;
    private String name;
    private String url;
    private String subjectId;
    private FileType type;
    private LocalDate uploadDate;
}
