package com.unican.polaflix.dominio;


import java.util.HashSet;
import java.util.Set;

public class Artista {
    protected String nombre;
    protected Set<Rol> rol;

    Artista(String nombre){
        this.rol = new HashSet<>();
        this.nombre = nombre;
    }

    //------------------------------
    //----> Setters y Getters <-----
    //------------------------------

    public void anyadirRol(Rol rol){
        this.rol.add(rol);
    }

    public void quitarRol(Rol rol){
        this.rol.remove(rol);
    }

    //------------------------------
    //------------------------------
    //------------------------------
}
