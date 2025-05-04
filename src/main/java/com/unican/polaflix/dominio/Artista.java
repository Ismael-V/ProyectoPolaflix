package com.unican.polaflix.dominio;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.unican.polaflix.restctrl.Views;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@JsonPropertyOrder({"nombre", "rol"})
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_artista;

    @JsonProperty("nombre")
    @JsonView({Views.VistaSerie.class})
    protected String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("rol")
    @JsonView({Views.VistaSerie.class})
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
