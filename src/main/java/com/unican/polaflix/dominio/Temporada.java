package com.unican.polaflix.dominio;

import java.util.ArrayList;
import java.util.List;

public class Temporada {
    protected Serie serie;
    protected int numeroTemporada;

    protected List<Capitulo> capitulos;

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
