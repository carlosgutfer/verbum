package com.GF.verbum.DB.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "FRATIP", primaryKeys = {"idFrase","idTipo"})

public class fratipEntity {
    @ForeignKey(entity = frasesEntity.class,parentColumns = "idFrase",childColumns = "idFrase")
    private int idFrase;
    @ForeignKey( entity = tiposEntity.class, parentColumns = "idOSimples", childColumns = "idTipo")
    private int idTipo;
    private int position;

    public fratipEntity(int idFrase, int idTipo,int position) {
        this.idFrase = idFrase;
        this.idTipo = idTipo;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
