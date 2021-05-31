package com.GF.verbum.DB.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "frases")
public class frasesEntity {
    @PrimaryKey (autoGenerate = true)
    private int idFrase;
    private boolean compuesta;

    public frasesEntity(boolean compuesta) {
        this.compuesta = compuesta;
    }

    public int getIdFrase() {
        return idFrase;
    }

    public void setIdFrase(int idFrase) {
        this.idFrase = idFrase;
    }

    public boolean isCompuesta() {
        return compuesta;
    }

    public void setCompuesta(boolean compuesta) {
        this.compuesta = compuesta;
    }
}
