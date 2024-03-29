package com.GF.verbum.ui.pantallajuegos.modoJuegos.morfologia.CajaDeHerramientas;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.PreguntasEntity;
import com.GF.verbum.R;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.modosDeJuegoViewModel;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.RecordFragment;
import com.google.android.gms.ads.interstitial.InterstitialAd;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HerramientasFragment extends Fragment {

    private modosDeJuegoViewModel mViewModel;
    private CheckBox op1, op2, op3,op4, ninguna;
    private List<PreguntasEntity> allPreguntas = new ArrayList<>();
    private List<PalabrasEntity> allPalabras = new ArrayList<>();
    private Button comprobar;
    private ProgressBar upProgressBar;
    private TextView tiempo,letrasconseguidas;
    private TextView pregunta;
    private int aleatorio;
    private   CountDownTimer countDownTimer;

    private int progresoBar=0;
    private int contador=0;
    private View v;
    private int letrasTotales=0;
    private int dificultad;
    private InterstitialAd mInterstitialad;





    private PalabrasEntity p1, p2, p3, p4;
    private PreguntasEntity preguntaActual;


    public static HerramientasFragment newInstance(int dificultad) {
        HerramientasFragment herramientasFragment = new HerramientasFragment();
        Bundle args = new Bundle();
        args.putInt("dificultad", dificultad);
        herramientasFragment.setArguments(args);
        return  herramientasFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_herramientas, container, false);
        /*
        mInterstitialad = new InterstitialAd(getActivity());
        mInterstitialad.setAdUnitId("ca-app-pub-9592543293433576/3091063629");
        mInterstitialad.loadAd(new AdRequest.Builder().build());*/
        mViewModel = new ViewModelProvider(this).get(modosDeJuegoViewModel.class);
        findViewById();

        mViewModel.getAllPalabras().observe(getActivity(), new Observer<List<PalabrasEntity>>() {
            @Override
            public void onChanged(List<PalabrasEntity> palabrasEntities) {
                allPalabras=setPalabras(palabrasEntities);
                NuevasPalabras();
            }
        });

        mViewModel.getAllPreguntas().observe(getActivity(), new Observer<List<PreguntasEntity>>() {
            @Override
            public void onChanged(List<PreguntasEntity> preguntasEntities) {
                allPreguntas=setPreguntas(preguntasEntities);
               NuevaPregunta(preguntasEntities);
            }
        });
    return v;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViewById();

        comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(op1.isChecked()) {
                    comprobarrespuesta(p1);
                    op1.setChecked(false);
                }
                if(op2.isChecked()){
                    comprobarrespuesta(p2);
                    op2.setChecked(false);
                }
                if(op3.isChecked()){
                    comprobarrespuesta(p3);
                    op3.setChecked(false);
                }
                if (op4.isChecked()){
                    comprobarrespuesta(p4);
                    op4.setChecked(false);
                }
                if(ninguna.isChecked()){
                    comprobarTodas();
                    ninguna.setChecked(false);
                }
            countDownTimer.cancel();
            countDownTimer.onFinish();
            letrasconseguidas.setText(String.valueOf(letrasTotales));
            }
        });
    }

    private void NuevasPalabras() {
        aleatorio= (int) (Math.random()*allPalabras.size());
        op1.setText(allPalabras.get(aleatorio).getPalabra());
        p1=allPalabras.get(aleatorio);
        aleatorio= (int) (Math.random()*allPalabras.size());
        op2.setText(allPalabras.get(aleatorio).getPalabra());
        p2=allPalabras.get(aleatorio);
        aleatorio= (int) (Math.random()*allPalabras.size());
        op3.setText(allPalabras.get(aleatorio).getPalabra());
        p3=allPalabras.get(aleatorio);
        aleatorio= (int) (Math.random()*allPalabras.size());
        op4.setText(allPalabras.get(aleatorio).getPalabra());
        p4=allPalabras.get(aleatorio);
        tiempo();
    }
    private void NuevaPregunta(List<PreguntasEntity> allPreguntas){
        aleatorio= (int) (Math.random()*allPreguntas.size());
        pregunta.setText(allPreguntas.get(aleatorio).getPregunta());
        preguntaActual=allPreguntas.get(aleatorio);
    }

    private List<PalabrasEntity> setPalabras(List<PalabrasEntity> palabrasEntities) {
        this.dificultad=getArguments().getInt("dificultad");

            for(int i=0;i<palabrasEntities.size();i++){
                if(funcionesPalabras(palabrasEntities.get(i),dificultad)){
                    this.allPalabras.add(palabrasEntities.get(i));
                }
            }

        return allPalabras;
    }

    private boolean  funcionesPalabras(PalabrasEntity palabra, int dificultad) {
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

    private List<PreguntasEntity> setPreguntas(List<PreguntasEntity> preguntasEntities) {
        this.allPreguntas=preguntasEntities;
        return allPreguntas;
    }

    private void findViewById() {
        op1=v.findViewById(R.id.CB_op1);
        op2=v.findViewById(R.id.CB_op2);
        op3=v.findViewById(R.id.CB_op3);
        op4=v.findViewById(R.id.CB_op4);
        tiempo=v.findViewById(R.id.TV_tiempo);
        pregunta=v.findViewById(R.id.TV_PreguntaHerramientas);
        comprobar=v.findViewById(R.id.BT_comprobarHerramientas);
        letrasconseguidas=v.findViewById(R.id.TV_LetrasConseguidasEscalera);
        ninguna=v.findViewById(R.id.CB_ninguna);
        upProgressBar=v.findViewById(R.id.PB_multiplicadorLetras);
    }

    private void tiempo(){
        int tiempoDificultad = tiempoDificultad();

         countDownTimer = new CountDownTimer(tiempoDificultad, 1000) {
            public void onTick(long millisUntilFinished) {
                tiempo.setText(String.format(Locale.getDefault(), "%d S", millisUntilFinished / 1000L));
            }
            public void onFinish() {
                JuegoFinalizado();
                NuevasPalabras();
                NuevaPregunta(allPreguntas);
            }
        }.start();
    }

    private int tiempoDificultad() {
        int tiempo=getArguments().getInt("dificultad");
        if(tiempo==1){
            tiempo=45000;
        }else if(tiempo==2){
            tiempo=30000;
        }else if(tiempo==3){
            tiempo=15000;
        }
        return tiempo;
    }

    private void JuegoFinalizado(){
        contador++;
        checkOut();
        if (contador == 9) {
            if(letrasTotales>0){
               /* if(mInterstitialad.isLoaded())
                    mInterstitialad.show();*/
                    requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerJuegos, RecordFragment.newInstance(letrasTotales,1,dificultad))
                        .commit();
            }else{

                requireActivity().onBackPressed();

            }


        }
    }

    private void checkOut() {
        op1.setChecked(false);
        op2.setChecked(false);
        op3.setChecked(false);
        op4.setChecked(false);
        ninguna.setChecked(false);
    }

    private void comprobarrespuesta(PalabrasEntity p){
        boolean valido=false;
        if(p.isAdjetivo()&&preguntaActual.isAdjetivo()){
             valido =true;
        }else if(p.isAdverbio()&&preguntaActual.isAdverbio()){
            valido =true;
        }else if(p.isArticulo()&&preguntaActual.isArticulo()){
            valido =true;
        }else if(p.isConjuncion()&&preguntaActual.isConjuncion()){
            valido=true;
        }else if(p.isInterjeccion()&&preguntaActual.isConjuncion()){
            valido=true;
        }else  if(p.isPreposicion()&&preguntaActual.isPreposicion()){
            valido=true;
        }else if(p.isPronombre()&&preguntaActual.isPronombre()){
            valido=true;
        }else  if(p.isSustantivo()&&preguntaActual.isSustantivo()){
            valido=true;
        }else if(p.isVerbo()&&preguntaActual.isVerbo()){
            valido=true;
        }
        if(valido)
            progreso();
        else
            fallo();
    }

    private void comprobarTodas(){
        PalabrasEntity p=null;
        boolean valido=false;
        for (int i=0;i<4;i++) {
            if (i == 0) {
                p = p1;
            }
            if (i == 1) {
                p = p2;
            }
            if (i == 2) {
                p = p3;
            }
            if (i == 3) {
                p = p4;
            }

            if (p.isAdjetivo() && preguntaActual.isAdjetivo()) {
                valido = true;
                break;
            } else if (p.isAdverbio() && preguntaActual.isAdverbio()) {
                valido = true;
                break;
            } else if (p.isArticulo() && preguntaActual.isArticulo()) {
                valido = true;
                break;
            } else if (p.isConjuncion() && preguntaActual.isConjuncion()) {
                valido = true;
                break;
            } else if (p.isInterjeccion() && preguntaActual.isConjuncion()) {
                valido = true;
                break;
            } else if (p.isPreposicion() && preguntaActual.isPreposicion()) {
                valido = true;
                break;
            } else if (p.isPronombre() && preguntaActual.isPronombre()) {
                valido = true;
                break;
            } else if (p.isSustantivo() && preguntaActual.isSustantivo()) {
                valido = true;
                break;
            } else if (p.isVerbo() && preguntaActual.isVerbo()) {
                valido = true;
                break;
            }
        }
            if(valido)
                fallo();
            else
                progreso();
    }

    private void fallo() {
        letrasTotales=letrasTotales-10;
        progresoBar=0;
        upProgressBar.setProgress(0);
    }


    public void progreso(){
        progresoBar++;
        if(progresoBar<3)
            letrasTotales=letrasTotales+10;
        else if(progresoBar>3&&progresoBar<7)
            letrasTotales = letrasTotales + 20;
        else if(progresoBar>5&&progresoBar<10)
            letrasTotales = letrasTotales + 30;
        else
            letrasTotales = letrasTotales + 50;

        if(progresoBar<11)
            upProgressBar.setProgress(progresoBar*10);
    }


}