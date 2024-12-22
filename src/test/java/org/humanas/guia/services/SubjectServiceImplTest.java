package org.humanas.guia.services;

import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;
import org.humanas.guia.entities.Subject;
import org.humanas.guia.mappers.SubjectMapper;
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


    @Test
    void save_validRequest_returnSavedSubjectResponse_staticMapper() {
        // Arrange
        SubjectRequestDTO requestDTO = new SubjectRequestDTO("Psicologia", 2, List.of("major1"));
        Subject entityToSave = new Subject("Psicologia", 2, List.of("major1"));
        Subject entitySaved = new Subject("Psicologia", 2, List.of("major1"));
        SubjectResponseDTO responseDTO = new SubjectResponseDTO("1", "Psicologia", 2, List.of("major1"));

        try (MockedStatic<SubjectMapper> mockedMapper = mockStatic(SubjectMapper.class)) {
            mockedMapper.when(() -> SubjectMapper.subjectRequestDTOToSubject(requestDTO))
                    .thenReturn(entityToSave);
            mockedMapper.when(() -> SubjectMapper.subjectToSubjectResponseDTO(entitySaved))
                    .thenReturn(responseDTO);

            when(subjectRepository.save(entityToSave)).thenReturn(entitySaved);

            // Act
            SubjectResponseDTO result = subjectService.save(requestDTO);

            // Assert
            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo("1");
            assertThat(result.getName()).isEqualTo("Psicologia");

            mockedMapper.verify(() -> SubjectMapper.subjectRequestDTOToSubject(requestDTO), times(1));
            mockedMapper.verify(() -> SubjectMapper.subjectToSubjectResponseDTO(entitySaved), times(1));
            verify(subjectRepository, times(1)).save(entityToSave);
        }
    }
    @Test
    void getSubjectsByMajorId_validId_returnListOfSubjects() {
        // Arrange
        String idMajor = "major1";
        List<Subject> subjects = List.of(
                new Subject("1", "Didactica", 2, List.of("major1")),
                new Subject("2", "Historia", 3, List.of("major1"))
        );
        List<SubjectResponseDTO> responseDTOs = List.of(
                new SubjectResponseDTO("1", "Didactica", 2, List.of("major1")),
                new SubjectResponseDTO("2", "Historia", 3, List.of("major1"))
        );

        when(subjectRepository.findAllByMajorsIds(idMajor)).thenReturn(subjects);

        try (MockedStatic<SubjectMapper> mockedMapper = mockStatic(SubjectMapper.class)) {
            mockedMapper.when(() -> SubjectMapper.subjectListToSubjectResponseDTOList(subjects))
                    .thenReturn(responseDTOs);

            // Act
            List<SubjectResponseDTO> result = subjectService.getSubjectsByMajorId(idMajor);

            // Assert
            assertThat(result).isNotNull().hasSize(2);
            assertThat(result).containsExactlyElementsOf(responseDTOs);

            verify(subjectRepository, times(1)).findAllByMajorsIds(idMajor);
            mockedMapper.verify(() -> SubjectMapper.subjectListToSubjectResponseDTOList(subjects), times(1));
        }
    }



}

