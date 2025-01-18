package org.humanas.guia.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
@AllArgsConstructor
public class SubjectRequestDTO {
    private String name;
    private Integer year;
    private Integer quarter;
    private List<Long> majorsIds;
}
