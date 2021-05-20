package com.GF.verbum.DB.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.GF.verbum.DB.Entities.frasimEntity;

import java.util.List;

@Dao
public interface FrasimDao {
    @Insert
    void insert(frasimEntity frasim);

    @Query("Select * from frasim where idFrase = :id")
    List<frasimEntity> getInfoSim(int id);

    @Query("Delete  from frasim") void deleteAll();
}
