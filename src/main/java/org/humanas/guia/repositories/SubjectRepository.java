package org.humanas.guia.repositories;

import org.humanas.guia.entities.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, String>{
    List<Subject> findAllByMajorsIds(String majorId);

}
