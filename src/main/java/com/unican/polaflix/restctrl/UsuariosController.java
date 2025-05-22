package com.unican.polaflix.restctrl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.unican.polaflix.dominio.Usuario;
import com.unican.polaflix.repositories.UsuarioRepository;

@RestController
public class UsuariosController {

    @Autowired
	UsuarioRepository ur;

    @GetMapping(value="usuarios", params="nombre-usuario")
    @JsonView({Views.VistaUsuario.class})
    public ResponseEntity<Usuario> getUsuario(@RequestParam("nombre-usuario") String nombre) {
        System.out.println(nombre);
        Optional<Usuario> u = ur.getUsuarioByNombre(nombre);
        ResponseEntity<Usuario> result;
        if (u.isPresent()) {
            result = ResponseEntity.ok(u.get());
        } else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }
}
