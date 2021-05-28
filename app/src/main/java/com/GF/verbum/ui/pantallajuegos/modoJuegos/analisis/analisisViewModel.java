package com.GF.verbum.ui.pantallajuegos.modoJuegos.analisis;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.GF.verbum.DB.Entities.palfraEntity;
import com.GF.verbum.PALFRARepository;

import java.util.List;

public class analisisViewModel  extends AndroidViewModel {
    private PALFRARepository newRepository;
    private LiveData<List<palfraEntity>> allPalfra;

    public analisisViewModel(@NonNull Application application,int id) {
        super(application);
        newRepository = new PALFRARepository(application,id);
        allPalfra = newRepository.getPALFRA();
    }

    public  LiveData<List<palfraEntity>> getallPALFRA( ){
        return  allPalfra;
    }
}
