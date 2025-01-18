package org.humanas.guia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity // Anotación para JPA
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String planDeEstudios;

    @Column(nullable = true)
    private String perfil_profesional;

    @Column(nullable = true)
    private String alcances_titulo;

    @Column
    private int anio_inicio;
}

