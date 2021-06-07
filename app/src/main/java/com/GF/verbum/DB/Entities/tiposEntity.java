package com.GF.verbum.DB.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import javax.annotation.Nullable;

@Entity(tableName = "tipos")
public class tiposEntity {
    @PrimaryKey(autoGenerate = true)
    private int idTipo;
    private String descriptcion;
    @Nullable
    private int idDependiente;

    public tiposEntity(String descriptcion, Integer idDependiente) {
        this.descriptcion = descriptcion;
        this.idDependiente = idDependiente;
    }
    @Ignore
    public tiposEntity(String descriptcion) {
        this.descriptcion = descriptcion;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdDependiente() {
        return idDependiente;
    }

    public void setIdDependiente(int idDependiente) {
        this.idDependiente = idDependiente;
    }

    public String getDescriptcion() {
        return descriptcion;
    }

    public void setDescriptcion(String descriptcion) {
        this.descriptcion = descriptcion;
    }
}
