package org.humanas.guia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.humanas.guia.entities.Subject;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class SubjectResponseDTO {
    private String id;
    private String name;
    private Integer year;
    private List<String> majorsIds;

}
