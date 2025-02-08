package org.humanas.guia.repositories;

import org.humanas.guia.entities.Major;
import org.humanas.guia.entities.Subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllByMajorsId(Long majorId);
    @Query("SELECT s.name from Subject s")
    List<String> getAllSubjectsNames();

    @Query("SELECT s.name from Subject s WHERE s.id = :idSubject")
    String getSubjectNameById(Long idSubject);

    @Query("SELECT s.majors from Subject s WHERE s.id = :subjectId")
    List<Major> getMajorsBy(Long subjectId);
}
