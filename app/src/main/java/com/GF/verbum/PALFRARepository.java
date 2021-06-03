package com.GF.verbum;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.GF.verbum.DB.DAO.FrasesDao;
import com.GF.verbum.DB.DAO.PalfraDao;
import com.GF.verbum.DB.DAO.TiposDao;
import com.GF.verbum.DB.Entities.frasesEntity;
import com.GF.verbum.DB.Entities.palfraEntity;
import com.GF.verbum.DB.Entities.tiposEntity;
import com.GF.verbum.DB.TiposRoomDataBase;
import com.GF.verbum.DB.palaFraRoomDataBase;

import java.util.List;

public class PALFRARepository {

    private PalfraDao DAOPalFra;
    private LiveData<List<palfraEntity>> allPalFra;

    private TiposDao DAOTipos;
    private LiveData<String> tipo;
    private LiveData<List<tiposEntity>> allTipos;

   public PALFRARepository (Application application){
       palaFraRoomDataBase roomDataBase =  palaFraRoomDataBase.getRoomDataBase(application);
       DAOPalFra = roomDataBase.DAO();

       TiposRoomDataBase roomDataBase1 = TiposRoomDataBase.getRoomDataBase(application);
       DAOTipos = roomDataBase1.DAO();
       allTipos = DAOTipos.getAllTipos();
   }

    public LiveData<List<palfraEntity>> getAllPalFra(int id) {
        allPalFra = DAOPalFra.getPalFra(id);
        return allPalFra;
    }


    public LiveData<String> getTipo(int id) {
        tipo = DAOTipos.getTipos(id);
        return tipo;
    }

    public LiveData<List<tiposEntity>> getAllTipos(){

       return allTipos;
    }



}
