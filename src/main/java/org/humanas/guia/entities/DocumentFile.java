package org.humanas.guia.entities;

import jakarta.persistence.*;
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
@Entity
public class DocumentFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String url;
    private Long subjectId;
    private FileType type;
    private LocalDate uploadDate;
    @Column(nullable = false)
    private String month;
}
