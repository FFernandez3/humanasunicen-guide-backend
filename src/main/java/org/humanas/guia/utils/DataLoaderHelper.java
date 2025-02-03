package org.humanas.guia.utils;

import org.humanas.guia.entities.File;
import org.humanas.guia.entities.Major;
import org.humanas.guia.entities.Subject;
import org.humanas.guia.enums.FileType;
import org.humanas.guia.repositories.FileRepository;
import org.humanas.guia.repositories.MajorRepository;
import org.humanas.guia.repositories.SubjectRepository;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public boolean areTablesEmpty(){
        return majorRepository.count() == 0 && subjectRepository.count() == 0 && fileRepository.count() == 0;
    }

    @Transactional
    public void loadCarreras() {
        List<String[]> carreras = CSVReaderHelper.readCSV("src/main/java/org/humanas/guia/utils/carreras.csv");
        System.out.println("Cargando datos de carreras");
        for (String[] carrera : carreras.subList(1, carreras.size())) {
            Major m = new Major();
            m.setName(carrera[1]);
            m.setOfficialPage(carrera[2]);
            //m.setPerfil_profesional(null);
            //m.setAlcances_titulo(null);
            m.setAnio_inicio(Integer.parseInt(carrera[3]));
            majorRepository.save(m);
        }
    }

    @Transactional
    public void loadMaterias() {
        List<String[]> catedras = CSVReaderHelper.readCSV("src/main/java/org/humanas/guia/utils/catedras.csv");
        System.out.println("Cargando datos de materias");
        for (String[] cat : catedras.subList(1, catedras.size())) {
            Subject s = new Subject();
            System.out.println(cat[1]);
            s.setName(cat[1]);
            s.setYear(Integer.valueOf(cat[2]));
            s.setQuarter(Integer.valueOf(cat[3]));
            subjectRepository.save(s);
        }
    }

    public List<Major> loadCarrerasMateria(Long idMateria){
        List<String[]> materiasCarreras = CSVReaderHelper.readCSV("src/main/java/org/humanas/guia/utils/catedra_carrera.csv");
        List<Major> carrerasParaMateria = new ArrayList<>();
        for (String[] mC : materiasCarreras.subList(1, materiasCarreras.size())) {
            if (Objects.equals(idMateria, Long.valueOf(mC[1]))){
                Optional<Major> m = majorRepository.findById(Long.valueOf(mC[0]));
                m.ifPresent(carrerasParaMateria::add);
            }
        }
        return carrerasParaMateria;
    }

    @Transactional
    public void loadArchivos() {
        List<String[]> files = CSVReaderHelper.readCSV("src/main/java/org/humanas/guia/utils/archivos.csv");
        System.out.println("Cargando datos de archivos");
        for (String[] arch : files.subList(1, files.size())) {
            File f = new File();
            f.setName(arch[1]);
            f.setUrl(arch[2]);
            f.setType(FileType.valueOf(arch[3]));
            f.setMonth(arch[4]); //llamado
            f.setSubjectId(Long.valueOf(arch[5]));
            f.setUploadDate(null);
            fileRepository.save(f);
        }
    }
}
