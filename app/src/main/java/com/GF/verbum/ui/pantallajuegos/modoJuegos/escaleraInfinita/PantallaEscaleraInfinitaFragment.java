package com.GF.verbum.ui.pantallajuegos.modoJuegos.escaleraInfinita;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.PreguntasEntity;
import com.GF.verbum.R;
import com.GF.verbum.commun.Constantes;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.MainActivity;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.ModosJuegosViewModel;

import java.util.List;

public class PantallaEscaleraInfinitaFragment extends Fragment implements View.OnClickListener {


    private List<PalabrasEntity> allPalabras;
    private List<PreguntasEntity> allPreguntas;
    private PalabrasEntity palabraAleatoria;
    private PreguntasEntity preguntaAleatoria;

    private ModosJuegosViewModel mpalabrasviewModel;
    private TextView palabra;
    private View v;
    private int aleatorio;
    private int letrasGanadas;
    private int  posicion;

    private TextView letras;
    private Button sust, adj, pro, adv, verb, pre, conj, inter, art;

    public static PantallaEscaleraInfinitaFragment newInstance() {

        return new PantallaEscaleraInfinitaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.escalera_infinita_fragment, container, false);
        this.v=view;
        findViewById();


        this.aleatorio= (int) (Math.random()*2);

        mpalabrasviewModel=new ViewModelProvider(this).get(ModosJuegosViewModel.class);
        mpalabrasviewModel.getAllPalabras().observe(getActivity(), new Observer<List<PalabrasEntity>>() {
            @Override
            public void onChanged(List<PalabrasEntity> palabrasEntities) {
                setSustantivos(palabrasEntities);
                if(aleatorio==0) {
                    posicion= (int) (Math.random()*palabrasEntities.size());
                    palabraAleatoria=palabrasEntities.get(posicion);
                    palabra.setText(palabraAleatoria.getPalabra());
                }
            }
        });
        mpalabrasviewModel.getAllPreguntas().observe(getViewLifecycleOwner(), new Observer<List<PreguntasEntity>>() {
            @Override
            public void onChanged(List<PreguntasEntity> preguntasEntities) {
                setPreguntas(preguntasEntities);
                if(aleatorio==1) {
                    posicion= (int) (Math.random()*preguntasEntities.size());
                    preguntaAleatoria=preguntasEntities.get(posicion);
                    palabra.setText(preguntaAleatoria.getPregunta());
                }
            }
        });

