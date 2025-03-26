package com.unican.polaflix.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Capitulo implements Comparable<Capitulo> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_capitulo;

    @ManyToOne
    protected Temporada temporada;
    
    protected String titulo;
    protected String descripcion;
    protected int numeroCapitulo;

    public Capitulo(Temporada temporada, String titulo, String descripcion, int numeroCapitulo){
        this.temporada = temporada;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.numeroCapitulo = numeroCapitulo;

        temporada.anyadirCapitulo(this);
    }

    //------------------------------
    //----> Setters y Getters <-----
    //------------------------------

    public Temporada getTemporada() {
        return temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumeroCapitulo() {
        return numeroCapitulo;
    }

    public void setNumeroCapitulo(int numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }

    //------------------------------
    //------------------------------
    //------------------------------

    @Override
    public int compareTo(Capitulo o) {
        
        //Si la temporada en la que esta es mas reciente
        if(this.temporada.getNumeroTemporada() > o.temporada.getNumeroTemporada()){

            //Es mayor
            return 1;

        //Si la temporada en la que esta es menos reciente
        }else if(this.temporada.getNumeroTemporada() < o.temporada.getNumeroTemporada()){

            //Es menor
            return -1;

        //Si son de la misma temporada
        }else{

            //Si el capitulo es mas reciente
            if(this.numeroCapitulo > o.numeroCapitulo){

                //Es mayor
                return 1;

            //Si el capitilo es menos reciente
            }else if(this.numeroCapitulo < o.numeroCapitulo){

                //Es menor
                return -1;
            }
        }

        //De lo contrario son el mismo capitulo
        return 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((temporada == null) ? 0 : temporada.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + numeroCapitulo;
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
        Capitulo other = (Capitulo) obj;
        if (temporada == null) {
            if (other.temporada != null)
                return false;
        } else if (!temporada.equals(other.temporada))
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;
        if (numeroCapitulo != other.numeroCapitulo)
            return false;
        return true;
    }
}
