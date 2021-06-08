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

    @Query("Select palabra from Palabras T1 inner join (select idPalabra from palfra where idFrase =:ID order by position ) T2 ON T1.idPalabra = T2.idPalabra")
    LiveData<List<String>> getPalabrasFrase(int ID);

}

 
