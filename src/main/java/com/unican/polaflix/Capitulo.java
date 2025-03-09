package com.unican.polaflix;

public class Capitulo implements Comparable<Capitulo> {
    protected Temporada temporada;
    protected String titulo;
    protected String descripcion;
    protected int numeroCapitulo;

    public Capitulo(Temporada temporada, String titulo, String descripcion, int numeroCapitulo){
        this.temporada = temporada;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.numeroCapitulo = numeroCapitulo;
    }

    //------------------------------
    //----> Setters y Getters <-----
    //------------------------------

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        //Quitamos el capitulo de la temporada previa
        this.temporada.quitarCapitulo(this);

        //Ponemos el capitulo en la temporada nueva
        temporada.anyadirCapitulo(this);

        //Actualizamos la temporada
        this.temporada = temporada;
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
    
}
