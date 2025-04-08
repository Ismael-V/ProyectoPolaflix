package com.unican.polaflix.dominio;

import java.util.HashSet;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ElementCollection
    protected Set<Rol> rol;

    protected Artista(){}

    public Artista(String nombre){
        
        //this.rol = new HashSet<>();
        this.nombre = nombre;
    }

    //------------------------------
    //----> Setters y Getters <-----
    //------------------------------

    public void anyadirRol(Rol rol){
        if(this.rol == null) this.rol = new HashSet<Rol>();
        this.rol.add(rol);
    }

    public void quitarRol(Rol rol){
        if(this.rol == null) this.rol = new HashSet<Rol>();
        this.rol.remove(rol);
    }

    //------------------------------
    //------------------------------
    //------------------------------
}
