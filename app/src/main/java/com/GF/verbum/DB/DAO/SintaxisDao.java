package com.GF.verbum.DB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.GF.verbum.DB.Entities.SintaxisEntity;

import java.util.List;

@Dao
public interface SintaxisDao {

    @Insert void insert(SintaxisEntity palabra);

    @Query("SELECT * FROM Sintaxis")
    LiveData<List<SintaxisEntity>> getAllTipos();

    @Query (" DELETE FROM Sintaxis") void deleteAll();


}
