package org.humanas.guia.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubjectRequestDTO {
    @NotBlank(message = "El nombre de la materia no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres.")
    private String name;
    @NotNull(message = "El año es obligatorio.")
    private Integer year;
    @NotNull(message = "El cuatrimestre es obligatorio.")
    private Integer quarter;
}
