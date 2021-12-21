package com.GF.verbum.DB.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "FRATIP", primaryKeys = {"idFrase","idTipo","position"},
        foreignKeys = {@ForeignKey(entity = frasesEntity.class,parentColumns = "idFrase",childColumns = "idFrase"),
                       @ForeignKey( entity = tiposEntity.class, parentColumns = "idTipo", childColumns = "idTipo")})

public class fratipEntity {
    private int idFrase;
    @ColumnInfo(index = true) //necesario para que no salte el warning
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
