package com.GF.verbum.DB.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.GF.verbum.DB.Entities.compuestasEntity;

import java.util.List;

@Dao
public interface CompuestasDao {

    @Insert void insert(compuestasEntity compuestos);

    @Query("SELECT * FROM compuestas WHERE idFrase = :id")
    List<compuestasEntity> selectCompuesta(int id);

    @Query("DELETE FROM compuestas")
    void deleteAll();
}
