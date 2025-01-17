package org.humanas.guia.repositories.impl;

import org.humanas.guia.entities.Major;
import org.humanas.guia.repositories.MajorJpaRepository;
import org.humanas.guia.repositories.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MajorJpaRepositoryImpl implements MajorRepository {

    @Autowired
    private final MajorJpaRepository jpaRepository;

    public MajorJpaRepositoryImpl(MajorJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Major save(Major major) {
        return jpaRepository.save(major);
    }

    @Override
    public Optional<Major> findById(String id) {
        return jpaRepository.findById(Long.valueOf(id));
    }

    @Override
    public List<Major> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(Long.valueOf(id));
    }
}

