package org.humanas.guia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.humanas.guia.dtos.SubjectRequestDTO;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Subject")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subject {
   @MongoId
    private String id;
    private String name;
    private Integer year;
    private List<String> majorsIds = new ArrayList<>();

    public Subject(SubjectRequestDTO requestDTO){
        this.name=requestDTO.getName();
        this.year=requestDTO.getYear();
        this.majorsIds = requestDTO.getMajorsIds() != null ? new ArrayList<>(requestDTO.getMajorsIds()) : new ArrayList<>();
    }

}
