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

    @Query("Select descriptcion FROM TIPOS WHERE idOSimples = :id")
        LiveData<String> getTipos(int id);

    @Query (" DELETE FROM TIPOS") void deleteAll();

    @Query("SELECT * FROM  tipos")
        LiveData<List<tiposEntity>> getAllTipos();
}
