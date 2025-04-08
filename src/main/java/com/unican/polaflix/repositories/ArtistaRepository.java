package com.unican.polaflix.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unican.polaflix.dominio.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer> {
    Optional<Artista> getBynombre(String nombre);
}
