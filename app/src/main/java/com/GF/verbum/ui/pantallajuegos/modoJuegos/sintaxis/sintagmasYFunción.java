package com.GF.verbum.ui.pantallajuegos.modoJuegos.sintaxis;

public class sintagmasYFunción {
    private String palabras;
    private String tipoSintagma;
    private String funcion;
    private int priorityOrder;

    public sintagmasYFunción(String palabras, String tipoSintagma, String funcion, int priorityOrder) {
        this.palabras = palabras;
        this.tipoSintagma = tipoSintagma;
        this.funcion = funcion;
        this.priorityOrder = priorityOrder;
    }

    public String getPalabras() {
        return palabras;
    }

    public void setPalabras(String palabras) {
        this.palabras = palabras;
    }

    public String getTipoSintagma() {
        return tipoSintagma;
    }

    public void setTipoSintagma(String tipoSintagma) {
        this.tipoSintagma = tipoSintagma;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public int getPriorityOrder() {
        return priorityOrder;
    }

    public void setPriorityOrder(int priorityOrder) {
        this.priorityOrder = priorityOrder;
    }
}
