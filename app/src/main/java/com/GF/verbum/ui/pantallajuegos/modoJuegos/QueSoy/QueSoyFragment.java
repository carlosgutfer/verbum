package com.GF.verbum.ui.pantallajuegos.modoJuegos.QueSoy;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.PreguntasEntity;
import com.GF.verbum.R;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.ModosJuegosViewModel;

import java.util.ArrayList;
import java.util.List;

public class QueSoyFragment extends Fragment {

    private ModosJuegosViewModel mpalabrasviewModel;
    private List<PalabrasEntity> allPalabras = new ArrayList<>();
    private List<PreguntasEntity> allPreguntas = new ArrayList<>();
    private int posicion, dificultad,aleatorio;
    private PalabrasEntity palabraAleatoria;
    private PreguntasEntity preguntaActual;
    private TextView palabra, pregunta;
    private View v;

    public static QueSoyFragment newInstance(int dificultad) {
        QueSoyFragment fragment = new QueSoyFragment();
        Bundle args = new Bundle();
        args.putInt("dificultad", dificultad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.v= inflater.inflate(R.layout.que_soy_fragment, container, false);
        findViewById();

        mpalabrasviewModel=new ViewModelProvider(this).get(ModosJuegosViewModel.class);
        mpalabrasviewModel.getAllPalabras().observe(getActivity(), new Observer<List<PalabrasEntity>>() {
            @Override
            public void onChanged(List<PalabrasEntity> palabrasEntities) {
                allPalabras=setPalabras(palabrasEntities);
                nuevaPlabra(allPalabras);
            }
        });
        mpalabrasviewModel.getAllPreguntas().observe(getActivity(), new Observer<List<PreguntasEntity>>() {
            @Override
            public void onChanged(List<PreguntasEntity> preguntasEntities) {
                allPreguntas=setPreguntas(preguntasEntities);
                NuevaPregunta(preguntasEntities);
            }
        });

        return v;
    }

    private void NuevaPregunta(List<PreguntasEntity> preguntasEntities) {
        aleatorio= (int) (Math.random()*preguntasEntities.size());
        preguntaActual=preguntasEntities.get(aleatorio);
        pregunta.setText(preguntaActual.getPregunta());
    }

    private void nuevaPlabra(List<PalabrasEntity> allPalabras) {
        posicion= (int) (Math.random()*allPalabras.size());
        palabraAleatoria=allPalabras.get(posicion);
        palabra.setText(palabraAleatoria.getPalabra());
    }

    private List<PreguntasEntity> setPreguntas(List<PreguntasEntity> preguntasEntities) {
        this.allPreguntas=preguntasEntities;
        return allPreguntas;
    }

    private List<PalabrasEntity> setPalabras(List<PalabrasEntity> palabrasEntities) {
        dificultad = getArguments().getInt("dificultad");

        for (int i = 0; i < palabrasEntities.size(); i++) {
            if (funcionesPalabras(palabrasEntities.get(i), dificultad)) {
                palabraAleatoria = palabrasEntities.get(i);
                allPalabras.add(palabraAleatoria);
            }
        }
        return allPalabras;

    }
    private boolean funcionesPalabras(PalabrasEntity palabra, int dificultad) {
        boolean valido=false;
        int contador =0;
        if(palabra.isArticulo())
            contador++;
        if(palabra.isInterjeccion())
            contador++;
        if(palabra.isConjuncion())
            contador++;
        if(palabra.isPreposicion())
            contador++;
        if(palabra.isVerbo())
            contador++;
        if(palabra.isPronombre())
            contador++;
        if(palabra.isSustantivo())
            contador++;
        if(palabra.isAdjetivo())
            contador++;
        if(palabra.isAdverbio())
            contador++;
        if(dificultad==1&&contador<2){
            valido=true;
        }else if(dificultad==2&&contador<3){
            valido=true;
        }else if(dificultad==3) {
            valido=true;
        }
        return valido;
    }

    private void findViewById() {
        palabra=v.findViewById(R.id.TV_palabra);
        pregunta=v.findViewById(R.id.TV_preguntas);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mpalabrasviewModel = ViewModelProviders.of(this).get(ModosJuegosViewModel.class);
        // TODO: Use the ViewModel
    }

}