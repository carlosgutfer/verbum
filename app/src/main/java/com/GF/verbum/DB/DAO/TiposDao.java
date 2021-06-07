package com.GF.verbum.DB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.GF.verbum.DB.Entities.tiposEntity;

import java.util.List;

@Dao
public interface TiposDao {
    @Insert
    void insert(tiposEntity simple);

    @Query("Select * FROM TIPOS WHERE idTipo = :id")
        LiveData<List<tiposEntity>> getTipos(int id);


    @Query("Select * FROM TIPOS WHERE idDependiente = :id")
        LiveData<List<tiposEntity>> getDependientes(int id);

    @Query (" DELETE FROM TIPOS") void deleteAll();

    @Query("SELECT * FROM  tipos")
        LiveData<List<tiposEntity>> getAllTipos();
}
