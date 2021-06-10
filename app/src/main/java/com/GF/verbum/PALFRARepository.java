package com.GF.verbum;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.GF.verbum.DB.DAO.FrasesDao;
import com.GF.verbum.DB.DAO.FratipDao;
import com.GF.verbum.DB.DAO.PalabrasDao;
import com.GF.verbum.DB.DAO.PalfraDao;
import com.GF.verbum.DB.DAO.TiposDao;
import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.frasesEntity;
import com.GF.verbum.DB.Entities.fratipEntity;
import com.GF.verbum.DB.Entities.palfraEntity;
import com.GF.verbum.DB.Entities.tiposEntity;
import com.GF.verbum.DB.PalabrasRoomDataBase;

import java.util.List;

public class PALFRARepository {

    private PalfraDao DAOPalFra;
    private LiveData<List<palfraEntity>> allPalFra;
    private LiveData<List<String>> palfra;


    private TiposDao DAOTipos;
    private LiveData<List<tiposEntity>> tipo;
    private LiveData<List<tiposEntity>> allTipos;

    private FratipDao DAOFraTip;
    private LiveData<List<fratipEntity>> tipFra;

    private FrasesDao DAOFrases;
    private LiveData<List<frasesEntity>> allFrases;

    private PalabrasDao DAOPALABRAS;
    private LiveData<List<PalabrasEntity>> allPalabras;



    public PALFRARepository(Application application) {
        PalabrasRoomDataBase roomDataBase = PalabrasRoomDataBase.getRoomDataBase(application);
        DAOPalFra = roomDataBase.DAOPALFRA();
        DAOTipos = roomDataBase.DAOTIPOS();
        DAOFraTip = roomDataBase.DAOFRATIP();
        DAOFrases = roomDataBase.DAOFRASES();
        DAOPALABRAS=roomDataBase.DAOPALABRAS();

    }

    //Obtengo todas las palabras de la frase con ese id
    public LiveData<List<palfraEntity>> getAllPalFra(int id) {
        allPalFra = DAOPalFra.getPalFra(id);
        return allPalFra;
    }
    //Obtengo todas las palabras
    public LiveData<List<PalabrasEntity>> getAllPalabras(){
         allPalabras=DAOPALABRAS.getAllPalabras();
         return  allPalabras;}

//Obtengo todas las frases
    public LiveData<List<frasesEntity>> getAllFrases(){
        allFrases =DAOFrases.getAllFrases();
        return  allFrases;
    }

//Obtengo  los tipos de la ID que paso
    public LiveData<List<tiposEntity>> getTipo(int id) {
        tipo = DAOTipos.getTipos(id);
        return tipo;
    }
//Obtengo todos los tipos de la frase
    public LiveData<List<fratipEntity>> getFraTip(int id) {
        tipFra = DAOFraTip.selectFraTip(id);
        return tipFra;
    }
//Obtengo todos los tipos
    public LiveData<List<tiposEntity>> getAllTipos() {
        allTipos =DAOTipos.getAllTipos();
        return  allTipos;
    }
//Obtengo los tipos hijos
    public LiveData<List<tiposEntity>> getSonTip(int id) {
        tipo = DAOTipos.getDependientes(id);
        return tipo;
    }

    public LiveData<List<String>> getPalabrasFrases(int id){
        palfra = DAOPALABRAS.getPalabrasFrase(id);
        return  palfra;
    }


}