        return view;
    }

    private void findViewById() {
        palabra=v.findViewById(R.id.TV_palabraInfinita);
        art=v.findViewById(R.id.BT_Articulo);
        sust=v.findViewById(R.id.BT_Sustantivo);
        pro=v.findViewById(R.id.BT_Pronombre);
        adj=v.findViewById(R.id.BT_Adjetivo);
        adv=v.findViewById(R.id.BT_Adverbios);
        verb=v.findViewById(R.id.BT_verbos);
        pre=v.findViewById(R.id.BT_Preposicion);
        conj=v.findViewById(R.id.BT_Conjuncion);
        inter=v.findViewById(R.id.BT_Interjencion);
        letras=v.findViewById(R.id.TV_LetrasConseguidasEscalera);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onClick();

    }

    private void onClick() {
        sust.setOnClickListener(this);
        adj.setOnClickListener(this);
        pro.setOnClickListener(this);
        adv.setOnClickListener(this);
        verb.setOnClickListener(this);
        pre.setOnClickListener(this);
        conj.setOnClickListener(this);
        inter.setOnClickListener(this);
        art.setOnClickListener(this);
    }

    private void setSustantivos(List<PalabrasEntity> palabras) {
        this.allPalabras=palabras;
    }
    private void setPreguntas(List<PreguntasEntity> preguntasEntities) { this.allPreguntas=preguntasEntities; }

    @Override
    public void onClick(View v) {
        int view = v.getId();
        if(view==R.id.BT_Sustantivo){
            if(aleatorio==0&&palabraAleatoria.isSustantivo()) {
                mostrar();
                nuevaPalabra();
            }else if(aleatorio==1&&preguntaAleatoria.isSustantivo()){
                mostrar();
                nuevaPalabra();
            }
            else{
                JuegoFinalizado();
            }

        }
        if(view==R.id.BT_Adjetivo){
            if(aleatorio==0&&palabraAleatoria.isAdjetivo()) {
                mostrar();
                nuevaPalabra();
            }else if(aleatorio==1&&preguntaAleatoria.isAdjetivo()){
                mostrar();
                nuevaPalabra();
            }
            else{
                JuegoFinalizado();
            }

        }
        if(view==R.id.BT_Pronombre){
            if(aleatorio==0&&palabraAleatoria.isPronombre()) {
                mostrar();
                nuevaPalabra();
            }else if(aleatorio==1&&preguntaAleatoria.isPronombre()) {
                mostrar();
                nuevaPalabra();
            } else{
                JuegoFinalizado();
            }

        }
        if(view==R.id.BT_Adverbios){
            if(aleatorio==0&&palabraAleatoria.isAdverbio()) {
                mostrar();
                nuevaPalabra();
            }else if(aleatorio==1&&preguntaAleatoria.isAdverbio()){
                mostrar();
                nuevaPalabra();
            } else{
                JuegoFinalizado();
            }

        }
        if(view==R.id.BT_verbos){
            if(aleatorio==0&&palabraAleatoria.isVerbo()) {
                mostrar();
                nuevaPalabra();
            }else if(aleatorio==1&&preguntaAleatoria.isVerbo()){
                mostrar();
                nuevaPalabra();
            } else{
                JuegoFinalizado();
            }
        }
        if(view==R.id.BT_Preposicion){
            if(aleatorio==0&&palabraAleatoria.isPreposicion()) {
                mostrar();
                nuevaPalabra();
            }else if(aleatorio==1&&preguntaAleatoria.isPreposicion()){
                mostrar();
                nuevaPalabra();
            } else{
                JuegoFinalizado();
            }

        }
        if(view==R.id.BT_Conjuncion){
            if(aleatorio==0&&palabraAleatoria.isConjuncion()) {
                mostrar();
                nuevaPalabra();
            }else if(aleatorio==1&&preguntaAleatoria.isConjuncion()){
               mostrar();
                nuevaPalabra();
            } else{
                JuegoFinalizado();
            }

        }
        if(view==R.id.BT_Interjencion){
            if(aleatorio==0&&palabraAleatoria.isInterjeccion()) {
                mostrar();
                nuevaPalabra();
            }else if(aleatorio==1&&preguntaAleatoria.isInterjeccion()){
                mostrar();
                nuevaPalabra();
            } else{
                JuegoFinalizado();
            }

        }
        if(view==R.id.BT_Articulo){
            if(aleatorio==0&&palabraAleatoria.isArticulo()) {
                mostrar();
                nuevaPalabra();
            }else if(aleatorio==1&&preguntaAleatoria.isArticulo()) {
                mostrar();
                nuevaPalabra();
            }else{
                JuegoFinalizado();
            }

        }

    }

    private void mostrar(){
        letrasGanadas++;
        letras.setText(String.valueOf(letrasGanadas));
    }

    private void nuevaPalabra() {
        this.aleatorio=(int)(Math.random()*2);
        if(aleatorio==0){
            posicion= (int) (Math.random()*allPalabras.size());
            palabraAleatoria=allPalabras.get(posicion);
            palabra.setText(palabraAleatoria.getPalabra());
        }else  if(aleatorio==1){
            posicion= (int) (Math.random()*allPreguntas.size());
            preguntaAleatoria=allPreguntas.get(posicion);
            palabra.setText(preguntaAleatoria.getPregunta());
        }
    }

    private void JuegoFinalizado(){

            if(SharedPreferentManager.getIntegerValue(Constantes.MEJOR_ESCALERA)<letrasGanadas)
                SharedPreferentManager.setIntegerValue(Constantes.MEJOR_ESCALERA,letrasGanadas);
            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);

    }
}
