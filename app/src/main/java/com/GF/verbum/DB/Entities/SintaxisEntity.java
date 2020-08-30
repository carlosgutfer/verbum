package com.GF.verbum.DB.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Sintaxis")
public class SintaxisEntity {
    @PrimaryKey
    @NonNull
    private String nombre;
    private String texto;

    public SintaxisEntity(@NonNull String nombre, String texto) {
        this.nombre = nombre;
        this.texto = texto;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
