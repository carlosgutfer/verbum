package com.GF.verbum.DB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.GF.verbum.DB.Entities.fratipEntity;

import java.util.List;

@Dao
public interface FratipDao {
    @Insert void insert(fratipEntity fratip);

    @Query("Select * from FRATIP where idFrase = :ID ")
    LiveData<List<fratipEntity>> selectFraTip(int ID);

    @Query("Delete from fratip") void deleteAll();
}
