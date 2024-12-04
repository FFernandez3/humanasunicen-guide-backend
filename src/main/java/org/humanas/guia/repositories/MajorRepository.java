package org.humanas.guia.repositories;

import org.humanas.guia.entities.Major;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends MongoRepository<Major, String> {
}
