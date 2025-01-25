package org.humanas.guia.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.humanas.guia.enums.FileType;

public class FileRequestDTO {
    @NotEmpty(message = "el campo archivo no puede estar vacío")
    @NotNull( message = "El archivo es obligatorio.")
    private Object file;
    @NotEmpty(message = "el campo carrera no puede estar vacío")
    @NotNull( message = "El nombre de la carrera es un campo obligatorio.")
    private String carrera;
    @NotEmpty(message = "el campo catedra del archivo no puede estar vacío")
    @NotNull( message = "La catedra es un campo obligatorio.")
    private String catedra;
    @NotEmpty(message = "el campo tipo de archivo no puede estar vacío")
    @NotNull( message = "El tipo es un campo obligatorio.")
    private FileType tipo;
    private Integer anio;
    private String llamado;

}
