package org.humanas.guia.helpers;

import org.humanas.guia.dtos.MajorDTO;
import org.humanas.guia.entities.Major;

import java.util.ArrayList;
import java.util.List;

public class MockedData {

    public List<MajorDTO> getMajorsMocked(){
        List<MajorDTO> majors = new ArrayList<>();
        MajorDTO m1 = new MajorDTO("Historia");
        MajorDTO m2 = new MajorDTO("Relaciones internacionales");
        MajorDTO m3 = new MajorDTO("Geografia");
        MajorDTO m4 = new MajorDTO("Trabajo social");
        MajorDTO m5 = new MajorDTO("Ciencias de la educacion");
        majors.add(m1);
        majors.add(m2);
        majors.add(m3);
        majors.add(m4);
        majors.add(m5);
        return majors;
    }
}
