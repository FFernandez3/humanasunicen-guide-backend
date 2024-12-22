package org.humanas.guia.mappers;

import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;
import org.humanas.guia.entities.Subject;
import java.util.List;


public class SubjectMapper {
    public static SubjectResponseDTO subjectToSubjectResponseDTO(Subject entity) {
        return new SubjectResponseDTO(entity.getId(), entity.getName(), entity.getYear(), entity.getMajorsIds());
    }
    public static List<SubjectResponseDTO> subjectListToSubjectResponseDTOList(List<Subject> entities) {
        return entities.stream().map(
                SubjectMapper::subjectToSubjectResponseDTO
        ).toList();
    }

    public static Subject subjectRequestDTOToSubject(SubjectRequestDTO dto) {
        return new Subject(dto.getName(), dto.getYear(), dto.getMajorsIds());
    }



}
