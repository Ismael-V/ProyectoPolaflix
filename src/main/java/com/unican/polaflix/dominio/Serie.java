package com.unican.polaflix.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Serie {

    protected String nombreSerie;
    protected String sinopsis;
    protected Categoria tipoSerie;

    protected Set<Artista> artistas;
    protected List<Temporada> temporadas;

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
