package com.unican.polaflix.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unican.polaflix.dominio.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Integer> {
    
    public List<Serie> findBynombreSerieLike(String nombre);
    public Optional<Serie> getBynombreSerie(String nombre);
    public Optional<Serie> getByidSerie(int idSerie);
}
