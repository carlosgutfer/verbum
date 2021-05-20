package com.GF.verbum.DB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.GF.verbum.DB.Entities.pre2simEntity;

import java.util.List;

@Dao
public interface Pre2SimDao {
    @Insert
    void insert(pre2simEntity pr1sim);

    @Query("SELECT * FROM PRE1SIM WHERE idOsimples = :ID")
            LiveData<List<pre2simEntity>> selectpre1sim(int ID);

    @Query("DELETE FROM PRE2SIM") void deleteAll();
}
