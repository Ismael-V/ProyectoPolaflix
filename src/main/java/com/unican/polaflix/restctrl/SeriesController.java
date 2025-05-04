package com.unican.polaflix.restctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.unican.polaflix.dominio.Serie;
import com.unican.polaflix.repositories.SerieRepository;

import jakarta.websocket.server.PathParam;

@RestController
public class SeriesController {

    @Autowired
    SerieRepository sr;

    @GetMapping(value="series", params = {"inicial"})
    @JsonView({Views.VistaSerie.class})
    public ResponseEntity<List<Serie>> getSerie(@PathParam("inicial") String inicial) {
        List<Serie> series = sr.findBynombreSerieLike(inicial + "%");
        ResponseEntity<List<Serie>> result;
        if (!series.isEmpty()) {
            result = ResponseEntity.ok(series);
        } else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }
    
}
