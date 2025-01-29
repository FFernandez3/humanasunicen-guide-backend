package org.humanas.guia.services;

import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;
import org.humanas.guia.dtos.SubjectYearDTO;
import org.humanas.guia.entities.Subject;

import java.util.List;

public interface SubjectService {
    SubjectResponseDTO save(SubjectRequestDTO requestDTO);
    List<SubjectResponseDTO> getSubjectsByMajorId(Long idMajor);
    List<Subject> getAllSubjects();
    List<SubjectYearDTO> getAllYearsForSubject(Long idSubject);
    List<String> getAllSubjectsNames();
}
