package com.GF.verbum.ui.pantallajuegos.modoJuegos.analisis;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.frasesEntity;
import com.GF.verbum.DB.Entities.palfraEntity;
import com.GF.verbum.PALFRARepository;
import com.GF.verbum.PalabraRepository;

import java.util.List;

public class analisisViewModel  extends AndroidViewModel {
    private PALFRARepository newRepository;
    private LiveData<List<palfraEntity>> allPalfra;



    public analisisViewModel(@NonNull Application application) {
        super(application);
        newRepository = new PALFRARepository(application);
    }

    public LiveData<List<palfraEntity>> getAllPalfra(int id) {
        allPalfra = newRepository.getAllPalFra(id);
    return allPalfra;
    }




}
