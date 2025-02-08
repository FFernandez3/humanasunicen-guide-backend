package org.humanas.guia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false)
    private Integer quarter;
    @ManyToMany
    @JoinTable(
            name = "major_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "major_id")
    )
    private List<Major> majors = new ArrayList<>();

    public Subject(String name, int year, int quarter, List<Major> majors) {
        this.name = name;
        this.year = year;
        this.quarter = quarter;
        this.majors=majors;
    }

}
