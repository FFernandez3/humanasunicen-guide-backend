package org.humanas.guia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false)
    private Integer quarter;
    @ManyToMany
    @JoinTable(
            name = "subject_correlatives",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "correlative_id")
    )
    private List<Subject> correlatives;
    @ManyToMany(mappedBy = "subjects")
    private List<Career> careers;

}
