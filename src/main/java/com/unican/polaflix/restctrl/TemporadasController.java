package com.unican.polaflix.restctrl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.annotation.JsonView;
import com.unican.polaflix.dominio.Serie;
import com.unican.polaflix.dominio.Temporada;
import com.unican.polaflix.repositories.SerieRepository;

@RestController
public class TemporadasController {
    
    @Autowired
	SerieRepository sr;

    @GetMapping(value="series/{id-serie}/temporadas/{numero-temporada}")
    @JsonView({Views.VistaTemporada.class})
    public ResponseEntity<Temporada> getTemporada(@PathVariable ("id-serie") Integer idSerie, @PathVariable ("numero-temporada") Integer temporada) {

        Optional<Serie> s = sr.getByidSerie(idSerie);
        ResponseEntity<Temporada> result;
        if (s.isPresent()) {
            Serie serie = s.get();
            Temporada t = serie.getTemporadaByNumber(temporada);

            if(t != null){
                result = ResponseEntity.ok(t);
            }else{
                result = ResponseEntity.notFound().build();
            }
        } else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }
}
