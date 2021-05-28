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

   public PALFRARepository (Application application,int id){
       palaFraRoomDataBase roomDataBase =  palaFraRoomDataBase.getRoomDataBase(application);
       DAOPalFra = roomDataBase.DAO();
       allPalFra = DAOPalFra.getPalFra(id);

   }

   public  LiveData<List<palfraEntity>> getPALFRA( ){
       return  allPalFra;
   }
}
