package com.GF.verbum.DB.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "frasim", primaryKeys = {"idFrase","idOSimple"})
public class frasimEntity {
    @ForeignKey(entity =frasesEntity.class, parentColumns = "idFrase", childColumns ="idFrase" )
    private int idFrase;
    @ForeignKey(entity = simplesEntity.class, parentColumns = "idOSimples", childColumns = "idOSimple")
    private int idOSimple;

    public frasimEntity(int idFrase, int idOSimple) {
        this.idFrase = idFrase;
        this.idOSimple = idOSimple;
    }

    public int getIdFrase() {
        return idFrase;
    }

    public void setIdFrase(int idFrase) {
        this.idFrase = idFrase;
    }

    public int getIdOSimple() {
        return idOSimple;
    }

    public void setIdOSimple(int idOSimple) {
        this.idOSimple = idOSimple;
    }
}
