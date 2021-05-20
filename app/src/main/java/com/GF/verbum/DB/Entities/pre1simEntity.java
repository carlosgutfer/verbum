package com.GF.verbum.DB.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "PRE1SIM", primaryKeys = {"idOsimple","pred1"})
public class pre1simEntity {
    @ForeignKey(entity = simplesEntity.class,parentColumns = "idOSimples",childColumns = "idOSimples")
    private int idOsimples;
    @ForeignKey(entity = compuestasEntity.class,parentColumns = "pred1",childColumns = "pred1")
    private int pred1;



    public pre1simEntity(int idOsimples, int pred) {
        this.idOsimples = idOsimples;
        this.pred1 = pred;
    }

    public int getIdOsimples() {
        return idOsimples;
    }

    public void setIdOsimples(int idOsimples) {
        this.idOsimples = idOsimples;
    }

    public int getPred() {
        return pred1;
    }

    public void setPred(int pred) {
        this.pred1 = pred;
    }
}
