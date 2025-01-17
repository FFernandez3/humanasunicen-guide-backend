package org.humanas.guia.repositories;

import org.humanas.guia.entities.Major;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Profile("mongo")
@Repository
public interface MajorMongoRepository extends MongoRepository<Major, String> {
    // Métodos específicos de MongoDB, si necesitas alguno
}

