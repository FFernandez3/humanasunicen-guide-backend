package org.humanas.guia.services;

import org.humanas.guia.repositories.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MajorService {

    @Qualifier("majorJpaRepositoryImpl")
    @Autowired
    private final MajorRepository majorRepository;

    public MajorService(@Qualifier("majorJpaRepositoryImpl") MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    // Usa majorRepository para operaciones de datos
}
