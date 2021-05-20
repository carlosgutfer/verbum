package com.GF.verbum.DB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.GF.verbum.DB.Entities.pre1simEntity;

import java.util.List;

@Dao
public interface Pre1SimDAO {
    @Insert void insert(pre1simEntity pr1sim);

    @Query("SELECT * FROM PRE1SIM WHERE idOsimples = :ID")
    LiveData<List<pre1simEntity>> selectpre1sim(int ID);

    @Query("DELETE FROM PRE1SIM") void deleteAll();
}
