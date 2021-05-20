package com.GF.verbum.DB.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "PRE2SIM", primaryKeys = {"idOsimple","pred2"})
public class pre2simEntity {
    @ForeignKey(entity = simplesEntity.class,parentColumns = "idOSimples",childColumns = "idOSimples")
    private int idOsimples;
    @ForeignKey(entity = compuestasEntity.class,parentColumns = "pred2",childColumns = "pred2")

    private int pred2;



    public pre2simEntity(int idOsimples, int pred) {
        this.idOsimples = idOsimples;
        this.pred2 = pred;
    }

    public int getIdOsimples() {
        return idOsimples;
    }

    public void setIdOsimples(int idOsimples) {
        this.idOsimples = idOsimples;
    }

    public int getPred() {
        return pred2;
    }

    public void setPred(int pred) {
        this.pred2 = pred;
    }
}