package com.unican.polaflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unican.polaflix.dominio.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
