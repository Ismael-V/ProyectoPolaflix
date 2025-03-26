package com.unican.polaflix.dominio;

import jakarta.persistence.Embeddable;

@Embeddable
public class IBAN {
    protected char[] country = new char[2];
    protected char[] controlDigits = new char[2];
    protected char[] entityCode = new char[4];
    protected char[] officeCode = new char[4];
    protected char[] accountControlDigits = new char[2];
    protected char[] accountNumber = new char[10];

    public char[] getCountry() {
        return country;
    }

    public void setCountry(char[] country) {

        for (char c : country) {
            if(c < 'A' || c > 'Z'){
                new Exception("Los caracteres del pais no se corresponden con letras mayusculas validas.\n");
            }
        }

        this.country = country;
    }

    public char[] getControlDigits() {
        return controlDigits;
    }

    public void setControlDigits(char[] controlDigits) {

        for (char c : country) {
            if(c < '0' || c > '9'){
                new Exception("Los caracteres del numero de control no son numeros.\n");
            }
        }

        this.controlDigits = controlDigits;
    }

    public char[] getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(char[] entityCode) {

        for (char c : country) {
            if(c < '0' || c > '9'){
                new Exception("Los caracteres del numero de la entidad no son numeros.\n");
            }
        }

        this.entityCode = entityCode;
    }

    public char[] getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(char[] officeCode) {

        for (char c : country) {
            if(c < '0' || c > '9'){
                new Exception("Los caracteres del codigo de oficina no son numeros.\n");
            }
        }

        this.officeCode = officeCode;
    }

    public char[] getAccountControlDigits() {
        return accountControlDigits;
    }

    public void setAccountControlDigits(char[] accountControlDigits) {

        for (char c : country) {
            if(c < '0' || c > '9'){
                new Exception("Los caracteres de control del numero de oficina no son numeros.\n");
            }
        }

        this.accountControlDigits = accountControlDigits;
    }

    public char[] getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(char[] accountNumber) {

        for (char c : country) {
            if(c < '0' || c > '9'){
                new Exception("Los caracteres del numero de cuenta bancaria no son numeros.\n");
            }
        }

        this.accountNumber = accountNumber;
    }
}
