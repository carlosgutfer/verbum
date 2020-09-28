package com.GF.verbum.ui.pantallajuegos.modoJuegos.escaleraInfinita;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.media.AudioManager;
import android.media.SoundPool;
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
import com.GF.verbum.R;
import com.GF.verbum.commun.Constantes;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.ModosJuegosViewModel;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.RecordFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PantallaEscaleraInfinitaFragment extends Fragment implements View.OnClickListener {


    private  int sonido_de_tecla;
    SoundPool sp;

    private List<PalabrasEntity> allPalabras = new ArrayList<>();
    private PalabrasEntity palabraAleatoria;

    private ModosJuegosViewModel mpalabrasviewModel;
    private TextView palabra;
    private View v;
    private int letrasGanadas;
    private int  posicion;
    private int dificultad;
    private String nombre;

    private TextView letras;
    private Button sust, adj, pro, adv, verb, pre, conj, inter, art;

    public static PantallaEscaleraInfinitaFragment newInstance(int dificultad) {
                PantallaEscaleraInfinitaFragment fragment = new PantallaEscaleraInfinitaFragment();
                    Bundle args = new Bundle();
                    args.putInt("dificultad", dificultad);
                    fragment.setArguments(args);
                return fragment;
    }
    public static PantallaEscaleraInfinitaFragment newInstance(String palabra,int letras) {
        PantallaEscaleraInfinitaFragment fragment = new PantallaEscaleraInfinitaFragment();
        Bundle args = new Bundle();
        args.putString("palabra", palabra);
        args.putInt("letras",letras);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_escalera_infinita, container, false);
        this.v=view;
        sp = new SoundPool(10, AudioManager.STREAM_MUSIC,1);
        sonido_de_tecla= sp.load(getActivity(),R.raw.tecla,1);
        findViewById();
        nombre=getArguments().getString("palabra",null);

        mpalabrasviewModel = new ViewModelProvider(this).get(ModosJuegosViewModel.class);
           mpalabrasviewModel.getAllPalabras().observe(getViewLifecycleOwner(), new Observer<List<PalabrasEntity>>() {
               @Override
               public void onChanged(List<PalabrasEntity> palabrasEntities) {
                   if(nombre==null) {
                       allPalabras = setPalabras(palabrasEntities);
                       posicion = (int) (Math.random() * allPalabras.size());
                       palabraAleatoria = allPalabras.get(posicion);
                       palabra.setText(palabraAleatoria.getPalabra());
                   }else{
                       buscarPalabra(palabrasEntities);
                       mostrar();
                   }

               }
           });

        return view;
    }

    private void buscarPalabra(List<PalabrasEntity> palabrasEntities) {
        for (int i =0;i<palabrasEntities.size();i++ ){
            if(nombre.equals(palabrasEntities.get(i).getPalabra())){
                palabraAleatoria = palabrasEntities.get(i);
                palabra.setText(palabraAleatoria.getPalabra());
                break;
            }
        }
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
        mpalabrasviewModel = new ViewModelProvider(this).get(ModosJuegosViewModel.class);

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

    private List<PalabrasEntity> setPalabras(List<PalabrasEntity> palabrasEntities) {
        this.dificultad=getArguments().getInt("dificultad");
            for(int i=0;i<palabrasEntities.size();i++){
                if(funcionesPalabras(palabrasEntities.get(i),dificultad)){
                    palabraAleatoria=palabrasEntities.get(i);
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

    @Override
    public void onClick(View v) {
        int view = v.getId();
        sonido();
        if(view==R.id.BT_Sustantivo){
            if(palabraAleatoria.isSustantivo()) {
                mostrar();
                nuevaPalabra();

            } else{
                juegoFinalizado();
            }

        }
        if(view==R.id.BT_Adjetivo){
            if(palabraAleatoria.isAdjetivo()) {
                mostrar();
                nuevaPalabra();
            } else{
                juegoFinalizado();
            }

        }
        if(view==R.id.BT_Pronombre){
            if(palabraAleatoria.isPronombre()) {
                mostrar();
                nuevaPalabra();
            }else{
                juegoFinalizado();
            }

        }
        if(view==R.id.BT_Adverbios){
            if(palabraAleatoria.isAdverbio()) {
                mostrar();
                nuevaPalabra();
            } else{
                juegoFinalizado();
            }

        }
        if(view==R.id.BT_verbos){
            if(palabraAleatoria.isVerbo()) {
                mostrar();
                nuevaPalabra();
            } else{
                juegoFinalizado();
            }
        }
        if(view==R.id.BT_Preposicion){
            if(palabraAleatoria.isPreposicion()) {
                mostrar();
                nuevaPalabra();
            } else{
                juegoFinalizado();
            }

        }
        if(view==R.id.BT_Conjuncion){
            if(palabraAleatoria.isConjuncion()) {
                mostrar();
                nuevaPalabra();
            } else{
                juegoFinalizado();
            }

        }
        if(view==R.id.BT_Interjencion){
            if(palabraAleatoria.isInterjeccion()) {
                mostrar();
                nuevaPalabra();

            } else{
                juegoFinalizado();
            }

        }
        if(view==R.id.BT_Articulo){
            if(palabraAleatoria.isArticulo()) {
                mostrar();
                nuevaPalabra();
            }else{
                juegoFinalizado();
            }

        }

    }

    private void sonido() {
        if(SharedPreferentManager.getIntegerValue("soundMode")==-1)
            sp.play(sonido_de_tecla,0.4f,0.4f,1,0,0);
    }

    private void mostrar(){
        if(getArguments().getInt("letras",-50)!=-50){
            letrasGanadas=getArguments().getInt("letras");
            letras.setText(String.valueOf(letrasGanadas));
        }else {
            letrasGanadas++;
            letras.setText(String.valueOf(letrasGanadas));
        }
    }

    private void nuevaPalabra() {
        if(nombre==null) {
            posicion = (int) (Math.random() * allPalabras.size());
            palabraAleatoria = allPalabras.get(posicion);
            palabra.setText(palabraAleatoria.getPalabra());
        }else {
            letrasGanadas++;
            juegoFinalizado();
        }
    }

    private void juegoFinalizado(){
        if(letrasGanadas==0){
            requireActivity().onBackPressed();
        }else {
            if (SharedPreferentManager.getIntegerValue(Constantes.MEJOR_ESCALERA) < letrasGanadas)
                SharedPreferentManager.setIntegerValue(Constantes.MEJOR_ESCALERA, letrasGanadas);
            if(nombre==null) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerJuegos, RecordFragment.newInstance(letrasGanadas, palabraAleatoria.getUrlRae(), palabraAleatoria.getPalabra(), 3, 1))
                        .commit();
            }else{
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerJuegos, RecordFragment.newInstance(letrasGanadas, palabraAleatoria.getUrlRae(), palabraAleatoria.getPalabra(), 2, 1))
                        .commit();
            }
        }
    }
}
