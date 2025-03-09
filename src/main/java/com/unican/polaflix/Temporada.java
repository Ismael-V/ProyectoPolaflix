package com.unican.polaflix;

import java.util.ArrayList;
import java.util.List;

public class Temporada {
    protected Serie serie;
    protected int numeroTemporada;

    protected List<Capitulo> capitulos;

    Temporada(Serie serie, int numeroTemporada){
        this.capitulos = new ArrayList<>();
        this.serie = serie;
        this.numeroTemporada = numeroTemporada;
    }

    //------------------------------
    //----> Setters y Getters <-----
    //------------------------------

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

    public void setCapitulos(List<Capitulo> capitulos) {
        
        //Para cada capitulo en capitulos
        for (Capitulo capitulo : capitulos) {
            //Los eliminamos de la posible temporada en la que estuviesen
            capitulo.getTemporada().quitarCapitulo(capitulo);

            //Nos aseguramos de que su nueva temporada es esta
            capitulo.setTemporada(this);
        }

        this.capitulos = capitulos;
    }

    //------------------------------
    //------------------------------
    //------------------------------

    public void anyadirCapitulo(Capitulo capitulo){
        //Si ya estaba adscrita a una temporada, lo quitamos de ahi
        capitulo.getTemporada().quitarCapitulo(capitulo);

        //Anyadimos el capitulo
        capitulos.add(capitulo);

        //Ponemos de temporada, esta temporada
        capitulo.setTemporada(this);
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
}
