package org.humanas.guia.controllers;

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
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubjectControllerTest {

    @InjectMocks
    private SubjectController subjectController;

    @Mock
    private SubjectServiceImpl subjectService;

    @Test
    void save_validRequest_returnSavedSubject() {
        // Arrange
        SubjectRequestDTO requestDTO = new SubjectRequestDTO("Pedagogia", 1,1);
        SubjectResponseDTO savedSubject = new SubjectResponseDTO(1L, "Pedagogia", 1,2);

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

