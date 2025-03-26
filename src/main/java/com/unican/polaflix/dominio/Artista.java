package com.unican.polaflix.dominio;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_artista;

    protected String nombre;

    @ElementCollection
    protected Set<Rol> rol;

    Artista(String nombre){
        
        //this.rol = new HashSet<>();
        this.nombre = nombre;
    }

    //------------------------------
    //----> Setters y Getters <-----
    //------------------------------

    public void anyadirRol(Rol rol){
        //this.rol.add(rol);
    }

    public void quitarRol(Rol rol){
        //this.rol.remove(rol);
    }

    //------------------------------
    //------------------------------
    //------------------------------
}
