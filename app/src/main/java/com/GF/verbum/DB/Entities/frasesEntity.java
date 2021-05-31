package com.GF.verbum.DB.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "FRASES")
public class frasesEntity {
    @PrimaryKey(autoGenerate = true)
    private int idFrase;
    private boolean oCompuesta;

    public frasesEntity(boolean oCompuesta) {
        this.oCompuesta = oCompuesta;
    }

    public int getIdFrase() {
        return idFrase;
    }

    public void setIdFrase(int idFrase) {
        this.idFrase = idFrase;
    }

    public boolean isoCompuesta() {
        return oCompuesta;
    }

    public void setoCompuesta(boolean oCompuesta) {
        this.oCompuesta = oCompuesta;
    }
}
