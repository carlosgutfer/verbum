package com.GF.verbum;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.GF.verbum.DB.DAO.FrasesDao;
import com.GF.verbum.DB.DAO.FratipDao;
import com.GF.verbum.DB.DAO.PalabrasDao;
import com.GF.verbum.DB.DAO.PalfraDao;
import com.GF.verbum.DB.DAO.PreguntasDao;
import com.GF.verbum.DB.DAO.TiposDao;
import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.PreguntasEntity;
import com.GF.verbum.DB.Entities.frasesEntity;
import com.GF.verbum.DB.Entities.fratipEntity;
import com.GF.verbum.DB.Entities.palfraEntity;
import com.GF.verbum.DB.Entities.tiposEntity;
import com.GF.verbum.DB.PalabrasRoomDataBase;
import com.GF.verbum.DB.PreguntasRoomDataBase;

import java.util.List;

public class PalabraRepository {

    private PreguntasDao DaoPreguntas;
    private LiveData<List<PreguntasEntity>> allPreguntas;

    private PalabrasDao DaoPalabras;
    private LiveData<List<PalabrasEntity>> allPalabras;
    private LiveData<List<String>> palfra;


    public PalabraRepository(Application application) {
        PalabrasRoomDataBase dbPalabras = PalabrasRoomDataBase.getRoomDataBase(application);
        DaoPalabras=dbPalabras.DAOPALABRAS();
        allPalabras=DaoPalabras.getAllPalabras();

        PreguntasRoomDataBase dbPreguntas = PreguntasRoomDataBase.getRoomDataBase(application);
        DaoPreguntas =dbPreguntas.DAO();
        allPreguntas=DaoPreguntas.getAllPreguntas();



    }

    public LiveData<List<PreguntasEntity>> getAllPreguntas(){
        return allPreguntas;
    }

    public LiveData<List<PalabrasEntity>> getAllPalabras(){
        return  allPalabras;}




}
