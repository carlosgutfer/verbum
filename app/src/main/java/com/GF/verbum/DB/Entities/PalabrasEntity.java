package com.GF.verbum.DB.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Palabras")
public class PalabrasEntity {
    @PrimaryKey
    @NonNull
    private String palabra;

    private boolean sustantivo;
    private boolean articulo;
    private boolean adjetivo;
    private boolean pronombre;
    private boolean verbo;
    private boolean adverbio;
    private boolean preposicion;
    private boolean conjuncion;
    private boolean interjeccion;

    public PalabrasEntity(@NonNull String palabra, boolean sustantivo, boolean articulo, boolean adjetivo, boolean pronombre, boolean verbo, boolean adverbio, boolean preposicion, boolean conjuncion, boolean interjeccion) {
        this.palabra = palabra;
        this.sustantivo = sustantivo;
        this.articulo = articulo;
        this.adjetivo = adjetivo;
        this.pronombre = pronombre;
        this.verbo = verbo;
        this.adverbio = adverbio;
        this.preposicion = preposicion;
        this.conjuncion = conjuncion;
        this.interjeccion = interjeccion;
    }

    @NonNull
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(@NonNull String palabra) {
        this.palabra = palabra;
    }

    public boolean isSustantivo() {
        return sustantivo;
    }

    public void setSustantivo(boolean sustantivo) {
        this.sustantivo = sustantivo;
    }

    public boolean isArticulo() {
        return articulo;
    }

    public void setArticulo(boolean articulo) {
        this.articulo = articulo;
    }

    public boolean isAdjetivo() {
        return adjetivo;
    }

    public void setAdjetivo(boolean adjetivo) {
        this.adjetivo = adjetivo;
    }

    public boolean isPronombre() {
        return pronombre;
    }

    public void setPronombre(boolean pronombre) {
        this.pronombre = pronombre;
    }

    public boolean isVerbo() {
        return verbo;
    }

    public void setVerbo(boolean verbo) {
        this.verbo = verbo;
    }

    public boolean isAdverbio() {
        return adverbio;
    }

    public void setAdverbio(boolean adverbio) {
        this.adverbio = adverbio;
    }

    public boolean isPreposicion() {
        return preposicion;
    }

    public void setPreposicion(boolean preposicion) {
        this.preposicion = preposicion;
    }

    public boolean isConjuncion() {
        return conjuncion;
    }

    public void setConjuncion(boolean conjuncion) {
        this.conjuncion = conjuncion;
    }

    public boolean isInterjeccion() {
        return interjeccion;
    }

    public void setInterjeccion(boolean interjeccion) {
        this.interjeccion = interjeccion;
    }
}

