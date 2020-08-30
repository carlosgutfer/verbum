package com.GF.verbum.DB.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.GF.verbum.DB.Entities.PreguntasEntity;

import java.util.List;

@Dao
public interface PreguntasDao {

    @Insert void insert(PreguntasEntity pregunta);

    @Query("SELECT * FROM Preguntas")
    LiveData<List<PreguntasEntity>> getAllPreguntas();

    @Query (" DELETE FROM Preguntas") void deleteAll();

}
