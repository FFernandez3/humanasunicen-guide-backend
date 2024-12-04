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
    private List<String> majorsIds = new ArrayList<>();

    public SubjectResponseDTO(Subject entity){
        this.id=entity.getId();
        this.name= entity.getName();
        this.year=entity.getYear();
        this.majorsIds = entity.getMajorsIds() != null ? new ArrayList<>(entity.getMajorsIds()) : new ArrayList<>();
    }
}
