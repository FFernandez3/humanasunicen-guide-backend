package org.humanas.guia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

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
    private List<String> majorsIds;

}
