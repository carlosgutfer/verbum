package com.GF.verbum.DB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.GF.verbum.DB.Entities.palfraEntity;

import java.util.List;

@Dao
public interface PalfraDao {

    @Insert void insert(palfraEntity palfra);

    @Query("SELECT * FROM PALFRA WHERE idFrase = :ID")
    LiveData<List<palfraEntity>> getPalFra(int ID);

    @Query("DELETE FROM PALFRA")
    void deleteAll();


}