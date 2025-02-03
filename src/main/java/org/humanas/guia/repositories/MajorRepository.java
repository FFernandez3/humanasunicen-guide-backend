package org.humanas.guia.repositories;

import org.humanas.guia.entities.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
    @Query("SELECT m FROM Major m JOIN FETCH m.subjects WHERE m.id = :majorId")
    Optional<Major> findByIdWithSubjects(@Param("majorId") Long majorId);
}

