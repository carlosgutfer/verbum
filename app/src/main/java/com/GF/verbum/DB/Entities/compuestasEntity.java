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
    private int pred1;
    private int pred2;


    public compuestasEntity(int idFrase, String descripcion, int pred1, int pred2) {
        this.idFrase = idFrase;
        this.descripcion = descripcion;
        this.pred1 = pred1;
        this.pred2 = pred2;
    }

    public int getPred1() {
        return pred1;
    }

    public void setPred1(int pred1) {
        this.pred1 = pred1;
    }

    public int getPred2() {
        return pred2;
    }

    public void setPred2(int pred2) {
        this.pred2 = pred2;
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
