package com.GF.verbum.DB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.GF.verbum.DB.Entities.PalabrasEntity;

import java.util.List;

@Dao

public interface PalabrasDao {
    @Insert
    void insert(PalabrasEntity palabra);

    @Query("SELECT * FROM Palabras")
    LiveData<List<PalabrasEntity>> getAllPalabras();


    @Query (" DELETE FROM Palabras") void deleteAll();

    @Query("Select palabra from Palabras where idPalabra = :ID")
    String getPalabraFrase(int ID);

}
