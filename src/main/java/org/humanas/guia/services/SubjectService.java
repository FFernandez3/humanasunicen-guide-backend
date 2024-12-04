package org.humanas.guia.services;

import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;

public interface SubjectService {
    SubjectResponseDTO save(SubjectRequestDTO requestDTO);
}
