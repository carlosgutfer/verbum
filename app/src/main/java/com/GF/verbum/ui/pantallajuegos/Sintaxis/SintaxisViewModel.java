package com.GF.verbum.ui.pantallajuegos.Sintaxis;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.GF.verbum.DB.Entities.SintaxisEntity;
import com.GF.verbum.SintaxisRepository;

import java.util.List;

public class SintaxisViewModel extends AndroidViewModel {

    private SintaxisRepository mRepository;
    private LiveData<List<SintaxisEntity>> allSintaxis;


    public SintaxisViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SintaxisRepository(application);
        allSintaxis=mRepository.getAll();
    }
    public LiveData<List<SintaxisEntity>> getAllSintaxis(){
        return  allSintaxis;}
}
