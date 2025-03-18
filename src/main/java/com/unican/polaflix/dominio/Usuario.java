package com.unican.polaflix.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.util.List;


public class Usuario {

    protected String nombre;
    protected String password;
    protected IBAN cuenta;

    protected List<Factura> facturas;

    protected List<Serie> seriesEnCurso;
    protected List<Serie> seriesPendientes;
    protected List<Serie> seriesTerminadas;

    public Usuario(String nombre, String password, IBAN cuenta){
        this.seriesEnCurso = new ArrayList<>();
        this.seriesPendientes = new ArrayList<>();
        this.seriesTerminadas = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.nombre = nombre;
        this.password = password;
        this.cuenta = cuenta;
    }

    //------------------------------
    //----> Setters y Getters <-----
    //------------------------------

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public List<Serie> getSeriesEnCurso() {
        return seriesEnCurso;
    }

    public void setSeriesEnCurso(List<Serie> seriesEnCurso) throws Exception {
        
        for (Serie serie : seriesPendientes) {
            if(seriesEnCurso.contains(serie)) throw new Exception("Las listas de series han colisionado al actualizar series en curso.\n");
        }

        for (Serie serie : seriesTerminadas) {
            if(seriesEnCurso.contains(serie)) throw new Exception("Las listas de series han colisionado al actualizar series en curso.\n");
        }

        this.seriesEnCurso = seriesEnCurso;
    }

    public List<Serie> getSeriesPendientes() {
        return seriesPendientes;
    }

    public void setSeriesPendientes(List<Serie> seriesPendientes) throws Exception {
        
        for (Serie serie : seriesEnCurso) {
            if(seriesPendientes.contains(serie)) throw new Exception("Las listas de series han colisionado al actualizar series pendientes.\n");
        }

        for (Serie serie : seriesTerminadas) {
            if(seriesPendientes.contains(serie)) throw new Exception("Las listas de series han colisionado al actualizar series pendientes.\n");
        }

        this.seriesPendientes = seriesPendientes;
    }

    public List<Serie> getSeriesTerminadas() {
        return seriesTerminadas;
    }

    public void setSeriesTerminadas(List<Serie> seriesTerminadas) throws Exception {

        for (Serie serie : seriesEnCurso) {
            if(seriesTerminadas.contains(serie)) throw new Exception("Las listas de series han colisionado al actualizar series terminadas.\n");
        }

        for (Serie serie : seriesPendientes) {
            if(seriesTerminadas.contains(serie)) throw new Exception("Las listas de series han colisionado al actualizar series terminadas.\n");
        }

        this.seriesTerminadas = seriesTerminadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public IBAN getCuenta() {
        return cuenta;
    }

    public void setCuenta(IBAN cuenta) {
        this.cuenta = cuenta;
    }

    //------------------------------
    //------------------------------
    //------------------------------

    public Factura crearFactura(){

        //Generamos una factura con la fecha de ahora
        Factura nuevaFactura = new Factura(this, LocalDate.now());
        this.facturas.add(nuevaFactura);

        //Devuelve la referencia
        return nuevaFactura;
    }

    public void anyadirFactura(Factura factura){
        facturas.remove(factura);
    }

    public void quitarFactura(Factura factura){
        facturas.remove(factura);
    }

    public void anyadirSerie(Serie serie){
        //Si no esta la serie en curso, ni pendiente, ni terminada la anyadimos
        if(!seriesEnCurso.contains(serie) && !seriesPendientes.contains(serie) && !seriesTerminadas.contains(serie)){
            seriesEnCurso.add(serie);
        }
    }

    public void verCapitulo(Capitulo capitulo, boolean fueComprado){
        //Obtenemos la serie a la que pertenece el capitulo
        Serie serie = capitulo.getTemporada().getSerie();

        //Si la serie esta en curso, la pasamos a pendientes
        if(seriesEnCurso.contains(serie)){
            seriesEnCurso.remove(serie);
            seriesPendientes.add(serie);
        }

        //Si la serie no esta terminada
        if(!seriesTerminadas.contains(serie)){
            //Generamos la lista de todos los capitulos
            List<Capitulo> todosLosCapitulos = new ArrayList<>();
            for (Temporada t : serie.getTemporadas()) {
                todosLosCapitulos.addAll(t.getCapitulos());
            }

            //Ordenamos de menos reciente a mas reciente
            Collections.sort(todosLosCapitulos);

            //Si el ultimo capitulo de todos los que hay coincide con el que se esta visualizando ahora
            if(todosLosCapitulos.get(todosLosCapitulos.size() - 1).equals(capitulo)){
                //Movemos a series terminadas, la serie en cuestion
                seriesPendientes.remove(serie);
                seriesTerminadas.add(serie);
            }
        }

        //Ahora se generara la entrada de la factura correspondiente, obtenemos primero la fecha
        LocalDate ahora = LocalDate.now();

        //Esta sera la factura que gestionemos
        Factura factura = null;

        //Para todas las facturas que hay en el sistema
        for(Factura f : this.facturas){

            //Si encontramos la factura de este mes y anyo, la tomamos
            if(f.getAnyo() == ahora.getYear() && f.getMes() == ahora.getMonthValue()){
                factura = f;
                break;
            }

        }

        //Si no se ha encontrado la generamos
        if(factura == null){
            factura = this.crearFactura();
        }

        //Con esta factura nueva, generamos la entrada
        factura.anyadirEntrada(ahora, serie.getNombreSerie(), capitulo.getTemporada().getNumeroTemporada(), capitulo.getNumeroCapitulo(), serie.getTipoSerie(), fueComprado);
    }

    public List<Capitulo> obtenerCapitulosVistos(Serie serie){
        List<Capitulo> capitulosVistos = new ArrayList<>();

        //Para cada factura
        for (Factura factura : this.facturas) {

            //Para cada entrada en la factura
            for (EntradaFactura entradaFactura : factura.getEntradas()) {

                //Si la entrada se corresponde con la serie que se consulta
                if(serie.getNombreSerie() == entradaFactura.getNombreSerie()){

                    //Intentamos acceder al capitulo concreto
                    Temporada temporada = serie.getTemporadaByNumber(entradaFactura.getNumeroTemporada());
                    if(temporada != null){

                        Capitulo capitulo = temporada.getCapituloByNumber(entradaFactura.getNumeroCapitulo());

                        //Anyadimos el capitulo en cuestion de existir aun
                        if(capitulo != null){
                            capitulosVistos.add(capitulo);
                        }
                    }   
                }
            }
        }

        //Devolvemos la lista de capitulos vistos
        return capitulosVistos;
    }
}
