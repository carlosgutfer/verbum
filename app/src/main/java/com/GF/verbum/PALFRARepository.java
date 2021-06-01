package com.GF.verbum;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.GF.verbum.DB.DAO.FrasesDao;
import com.GF.verbum.DB.DAO.PalfraDao;
import com.GF.verbum.DB.Entities.frasesEntity;
import com.GF.verbum.DB.Entities.palfraEntity;
import com.GF.verbum.DB.palaFraRoomDataBase;

import java.util.List;

public class PALFRARepository {

    private PalfraDao DAOPalFra;
    private LiveData<List<palfraEntity>> allPalFra;

   public PALFRARepository (Application application){
       palaFraRoomDataBase roomDataBase =  palaFraRoomDataBase.getRoomDataBase(application);
       DAOPalFra = roomDataBase.DAO();

   }

    public LiveData<List<palfraEntity>> getAllPalFra(int id) {
        allPalFra = DAOPalFra.getPalFra(id);
        return allPalFra;
    }

}
