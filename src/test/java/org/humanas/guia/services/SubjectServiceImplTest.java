package org.humanas.guia.services;

import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;
import org.humanas.guia.entities.Major;
import org.humanas.guia.entities.Subject;
import org.humanas.guia.mappers.SubjectMapper;
import org.humanas.guia.repositories.MajorRepository;
import org.humanas.guia.repositories.SubjectRepository;
import org.humanas.guia.services.impl.SubjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {

    @InjectMocks
    private SubjectServiceImpl subjectService;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private MajorRepository majorRepository;

    @Test
    void save_validRequest_returnSavedSubjectResponse() {
        // Arrange
        List<Long> majorsIds = List.of(1L);
        SubjectRequestDTO requestDTO = new SubjectRequestDTO("Psicologia", 2, 1, majorsIds);
        Major major = new Major(1L, "Psicologia", "Plan A", "Perfil A", "Alcance A", 2020);
        List<Major> majors = List.of(major);

        Subject entityToSave = new Subject("Psicologia", 2, 1, majors);
        entityToSave.setId(1L);

        SubjectResponseDTO responseDTO = new SubjectResponseDTO(1L, "Psicologia", 2, 1, majorsIds);

        when(majorRepository.findAllById(majorsIds)).thenReturn(majors);
        when(subjectRepository.save(entityToSave)).thenReturn(entityToSave);

        try (MockedStatic<SubjectMapper> mockedMapper = mockStatic(SubjectMapper.class)) {
            mockedMapper.when(() -> SubjectMapper.subjectRequestDTOToSubject(requestDTO, majors))
                    .thenReturn(entityToSave);
            mockedMapper.when(() -> SubjectMapper.subjectToSubjectResponseDTO(entityToSave))
                    .thenReturn(responseDTO);

            // Act
            SubjectResponseDTO result = subjectService.save(requestDTO);

            // Assert
            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(1L);
            assertThat(result.getName()).isEqualTo("Psicologia");


            verify(majorRepository, times(1)).findAllById(majorsIds);
            verify(subjectRepository, times(1)).save(entityToSave);
            mockedMapper.verify(() -> SubjectMapper.subjectRequestDTOToSubject(requestDTO, majors), times(1));
            mockedMapper.verify(() -> SubjectMapper.subjectToSubjectResponseDTO(entityToSave), times(1));
        }
    }

    @Test
    void getSubjectsByMajorId_validId_returnListOfSubjects() {
        // Arrange
        Long majorId = 1L;
        Major major = new Major(majorId, "Psicologia", "Plan A", "Perfil A", "Alcance A", 2020);
        List<Subject> subjects = List.of(
                new Subject("Didactica", 2, 1, List.of(major)),
                new Subject("Historia", 3, 1, List.of(major))
        );
        List<SubjectResponseDTO> responseDTOs = List.of(
                new SubjectResponseDTO(1L, "Didactica", 2, 1, List.of(majorId)),
                new SubjectResponseDTO(2L, "Historia", 3, 1, List.of(majorId))
        );

        when(subjectRepository.findAllByMajorsId(majorId)).thenReturn(subjects);

        try (MockedStatic<SubjectMapper> mockedMapper = mockStatic(SubjectMapper.class)) {
            mockedMapper.when(() -> SubjectMapper.subjectListToSubjectResponseDTOList(subjects))
                    .thenReturn(responseDTOs);

            // Act
            List<SubjectResponseDTO> result = subjectService.getSubjectsByMajorId(majorId);

            // Assert
            assertThat(result).isNotNull().hasSize(2);
            assertThat(result).containsExactlyElementsOf(responseDTOs);

            verify(subjectRepository, times(1)).findAllByMajorsId(majorId);
            mockedMapper.verify(() -> SubjectMapper.subjectListToSubjectResponseDTOList(subjects), times(1));
        }
    }
}



