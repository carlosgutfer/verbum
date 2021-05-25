package com.GF.verbum.DB.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.GF.verbum.DB.Entities.tiposEntity;

@Dao
public interface TiposDao {
    @Insert
    void insert(tiposEntity simple);

    @Query("Select descriptcion FROM tiposEntity WHERE idOSimples = :id")
        String getSimples(int id);

    @Query (" DELETE FROM tiposEntity") void deleteAll();

}
