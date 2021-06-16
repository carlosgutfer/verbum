package com.GF.verbum.DB.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "palfra", primaryKeys = {"idFrase","idPalabra","position"},foreignKeys = {
         @ForeignKey(entity = frasesEntity.class,parentColumns = "idFrase",childColumns = "idFrase")
        ,@ForeignKey(entity = PalabrasEntity.class,parentColumns = "idPalabra",childColumns = "idPalabra")})
public class palfraEntity {

    private int idFrase;
    @ColumnInfo(index = true) //necesario para que no salte el warning
    private int idPalabra;
    private int position;

    public palfraEntity(int idFrase, int idPalabra, int position) {
        this.idFrase = idFrase;
        this.idPalabra = idPalabra;
        this.position = position;
    }

    public int getIdFrase() {
        return idFrase;
    }

    public void setIdFrase(int idFrase) {
        this.idFrase = idFrase;
    }

    public int getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(int idPalabra) {
        this.idPalabra = idPalabra;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
