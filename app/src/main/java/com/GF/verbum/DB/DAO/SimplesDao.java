package com.GF.verbum.DB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.GF.verbum.DB.Entities.simplesEntity;

import java.util.List;

@Dao
public interface SimplesDao {
    @Insert
    void insert(simplesEntity simple);

    @Query("Select descriptcion FROM simples WHERE idOSimples = :id")
        String getSimples(int id);

    @Query (" DELETE FROM simples") void deleteAll();

}
