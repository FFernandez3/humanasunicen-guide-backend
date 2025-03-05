package org.humanas.guia.repositories;

import org.humanas.guia.entities.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {

    @Query("SELECT m.name FROM Major m")
    List<String> getMajorsNames();

}

