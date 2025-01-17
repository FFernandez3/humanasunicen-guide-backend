package org.humanas.guia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity // Anotación para JPA
@Document(collection = "Major") // Anotación para MongoDB
public class Major {

    @Id // Anotación JPA (Primary Key para PostgreSQL)
    @MongoId // Anotación MongoDB (ID en Mongo)
    @GeneratedValue(strategy = GenerationType.AUTO) // Solo para PostgreSQL
    private Long id;

    @Column(nullable = false) // En PostgreSQL, este campo es obligatorio
    private String name;

    @Column(nullable = true) // Opcional en PostgreSQL
    private String planDeEstudios;

    @Column(nullable = true) // Opcional en PostgreSQL
    private String perfil_profesional;

    @Column(nullable = true) // Opcional en PostgreSQL
    private String alcances_titulo;

    @Column // En PostgreSQL, este campo es obligatorio
    private int anio_inicio;
}

