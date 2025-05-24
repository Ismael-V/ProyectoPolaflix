package com.unican.polaflix.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unican.polaflix.dominio.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Optional<Usuario> getUsuarioByNombre(String nombre);
    public Optional<Usuario> getUsuarioByidUsuario(int idUsuario);
}
