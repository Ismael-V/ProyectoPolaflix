package com.unican.polaflix;

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
}
