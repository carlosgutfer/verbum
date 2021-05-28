package com.GF.verbum.ui.pantallajuegos.modoJuegos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.PreguntasEntity;
import com.GF.verbum.DB.Entities.frasesEntity;
import com.GF.verbum.PalabraRepository;

import java.util.List;

public class modosDeJuegoViewModel extends AndroidViewModel {

    private PalabraRepository mRepository;
    private LiveData<List<PalabrasEntity>> allPalabras;
    private LiveData<List<PreguntasEntity>> allPreguntas;
    private LiveData<List<frasesEntity>> allFrases;

    public modosDeJuegoViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PalabraRepository(application);
        allPalabras=mRepository.getAll();
        allPreguntas=mRepository.getAllPreguntas();
        allFrases = mRepository.getAllFrases();

    }
    public LiveData<List<PalabrasEntity>> getAllPalabras(){
        return  allPalabras;}

    public LiveData<List<PreguntasEntity>> getAllPreguntas(){
        return  allPreguntas;}

    public LiveData<List<frasesEntity>> getAllFrases(){
        return  allFrases;}


}
