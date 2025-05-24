package com.unican.polaflix.restctrl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.unican.polaflix.dominio.Capitulo;
import com.unican.polaflix.dominio.Serie;
import com.unican.polaflix.dominio.Temporada;
import com.unican.polaflix.dominio.Usuario;
import com.unican.polaflix.repositories.SerieRepository;
import com.unican.polaflix.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@RestController
public class UsuariosController {

    @Autowired
    SerieRepository sr;

    @Autowired
	UsuarioRepository ur;

    private static final String MENSAJE_EXITO = "Ok\n";

    @GetMapping(value="usuarios", params="nombre-usuario")
    @JsonView({Views.VistaUsuario.class})
    public ResponseEntity<Usuario> getUsuario(@RequestParam("nombre-usuario") String nombre) {

        Optional<Usuario> u = ur.getUsuarioByNombre(nombre);
        ResponseEntity<Usuario> result;
        if (u.isPresent()) {
            result = ResponseEntity.ok(u.get());
        } else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }

    @GetMapping(value="usuarios/{id-usuario}")
    @JsonView({Views.VistaUsuario.class})
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id-usuario") Integer idUsuario) {

        Optional<Usuario> u = ur.getUsuarioByidUsuario(idUsuario);
        ResponseEntity<Usuario> result;
        if (u.isPresent()) {
            result = ResponseEntity.ok(u.get());
        } else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }

    @Transactional
    String verCapituloTransaction(Integer idUsuario, Integer idSerie, Integer temporada, Integer capitulo){

        //Obtenemos el usuario
        Optional<Usuario> u = ur.getUsuarioByidUsuario(idUsuario);

        //Si el usuario existe
        if(u.isPresent()){

            Usuario usr = u.get();

            //Obtenemos la serie
            Optional<Serie> s = sr.getByidSerie(idSerie);

            //Si la serie existe
            if(s.isPresent()){

                Serie ser = s.get();

                //Obtenemos la temporada
                Temporada t = ser.getTemporadaByNumber(temporada);

                //Si la temporada existe
                if(t != null){

                    //Obtenemos el capitulo
                    Capitulo c = t.getCapituloByNumber(capitulo);

                    //Si el capitulo existe
                    if(c != null){

                        //Vemos el capitulo
                        usr.verCapitulo(c);

                        //Guardamos el nuevo estado
                        ur.save(usr);

                    }else{
                        return "El capitulo que se busca visualizar no existe\n";
                    }
                }else{
                    return "La temporada que se busca visualizar no existe\n";
                }
            }else{
                return "La serie que se busca visualizar no existe\n";
            }
        }else{
            return "El usuario que realiza la operacion no existe\n";
        }

        return MENSAJE_EXITO;
    }

    @PostMapping(value="usuarios/{id-usuario}/verCapitulo")
    @JsonView({Views.VistaSerie.class})
    public ResponseEntity<Map<String, String>> verCapitulo(@PathVariable("id-usuario") Integer idUsuario, @RequestHeader("id-serie") Integer idSerie, @RequestHeader("numero-temporada") Integer temporada, @RequestHeader("numero-capitulo") Integer capitulo) {
        ResponseEntity<Map<String, String>> result;

        String resultado = verCapituloTransaction(idUsuario, idSerie, temporada, capitulo);
        Map<String, String> msg = Map.of("Mensaje", resultado);

        if(MENSAJE_EXITO.equals(resultado)){
            result = ResponseEntity.ok(msg);
            return result;
        }

        result = new ResponseEntity<Map<String, String>>(msg, HttpStatus.NOT_FOUND);
        return result;
    }

    String anyadirSerieTransaction(Integer idUsuario, Integer idSerie){
        //Obtenemos el usuario
        Optional<Usuario> u = ur.getUsuarioByidUsuario(idUsuario);

        //Si el usuario existe
        if(u.isPresent()){

            Usuario usr = u.get();

            //Obtenemos la serie
            Optional<Serie> s = sr.getByidSerie(idSerie);

            //Si la serie existe
            if(s.isPresent()){

                Serie ser = s.get();

                //Anyadimos la serie a la lista de series en curso del usuario
                usr.anyadirSerie(ser);

                //Guardamos al usuario
                ur.save(usr);
            }else{
                return "La serie que se busca anyadir no existe\n";
            }
        }else{
            return "El usuario que realiza la operacion no existe\n";
        }

        return MENSAJE_EXITO;
    }

    @PostMapping(value="usuarios/{id-usuario}/anyadirSerie")
    @JsonView({Views.VistaSerie.class})
    public ResponseEntity<Map<String, String>> anyadirSerie(@PathVariable("id-usuario") Integer idUsuario, @RequestHeader("id-serie") Integer idSerie) {
        ResponseEntity<Map<String, String>> result;

        String resultado = anyadirSerieTransaction(idUsuario, idSerie);
        Map<String, String> msg = Map.of("Mensaje", resultado);

        if(MENSAJE_EXITO.equals(resultado)){
            result = ResponseEntity.ok(msg);
            return result;
        }

        result = new ResponseEntity<Map<String, String>>(msg, HttpStatus.NOT_FOUND);
        return result;
    }
}
