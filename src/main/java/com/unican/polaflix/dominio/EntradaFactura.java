package com.unican.polaflix.dominio;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EntradaFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_entrada_factura;

    private int id_factura;

    protected LocalDate fechaVisualizacion;
    protected String nombreSerie;
    protected int numeroTemporada;
    protected int numeroCapitulo;
    protected int importeCentimos;
    protected boolean fueComprado = false;

    public EntradaFactura(LocalDate fechaVisualizacion, String nombreSerie, int numeroTemporada, int numeroCapitulo, Categoria tipoSerie, boolean fueComprado){
        this.fechaVisualizacion = fechaVisualizacion;
        this.nombreSerie = nombreSerie;
        this.numeroCapitulo = numeroCapitulo;
        this.numeroTemporada = numeroTemporada;
        this.fueComprado = fueComprado;

        //En funcion de la categoria calculamos el importe a cobrar
        switch(tipoSerie){
            case STANDARD:
                this.importeCentimos = 50;
            break;

            case SILVER:
                this.importeCentimos = 75;
            break;

            case GOLD:
                this.importeCentimos = 150;
            break;

            //Por si acaso, evitar errores de no inicializado
            default:
                this.importeCentimos = 0;
        }
    }

    //------------------------------
    //----> Setters y Getters <-----
    //------------------------------

    public LocalDate getFechaVisualizacion() {
        return fechaVisualizacion;
    }

    public void setFechaVisualizacion(LocalDate fechaVisualizacion) {
        this.fechaVisualizacion = fechaVisualizacion;
    }

    public String getNombreSerie() {
        return nombreSerie;
    }

    public void setNombreSerie(String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }

    public int getNumeroTemporada() {
        return numeroTemporada;
    }

    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }

    public int getNumeroCapitulo() {
        return numeroCapitulo;
    }

    public void setNumeroCapitulo(int numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }

    public int getImporteCentimos() {
        return importeCentimos;
    }

    public void setImporteCentimos(int importeCentimos) {
        this.importeCentimos = importeCentimos;
    }

    public boolean getFueComprado() {
        return fueComprado;
    }

    public void setFueComprado(boolean fueComprado) {
        this.fueComprado = fueComprado;
    }

    //------------------------------
    //------------------------------
    //------------------------------
}
