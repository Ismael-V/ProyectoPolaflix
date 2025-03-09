package com.unican.polaflix;

import java.util.ArrayList;
import java.util.List;

public class Factura {

    protected Usuario deudor;
    protected int importeTotalCent = 0;
    private boolean comproBono = false;
    
    protected List<EntradaFactura> entradas;

    public Factura(Usuario deudor){
        //Inicializamos las entradas
        this.entradas = new ArrayList<>();

        //Ponemos al deudor
        this.deudor = deudor;

        //Le adjuntamos su factura
        deudor.anyadirFactura(this);
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
        
        //Se la transferimos al nuevo
        deudor.anyadirFactura(this);

        //Cambiamos de deudor
        this.deudor = deudor;
    }

    public int getImporteTotalCent() {
        return importeTotalCent;
    }

    //------------------------------
    //------------------------------
    //------------------------------

    public void borrarFactura(){
        //Eliminamos la factura del deudor
        deudor.quitarFactura(this);

        //Decimos que esta factura no se asocia a ningun deudor
        deudor = null;
    }

    public void anyadirEntrada(EntradaFactura entrada){
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
