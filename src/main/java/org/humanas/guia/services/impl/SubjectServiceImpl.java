package org.humanas.guia.services.impl;

import lombok.RequiredArgsConstructor;
import org.humanas.guia.dtos.SubjectRequestDTO;
import org.humanas.guia.dtos.SubjectResponseDTO;
import org.humanas.guia.dtos.SubjectYearDTO;
import org.humanas.guia.entities.Major;
import org.humanas.guia.entities.Subject;
import org.humanas.guia.mappers.SubjectMapper;
import org.humanas.guia.repositories.MajorRepository;
import org.humanas.guia.repositories.SubjectRepository;
import org.humanas.guia.services.SubjectService;
import org.springframework.stereotype.Service;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository repository;
    private final MajorRepository majorRepository;

    public List<Subject> getAllSubjects(){
        return this.repository.findAll();
    }
    @Override
    public SubjectResponseDTO save(SubjectRequestDTO requestDTO) {
        List<Major> majors = majorRepository.findAllById(requestDTO.getMajorsIds());
        Subject entityToSave = SubjectMapper.subjectRequestDTOToSubject(requestDTO, majors);
        Subject entitySaved = repository.save(entityToSave);
        return SubjectMapper.subjectToSubjectResponseDTO(entitySaved);
    }
    @Override
    public List<SubjectResponseDTO> getSubjectsByMajorId(Long idMajor) {
        List<Subject> subjects = repository.findAllByMajorsId(idMajor);
        return SubjectMapper.subjectListToSubjectResponseDTOList(subjects);
    }

    public List<SubjectYearDTO> getAllYearsForSubject(Long idSubject){
        List<SubjectYearDTO> subjectYears = new ArrayList<>();
        Optional<Subject> s = this.repository.findById(idSubject);
        if (s.isPresent()) {
            Subject sub = s.get();
            Integer year = sub.getYear();
            int anioActual = Year.now().getValue(); // Año actual

            // Generar la lista de años
            List<Integer> listaAnios = IntStream.rangeClosed(year, anioActual)
                    .boxed()
                    .toList();

            for (Integer anio : listaAnios){
                SubjectYearDTO sYD = new SubjectYearDTO(anio);
                subjectYears.add(sYD);
            }
        }
        return subjectYears;
    }

    public List<String> getAllSubjectsNames(){
        return this.repository.getAllSubjectsNames();
    }

    public String getSubjectNameById(Long id){
        return this.repository.getSubjectNameById(id);
    }
}
