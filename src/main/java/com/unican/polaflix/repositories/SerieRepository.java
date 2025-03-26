package com.unican.polaflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.unican.polaflix.dominio.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Integer> {
    
}
