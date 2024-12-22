package org.humanas.guia.services;

import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;

import java.util.List;

public interface SubjectService {
    SubjectResponseDTO save(SubjectRequestDTO requestDTO);
    List<SubjectResponseDTO> getSubjectsByMajorId(String idMajor);
}
