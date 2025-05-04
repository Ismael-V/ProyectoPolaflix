package com.unican.polaflix.restctrl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.unican.polaflix.dominio.Factura;
import com.unican.polaflix.repositories.FacturaRepository;

@RestController
public class FacturasController {
    @Autowired
	FacturaRepository fr;

    @GetMapping(value="facturas/{id}")
    @JsonView({Views.VistaFactura.class})
    public ResponseEntity<Factura> getFactura(@PathVariable ("id") int id_factura) {
        Optional<Factura> f = fr.getByIdFactura(id_factura);
        ResponseEntity<Factura> result;
        if (f.isPresent()) {
            result = ResponseEntity.ok(f.get());
        } else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }
}
