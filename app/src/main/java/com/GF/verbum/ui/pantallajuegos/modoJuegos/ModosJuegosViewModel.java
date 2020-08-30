package com.GF.verbum.ui.pantallajuegos.modoJuegos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.PreguntasEntity;
import com.GF.verbum.PalabraRepository;

import java.util.List;

public class ModosJuegosViewModel extends AndroidViewModel {

    private PalabraRepository mRepository;
    private LiveData<List<PalabrasEntity>> allPalabras;
    private LiveData<List<PreguntasEntity>> allPreguntas;

    public ModosJuegosViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PalabraRepository(application);
        allPalabras=mRepository.getAll();
        allPreguntas=mRepository.getAllPreguntas();
    }
    public LiveData<List<PalabrasEntity>> getAllPalabras(){
        return  allPalabras;}

    public LiveData<List<PreguntasEntity>> getAllPreguntas(){
        return  allPreguntas;}


}
