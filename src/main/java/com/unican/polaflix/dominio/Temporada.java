package com.unican.polaflix.dominio;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.unican.polaflix.restctrl.Views;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonPropertyOrder({"id-temporada", "nombre-serie", "numero-temporada", "capitulos"})
public class Temporada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id-temporada")
    @JsonView({Views.VistaSerie.class})
    int id_temporada;

    @ManyToOne
    @JsonBackReference
    protected Serie serie;

    @JsonProperty("numero-temporada")
    @JsonView({Views.VistaSerie.class, Views.VistaTemporada.class})
    protected int numeroTemporada;

    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonProperty("capitulos")
    @JsonView({Views.VistaTemporada.class})
    protected List<Capitulo> capitulos;

    protected Temporada(){}

    public Temporada(Serie serie, int numeroTemporada){
        this.capitulos = new ArrayList<>();
        this.serie = serie;
        this.numeroTemporada = numeroTemporada;

        //Anyadimos la temporada correspondiente
        serie.anyadirTemporada(this);
    }

    //------------------------------
    //----> Setters y Getters <-----
    //------------------------------

    @JsonProperty("nombre-serie")
    @JsonView({Views.VistaTemporada.class})
    public String nombreDeSerie(){
        return serie.getNombreSerie();
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public int getNumeroTemporada() {
        return numeroTemporada;
    }

    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<Capitulo> capitulos) throws Exception {
        
        //Para cada capitulo en capitulos
        for (Capitulo capitulo : capitulos) {
            //Los eliminamos de la posible temporada en la que estuviesen
            capitulo.getTemporada().quitarCapitulo(capitulo);

            //Nos aseguramos de que su nueva temporada es esta
            if(capitulo.getTemporada() != this) throw new Exception("El capitulo, anyadido no pertenece a la temporada correspondiente");
        }

        this.capitulos = capitulos;
    }

    //------------------------------
    //------------------------------
    //------------------------------

    public void anyadirCapitulo(Capitulo capitulo){
        //Anyadimos el capitulo
        capitulos.add(capitulo);
    }

    public void quitarCapitulo(Capitulo capitulo){
        //Borramos el capitulo de la temporada
        capitulos.remove(capitulo);
    }

    public Capitulo getCapituloByNumber(int numeroCapitulo){
        //Para todos los capitulos de esta temporada
        for (Capitulo c : this.capitulos) {
            //Devuelve aquella cuyo numero coincida
            if(c.getNumeroCapitulo() == numeroCapitulo) return c;
        }

        //Si no existe devuelve null
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((serie == null) ? 0 : serie.hashCode());
        result = prime * result + numeroTemporada;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Temporada other = (Temporada) obj;
        if (serie == null) {
            if (other.serie != null)
                return false;
        } else if (!serie.equals(other.serie))
            return false;
        if (numeroTemporada != other.numeroTemporada)
            return false;
        return true;
    }
}
