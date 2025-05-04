package com.unican.polaflix.dominio;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.unican.polaflix.restctrl.Views;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonPropertyOrder({"fecha-visualizacion", "nombre-serie", "numero-temporada", "numero-capitulo", "importe-centimos", "fue-comprado"})
public class EntradaFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_entrada_factura;

    @ManyToOne
    @JoinColumn(name = "id_factura", referencedColumnName = "idFactura")
    Factura factura;

    @JsonProperty("fecha-visualizacion")
    @JsonView({Views.VistaFactura.class})
    protected LocalDate fechaVisualizacion;

    @JsonProperty("nombre-serie")
    @JsonView({Views.VistaFactura.class})
    protected String nombreSerie;

    @JsonProperty("numero-temporada")
    @JsonView({Views.VistaFactura.class})
    protected int numeroTemporada;

    @JsonProperty("numero-capitulo")
    @JsonView({Views.VistaFactura.class})
    protected int numeroCapitulo;

    @JsonProperty("importe-centimos")
    @JsonView({Views.VistaFactura.class})
    protected int importeCentimos;

    @JsonProperty("fue-comprado")
    @JsonView({Views.VistaFactura.class})
    protected boolean fueComprado = false;

    protected EntradaFactura(){}

    public EntradaFactura(Factura factura, LocalDate fechaVisualizacion, String nombreSerie, int numeroTemporada, int numeroCapitulo, Categoria tipoSerie, boolean fueComprado){
        this.factura = factura;
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
