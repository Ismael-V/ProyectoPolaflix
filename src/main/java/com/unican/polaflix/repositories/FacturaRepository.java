package com.unican.polaflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unican.polaflix.dominio.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    List<Factura> findByAnyo(int anyo);
    List<Factura> findByAnyoAndMes(int anyo, int mes);

}
