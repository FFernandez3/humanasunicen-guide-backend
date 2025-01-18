package org.humanas.guia.services.impl;

import lombok.RequiredArgsConstructor;
import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;
import org.humanas.guia.entities.Major;
import org.humanas.guia.entities.Subject;
import org.humanas.guia.mappers.SubjectMapper;
import org.humanas.guia.repositories.MajorRepository;
import org.humanas.guia.repositories.SubjectRepository;
import org.humanas.guia.services.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository repository;
    private final MajorRepository majorRepository;

    @Override
    public SubjectResponseDTO save(SubjectRequestDTO requestDTO) {
        List<Major> majors = majorRepository.findAllById(requestDTO.getMajorsIds());
        Subject entityToSave = SubjectMapper.subjectRequestDTOToSubject(requestDTO, majors);
        Subject entitySaved = repository.save(entityToSave);
        return SubjectMapper.subjectToSubjectResponseDTO(entitySaved);
    }

    @Override
    public List<SubjectResponseDTO> getSubjectsByMajorId(Long idMajor) {
        List<Subject> subjects = repository.findAllByMajorsId(idMajor);
        return SubjectMapper.subjectListToSubjectResponseDTOList(subjects);
    }
}
