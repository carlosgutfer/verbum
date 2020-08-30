package com.GF.verbum;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import com.GF.verbum.DB.DAO.SintaxisDao;
import com.GF.verbum.DB.Entities.SintaxisEntity;
import com.GF.verbum.DB.SintaxisRoomDataBase;

import java.util.List;

public class SintaxisRepository {
    private SintaxisDao DAO;
    private LiveData<List<SintaxisEntity>> allTipos;

    public SintaxisRepository(Application application) {
        SintaxisRoomDataBase roomDataBase = SintaxisRoomDataBase.getRoomDataBase(application);
        DAO=roomDataBase.DAO();
        this.allTipos = DAO.getAllTipos();
        }

    public LiveData<List<SintaxisEntity>> getAll(){
        return  allTipos;}
}
