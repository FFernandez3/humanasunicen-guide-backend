package org.humanas.guia.services;

import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;
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
        SubjectRequestDTO requestDTO = new SubjectRequestDTO("Psicologia", 2, 1);

        Subject entityToSave = new Subject("Psicologia", 2, 1);
        entityToSave.setId(1L);
        SubjectResponseDTO responseDTO = new SubjectResponseDTO(1L, "Psicologia", 2, 1);

        when(subjectRepository.save(entityToSave)).thenReturn(entityToSave);

        try (MockedStatic<SubjectMapper> mockedMapper = mockStatic(SubjectMapper.class)) {
            mockedMapper.when(() -> SubjectMapper.subjectRequestDTOToSubject(requestDTO))
                    .thenReturn(entityToSave);
            mockedMapper.when(() -> SubjectMapper.subjectToSubjectResponseDTO(entityToSave))
                    .thenReturn(responseDTO);

            // Act
            SubjectResponseDTO result = subjectService.save(requestDTO);

            // Assert
            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(1L);
            assertThat(result.getName()).isEqualTo("Psicologia");


            verify(subjectRepository, times(1)).save(entityToSave);
            mockedMapper.verify(() -> SubjectMapper.subjectRequestDTOToSubject(requestDTO), times(1));
            mockedMapper.verify(() -> SubjectMapper.subjectToSubjectResponseDTO(entityToSave), times(1));
        }
    }


}



