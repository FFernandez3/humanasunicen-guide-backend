package org.humanas.guia.utils;

import org.humanas.guia.entities.Major;
import org.humanas.guia.repositories.FileRepository;
import org.humanas.guia.repositories.MajorRepository;
import org.humanas.guia.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class DataLoaderHelper {

    private final FileRepository fileRepository;

    private final MajorRepository majorRepository;

    private final SubjectRepository subjectRepository;

    public DataLoaderHelper(FileRepository fileRepository, @Qualifier("majorJpaRepositoryImpl") MajorRepository majorRepository, SubjectRepository subjectRepository) {
        this.fileRepository = fileRepository;
        this.majorRepository = majorRepository;
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public void loadCarreras() {
        List<String[]> carreras = CSVReaderHelper.readCSV("/src/main/java/org/humanas/guia/utils/carreras.csv");
        for (String[] carrera : carreras.subList(1, carreras.size())) {
            Major m = new Major();
//            p.setId(Long.parseLong(parada[0]));
            m.setName(carrera[1]);
            m.setPlanDeEstudios(null);
            m.setPerfil_profesional(null);
            m.setAlcances_titulo(null);
            m.setAnio_inicio(Integer.parseInt(carrera[5]));
            majorRepository.save(m);
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
