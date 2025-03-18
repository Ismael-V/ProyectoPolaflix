package com.unican.polaflix.dominio;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class Factura {

    protected Usuario deudor;
    protected int importeTotalCent = 0;
    private boolean comproBono = false;
    protected int mes;
    protected int anyo;
    
    protected List<EntradaFactura> entradas;

    public Factura(Usuario deudor, LocalDate fecha){
        //Inicializamos las entradas
        this.entradas = new ArrayList<>();

        //Ponemos al deudor
        this.deudor = deudor;

        //Colocamos el mes y anyo
        this.mes = fecha.getMonthValue();
        this.anyo = fecha.getYear();
    }

    //------------------------------
    //----> Setters y Getters <-----
    //------------------------------

    public List<EntradaFactura> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<EntradaFactura> entradas) {
        //Colocamos las nuevas entradas
        this.entradas = entradas;

        //Recalculamos el importe total
        importeTotalCent = 0;

        //Para cada entrada de la factura
        for (EntradaFactura entradaFactura : entradas) {

            //Si se indica que fue comprado el capitulo, anyadimos el importe que corresponda
            if(entradaFactura.getFueComprado()) this.importeTotalCent += entradaFactura.getImporteCentimos();
            //En cambio, si no fue comprado y no tuvimos en cuenta el bono
            else if(!comproBono){

                //Indicamos que compramos bono y cargamos dicho importe
                this.comproBono = true;
                this.importeTotalCent += 2000;
            }
        }
    }

    public Usuario getDeudor() {
        return deudor;
    }

    public void setDeudor(Usuario deudor) {
        //Le quitamos la factura al deudor previo
        if(this.deudor != null){
            this.deudor.quitarFactura(this);
        }
        
        //Se la transferimos al nuevo de existir el usuario
        if(deudor != null){
            deudor.anyadirFactura(this);
        }
        
        //Cambiamos de deudor
        this.deudor = deudor;
    }

    public int getImporteTotalCent() {
        return importeTotalCent;
    }

    public int getMes() {
        return mes;
    }

    public int getAnyo() {
        return anyo;
    }

    //------------------------------
    //------------------------------
    //------------------------------

    public void anyadirEntrada(LocalDate fechaVisualizacion, String nombreSerie, int numeroTemporada, int numeroCapitulo, Categoria tipoSerie, boolean fueComprado){

        //Creamos la entrada
        EntradaFactura entrada = new EntradaFactura(fechaVisualizacion, nombreSerie, numeroTemporada, numeroCapitulo, tipoSerie, fueComprado);

        //Anyadimos una entrada
        entradas.add(entrada);

        //Si no compro la ultima visualizacion y no queda registrado que compro bono
        if(!entrada.fueComprado && !this.comproBono){

            //Registramos el importe por haber comprado el bono
            importeTotalCent += 2000;

            //Marcamos como que si que ha comprado el bono
            this.comproBono = true;
        }
        
        //Si no tiene bono comprado reflejamos el importe que corresponda
        if(!comproBono){
            importeTotalCent += entrada.getImporteCentimos();
        }

    }

    public void quitarEntrada(EntradaFactura entrada){

        //Quitamos la entrada
        entradas.remove(entrada);

        //Retiramos el importe que supuso la entrada de haberlo hecho
        if(entrada.fueComprado){

            importeTotalCent -= entrada.getImporteCentimos();

        //En caso contrario, revisamos que el cliente siga teniendo alguna entrada que
        //refleje que compro bono, de lo contrario, recalculamos el importe de manera acorde
        }else{

            //No se sabe todavia si compro o no bono
            this.comproBono = false;

            //Calcularemos el importe que se deberia cobrar al usuario
            int importeTeorico = 0;

            for(EntradaFactura entradaFactura : entradas) {

                //Si alguna no fue comprada entonces compro bono
                this.comproBono |= !entradaFactura.getFueComprado();

                //Si compramos el bono dejamos de comprobar
                if(this.comproBono) break;

                //Anyadimos el importe teorico
                importeTeorico += entradaFactura.getImporteCentimos();
            }

            //Si al final no compro bono, reflejamos el importe real
            if(!comproBono){
                this.importeTotalCent = importeTeorico;
            }
        }
    }
}
