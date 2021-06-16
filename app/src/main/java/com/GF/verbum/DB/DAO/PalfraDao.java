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

    @Query("SELECT * FROM PALFRA WHERE idFrase = :arg0 order by position")
        LiveData<List<palfraEntity>> getPalFra(int arg0);

    @Query("DELETE FROM PALFRA")
    void deleteAll();


}
