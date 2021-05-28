package com.GF.verbum;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.GF.verbum.DB.DAO.FrasesDao;
import com.GF.verbum.DB.DAO.PalabrasDao;
import com.GF.verbum.DB.DAO.PreguntasDao;
import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.PreguntasEntity;
import com.GF.verbum.DB.Entities.frasesEntity;
import com.GF.verbum.DB.FrasesRoomDataBase;
import com.GF.verbum.DB.PalabrasRoomDataBase;
import com.GF.verbum.DB.PreguntasRoomDataBase;

import java.util.List;

public class PalabraRepository {

    private PreguntasDao DaoPreguntas;
    private LiveData<List<PreguntasEntity>> allPreguntas;

    private PalabrasDao DaoPalabras;
    private LiveData<List<PalabrasEntity>> allPalabras;


    private FrasesDao DAOfrases;
    private LiveData<List<frasesEntity>> allFrases;


    public PalabraRepository(Application application) {
        PalabrasRoomDataBase dbPalabras = PalabrasRoomDataBase.getRoomDataBase(application);
        DaoPalabras=dbPalabras.DAO();
        allPalabras=DaoPalabras.getAllPalabras();

        PreguntasRoomDataBase dbPreguntas = PreguntasRoomDataBase.getRoomDataBase(application);
        DaoPreguntas =dbPreguntas.DAO();
        allPreguntas=DaoPreguntas.getAllPreguntas();

        FrasesRoomDataBase dbFrases = FrasesRoomDataBase.getRoomDataBase(application);
        DAOfrases=dbFrases.DAO();
        allFrases=DAOfrases.getAllFrases();

    }

    public LiveData<List<PreguntasEntity>> getAllPreguntas(){
        return allPreguntas;
    }

    public LiveData<List<PalabrasEntity>> getAll(){
        return  allPalabras;}

    public LiveData<List<frasesEntity>> getAllFrases(){
        return allFrases;
    }


}
