package org.humanas.guia.services.impl;

import lombok.RequiredArgsConstructor;
import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;
import org.humanas.guia.entities.Subject;
import org.humanas.guia.repositories.SubjectRepository;
import org.humanas.guia.services.SubjectService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository repository;
   /* public List<SubjectResponseDTO> getSubjectByMajor() {

    }*/
    @Override
    public SubjectResponseDTO save(SubjectRequestDTO requestDTO) {
        System.out.println(requestDTO.getName());
        Subject entityToSave=new Subject(requestDTO);
        Subject entitySaved=repository.save(entityToSave);
        System.out.println(entitySaved.getId() + " " + entitySaved.getName());
        return new SubjectResponseDTO(entitySaved);
    }
}
