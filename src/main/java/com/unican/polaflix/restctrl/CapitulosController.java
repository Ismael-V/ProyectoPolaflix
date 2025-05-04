package com.unican.polaflix.restctrl;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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
public class CapitulosController {
    
    @Autowired
    SerieRepository sr;

    @Autowired
    UsuarioRepository ur;


    @Transactional
    public void ismaVeCapitulo(Capitulo c){
        Usuario Ismael = ur.getUsuarioByNombre("Ismael").get();
        Ismael.verCapitulo(c, true);
        ur.save(Ismael);
    }

    @PostMapping(value="capitulos")
    @JsonView({Views.VistaSerie.class})
    public ResponseEntity<Map<String, String>> getSerie(@RequestHeader("nombre-serie") String nombreSerie, @RequestHeader("temporada") int temporada, @RequestHeader("capitulo") int capitulo) {
        ResponseEntity<Map<String, String>> result;

        System.out.println(nombreSerie);

        Optional<Serie> s = sr.getBynombreSerie(nombreSerie);
        
        if (s.isPresent()) {
            Serie serie = s.get();
            Temporada t = serie.getTemporadaByNumber(temporada);

            if(t != null){
                Capitulo c = t.getCapituloByNumber(capitulo);

                if(c != null){
                    ismaVeCapitulo(c);
                    Map<String, String> msg = Map.of("Mensaje", "Capítulo visto correctamente");
                    result = ResponseEntity.ok(msg);
                    return result;
                }
            }
        }
        result = ResponseEntity.notFound().build();
        return result;
    }
}
