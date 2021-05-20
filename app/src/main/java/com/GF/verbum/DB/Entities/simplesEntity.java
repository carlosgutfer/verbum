package com.GF.verbum.DB.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "simples")
public class simplesEntity   {
    @PrimaryKey
    private int idOSimples;
    private int value;
    private String descriptcion;

    public simplesEntity(int idOSimples, int value, String descriptcion) {
        this.idOSimples = idOSimples;
        this.value = value;
        this.descriptcion = descriptcion;
    }

    public int getIdOSimples() {
        return idOSimples;
    }

    public void setIdOSimples(int idOSimples) {
        this.idOSimples = idOSimples;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescriptcion() {
        return descriptcion;
    }

    public void setDescriptcion(String descriptcion) {
        this.descriptcion = descriptcion;
    }
}
