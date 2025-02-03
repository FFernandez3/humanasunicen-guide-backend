package org.humanas.guia.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.humanas.guia.dtos.SubjectResponseDTO;
import org.humanas.guia.entities.Major;
import org.humanas.guia.mappers.SubjectMapper;
import org.humanas.guia.repositories.MajorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;

    public List<Major> getAllMajors(){
        return this.majorRepository.findAll();
    }

    public List<SubjectResponseDTO> getSubjectsByMajorId(Long majorId) {
        Major major = majorRepository.findByIdWithSubjects(majorId)
                    .orElseThrow(() -> new EntityNotFoundException("Major not found with id: " + majorId));

        return major.getSubjects().stream()
                .map(SubjectMapper::subjectToSubjectResponseDTO)
                .toList();
    }

}
