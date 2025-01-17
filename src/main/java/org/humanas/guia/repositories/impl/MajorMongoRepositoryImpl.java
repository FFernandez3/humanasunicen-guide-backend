package org.humanas.guia.repositories.impl;

import org.humanas.guia.entities.Major;
import org.humanas.guia.repositories.MajorMongoRepository;
import org.humanas.guia.repositories.MajorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MajorMongoRepositoryImpl implements MajorRepository {
    private final MajorMongoRepository mongoRepository;

    public MajorMongoRepositoryImpl(MajorMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Major save(Major major) {
        return mongoRepository.save(major);
    }

    @Override
    public Optional<Major> findById(String id) {
        return mongoRepository.findById(id);
    }

    @Override
    public List<Major> findAll() {
        return mongoRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }
}

