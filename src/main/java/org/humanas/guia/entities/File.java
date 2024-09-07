package org.humanas.guia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    //por ahi cambiar esto a un enum
    private  String documentType;
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
}
