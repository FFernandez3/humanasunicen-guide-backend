package org.humanas.guia.utils;

import org.humanas.guia.entities.File;
import org.humanas.guia.entities.Major;
import org.humanas.guia.entities.Subject;
import org.humanas.guia.enums.FileType;
import org.humanas.guia.repositories.FileRepository;
import org.humanas.guia.repositories.MajorRepository;
import org.humanas.guia.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import java.util.List;

@Component
public class DataLoaderHelper {

    private FileRepository fileRepository;

    private MajorRepository majorRepository;

    private SubjectRepository subjectRepository;

    public DataLoaderHelper(FileRepository fileRepository, MajorRepository majorRepository, SubjectRepository subjectRepository) {
        this.fileRepository = fileRepository;
        this.majorRepository = majorRepository;
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public void loadCarreras() {
        List<String[]> carreras = CSVReaderHelper.readCSV("src/main/java/org/humanas/guia/utils/carreras.csv");
        System.out.println(carreras.size());
        for (String[] carrera : carreras.subList(1, carreras.size())) {
            Major m = new Major();
            m.setName(carrera[1]);
            m.setPlanDeEstudios(null);
            m.setPerfil_profesional(null);
            m.setAlcances_titulo(null);
            m.setAnio_inicio(Integer.parseInt(carrera[5]));
            majorRepository.save(m);
        }
    }

    @Transactional
    public void loadMaterias() {
        List<String[]> catedras = CSVReaderHelper.readCSV("src/main/java/org/humanas/guia/utils/catedras.csv");
        System.out.println(catedras.size());
        for (String[] cat : catedras.subList(1, catedras.size())) {
            Subject s = new Subject();
            System.out.println(cat[1]);
            s.setName(cat[1]);
            s.setYear(Integer.valueOf(cat[2]));
            s.setQuarter(Integer.valueOf(cat[3]));
            subjectRepository.save(s);
        }
    }

    @Transactional
    public void loadArchivos() {
        List<String[]> files = CSVReaderHelper.readCSV("src/main/java/org/humanas/guia/utils/archivos.csv");
        for (String[] arch : files.subList(1, files.size())) {
            File f = new File();
            f.setName(arch[1]);
            f.setInstance(arch[2]);
            f.setUrl(arch[3]);
            f.setType(FileType.valueOf(arch[4]));
            f.setMonth(arch[5]); //llamado
            f.setSubjectId(Long.valueOf(arch[6]));
            f.setUploadDate(null);
            fileRepository.save(f);
        }
    }

    private Integer parseInteger(String value, int defaultValue) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue; // Retorna un valor predeterminado si hay un error
        }
    }
}
