package com.GF.verbum.DB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.GF.verbum.DB.Entities.frasesEntity;

import java.util.List;

@Dao
public interface FrasesDao
{

    @Insert void insert(frasesEntity frase);

    @Query("Select * from frases ")
    LiveData<List<frasesEntity>> getAllFrases();

    @Query("DELETE FROM frases")
    void deleteAll();
}
