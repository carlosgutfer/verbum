package com.GF.verbum.DB.Entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "compuestas")
public class compuestasEntity {
    @PrimaryKey
    @ForeignKey(entity = frasesEntity.class, parentColumns = "idFrase",childColumns = "idFrase")
    private int idFrase;
    private String descripcion;

    public compuestasEntity(int idFrase, String descripcion) {
        this.idFrase = idFrase;
        this.descripcion = descripcion;
    }

    public int getIdFrase() {
        return idFrase;
    }

    public void setIdFrase(int idFrase) {
        this.idFrase = idFrase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
