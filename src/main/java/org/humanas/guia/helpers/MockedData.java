package org.humanas.guia.helpers;

import org.humanas.guia.dtos.AphorismDTO;
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

    public List<AphorismDTO> getAphorismsMocked(){
        List<AphorismDTO> aphs = new ArrayList<>();
        AphorismDTO a1 = new AphorismDTO("Karl Marx", "La lucha de clases es el motor de la historia");
        AphorismDTO a2 = new AphorismDTO("Max Weber", "No hay Estado sin una burocracia profesional");
        AphorismDTO a3 = new AphorismDTO("Juan Domingo Perón", "La única verdad es la realidad");
        AphorismDTO a4 = new AphorismDTO("Martin Heiddeger", "La esencia de la libertad se encuentra en la verdad");
        AphorismDTO a5 = new AphorismDTO("Friedrich Nietzsche", "No hay mayor desgracia que el de ser un hombre debil");
        aphs.add(a1);
        aphs.add(a2);
        aphs.add(a3);
        aphs.add(a4);
        aphs.add(a5);
        return aphs;
    }
}
