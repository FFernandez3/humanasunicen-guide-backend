package org.humanas.guia.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;
import org.humanas.guia.services.impl.SubjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubjectControllerTest {

    @InjectMocks
    private SubjectController subjectController;

    @Mock
    private SubjectServiceImpl subjectService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getSubjectsByMajor_validId_returnListOfSubjects() {
        // Arrange
        Long idMajor = 1L;
        List<SubjectResponseDTO> mockSubjects = List.of(
                new SubjectResponseDTO(1L, "Historia mundial 1", 3,2, List.of(idMajor)),
                new SubjectResponseDTO(2L, "Historia argentina", 1, 1, List.of(idMajor))
        );

        when(subjectService.getSubjectsByMajorId(idMajor)).thenReturn(mockSubjects);

        // Act
        ResponseEntity<List<SubjectResponseDTO>> response = subjectController.getSubjectsByMajor(idMajor);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().hasSize(2);
        assertThat(response.getBody()).containsExactlyElementsOf(mockSubjects);

        verify(subjectService, times(1)).getSubjectsByMajorId(idMajor);
    }

    @Test
    void save_validRequest_returnSavedSubject() {
        // Arrange
        SubjectRequestDTO requestDTO = new SubjectRequestDTO("Pedagogia", 1,1,  List.of(2L));
        SubjectResponseDTO savedSubject = new SubjectResponseDTO(1L, "Pedagogia", 1,2, List.of(2L));

        when(subjectService.save(requestDTO)).thenReturn(savedSubject);

        // Act
        ResponseEntity<SubjectResponseDTO> response = subjectController.save(requestDTO);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(savedSubject.getId());
        assertThat(response.getBody().getName()).isEqualTo(savedSubject.getName());


        verify(subjectService, times(1)).save(requestDTO);
    }
}

