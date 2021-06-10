package com.GF.verbum.ui.pantallajuegos.modoJuegos.analisis;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.frasesEntity;
import com.GF.verbum.DB.Entities.fratipEntity;
import com.GF.verbum.DB.Entities.palfraEntity;
import com.GF.verbum.DB.Entities.tiposEntity;
import com.GF.verbum.PALFRARepository;
import com.GF.verbum.PalabraRepository;

import java.util.List;

public class analisisViewModel  extends AndroidViewModel {
    private PALFRARepository newRepository;
    private LiveData<List<palfraEntity>> allPalfra;
    private LiveData<List<fratipEntity>> fraTip;
    private LiveData<List<tiposEntity>> tipo;
    private LiveData<List<tiposEntity>> allTipos;
    private LiveData<List<frasesEntity>> allFrases;
    private LiveData<List<String>> palFra;






    public analisisViewModel(@NonNull Application application) {
        super(application);
        newRepository = new PALFRARepository(application);
        allFrases = newRepository.getAllFrases();

    }
    // devuelve la lista de palabras/frase del id proporcionado
    public LiveData<List<palfraEntity>> getAllPalfra(int id) {
        allPalfra = newRepository.getAllPalFra(id);
    return allPalfra;
    }

    // Devuelve la lista de de objetos tipfra de la tabla tipFra con el id pasado como parámetro.
    public LiveData<List<fratipEntity>> getTipFra(int id) {
        fraTip = newRepository.getFraTip(id);
        return fraTip;
    }
    //Devuelve los tipos de la oración
    public LiveData<List<tiposEntity>> getTipo (int id){
        tipo = newRepository.getTipo(id);
        return tipo;
    }
    //Devuelve todos los tipos
    public LiveData<List<tiposEntity>> getAllTipos(){
            allTipos = newRepository.getAllTipos();
        return  allTipos;
    }

    //Devuelve los tipos de la oración
    public LiveData<List<tiposEntity>> getSonTip (int id){
        tipo = newRepository.getSonTip(id);
        return tipo;
    }

    public LiveData<List<frasesEntity>> getAllFrases(){
        return  allFrases;}

    public LiveData<List<String>> getPalFrases(int id){
        palFra = newRepository.getPalabrasFrases(id);
        return palFra;
    }
}
