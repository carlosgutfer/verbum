package com.GF.verbum.DB.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "FRATIP", primaryKeys = {"idFrase","idTipo"})
public class fratipEntity {
    @ForeignKey(entity = frasesEntity.class,parentColumns = "idFrase",childColumns = "idColums")
    private int idFrase;
    @ForeignKey( entity = tiposEntity.class, parentColumns = "idTipo", childColumns = "idTipo")
    private int idTipo;

    public fratipEntity(int idFrase, int idTipo) {
        this.idFrase = idFrase;
        this.idTipo = idTipo;
    }

    public int getIdFrase() {
        return idFrase;
    }

    public void setIdFrase(int idFrase) {
        this.idFrase = idFrase;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
}
