package org.humanas.guia.repositories;

import org.humanas.guia.entities.Major;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("jpa")
@Repository
public interface MajorJpaRepository extends JpaRepository<Major, Long> {
    // Métodos específicos de JPA, si necesitas alguno
}

