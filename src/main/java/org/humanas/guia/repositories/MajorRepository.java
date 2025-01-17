package org.humanas.guia.repositories;

import org.humanas.guia.entities.Major;

import java.util.List;
import java.util.Optional;

public interface MajorRepository {
    Major save(Major major);
    Optional<Major> findById(String id);
    List<Major> findAll();
    void deleteById(String id);
}

