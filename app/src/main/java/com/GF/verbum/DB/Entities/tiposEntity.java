package com.GF.verbum.DB.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "simples")
public class tiposEntity {
    @PrimaryKey(autoGenerate = true)
    private int idOSimples;
    private String descriptcion;

    public tiposEntity(String descriptcion) {
        this.descriptcion = descriptcion;
    }

    public int getIdOSimples() {
        return idOSimples;
    }

    public void setIdOSimples(int idOSimples) {
        this.idOSimples = idOSimples;
    }


    public String getDescriptcion() {
        return descriptcion;
    }

    public void setDescriptcion(String descriptcion) {
        this.descriptcion = descriptcion;
    }
}
