package org.humanas.guia.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String officialPage;

    //@Column(nullable = true)
    //private String perfil_profesional;

    //@Column(nullable = true)
    //private String alcances_titulo;

    @Column
    private int anio_inicio;

    @ManyToMany(mappedBy = "majors")
    private List<Subject> subjects;

    public Major(String name, String officialPage, int anio_inicio) {
        this.name = name;
        this.officialPage = officialPage;
        this.anio_inicio = anio_inicio;
        this.subjects=new ArrayList<>();
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        if (!subject.getMajors().contains(this)) {
            subject.getMajors().add(this);
        }
    }
}

