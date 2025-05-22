package com.unican.polaflix.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.unican.polaflix.restctrl.Views;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonPropertyOrder({"id-serie", "nombre-serie", "sinopsis", "tipo-serie", "artistas", "temporadas"})
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id-serie")
    @JsonView({Views.VistaUsuario.class})
    int idSerie;

    @JsonProperty("nombre-serie")
    @JsonView({Views.VistaSerie.class, Views.VistaUsuario.class})
    protected String nombreSerie;

    @Column(length = 4096)
    @JsonProperty("sinopsis")
    @JsonView({Views.VistaSerie.class})
    protected String sinopsis;

    @JsonProperty("tipo-serie")
    @JsonView({Views.VistaSerie.class})
    protected Categoria tipoSerie;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonProperty("artistas")
    @JsonView({Views.VistaSerie.class})
    protected Set<Artista> artistas;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    @JsonProperty("temporadas")
    @JsonManagedReference
    @JsonView({Views.VistaSerie.class})
    protected List<Temporada> temporadas;

    protected Serie(){}

    public Serie(String nombreSerie, String sinopsis, Categoria tipoSerie){
        this.artistas = new HashSet<>();
        this.temporadas = new ArrayList<>();
        this.nombreSerie = nombreSerie;
        this.sinopsis = sinopsis;
        this.tipoSerie = tipoSerie;
    }

    //------------------------------
    //----> Setters y Getters <-----
    //------------------------------

    public String getNombreSerie() {
        return nombreSerie;
    }

    public void setNombreSerie(String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Categoria getTipoSerie() {
        return tipoSerie;
    }

    public void setTipoSerie(Categoria tipoSerie) {
        this.tipoSerie = tipoSerie;
    }

    public void setArtistas(Set<Artista> artistas){
        this.artistas = artistas;
    }
    
    public Set<Artista> getArtistas() {
        return artistas;
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {

        for (Temporada temporada : temporadas) {
            //Las eliminamos de las posibles series en las que estuviesen
            temporada.getSerie().quitarTemporada(temporada);

            //Nos aseguramos de que su nueva serie es esta
            temporada.setSerie(this);
        }

        this.temporadas = temporadas;
    }

    //------------------------------
    //------------------------------
    //------------------------------

    public void anyadirArtista(Artista artista){
        artistas.add(artista);
    }

    public void quitarArtista(Artista artista){
        artistas.remove(artista);
    }

    public void anyadirTemporada(Temporada temporada){
        //Si ya estaba adscrita a una serie, la quitamos de ahi
        temporada.getSerie().quitarTemporada(temporada);

        //Anyadimos a esta serie la temporada
        temporadas.add(temporada);
        
        //Indicamos que la serie a la que pertenece esta temporada es al indicada
        temporada.setSerie(this);
    }

    public void quitarTemporada(Temporada temporada){
        //Quitamos la temporada
        temporadas.remove(temporada);
    }

    public Temporada getTemporadaByNumber(int numeroTemporada){
        //Para todas las temporadas de esta serie
        for (Temporada t : this.temporadas) {
            //Devuelve aquella cuyo numero coincida
            if(t.getNumeroTemporada() == numeroTemporada) return t;
        }

        //Si no existe devuelve null
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombreSerie == null) ? 0 : nombreSerie.hashCode());
        result = prime * result + ((sinopsis == null) ? 0 : sinopsis.hashCode());
        result = prime * result + ((tipoSerie == null) ? 0 : tipoSerie.hashCode());
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
        Serie other = (Serie) obj;
        if (nombreSerie == null) {
            if (other.nombreSerie != null)
                return false;
        } else if (!nombreSerie.equals(other.nombreSerie))
            return false;
        if (sinopsis == null) {
            if (other.sinopsis != null)
                return false;
        } else if (!sinopsis.equals(other.sinopsis))
            return false;
        if (tipoSerie != other.tipoSerie)
            return false;
        return true;
    }
}
