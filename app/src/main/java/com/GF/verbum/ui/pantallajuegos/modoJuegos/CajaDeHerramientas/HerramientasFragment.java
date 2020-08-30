package com.GF.verbum.ui.pantallajuegos.modoJuegos.CajaDeHerramientas;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.PreguntasEntity;
import com.GF.verbum.R;
import com.GF.verbum.ui.pantallajuegos.MainActivity;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.ModosJuegosViewModel;

import java.util.List;
import java.util.Locale;

public class HerramientasFragment extends Fragment {

    private ModosJuegosViewModel mViewModel;
    private CheckBox op1, op2, op3,op4;
    private List<PreguntasEntity> allPreguntas;
    private List<PalabrasEntity> allPalabras;
    private Button comprobar;
    private TextView tiempo,letrasconseguidas;
    private TextView pregunta;
    private int aleatorio;
    private   CountDownTimer countDownTimer;
    private int contador=0;
    private View v;
    private int letrasTotales=0;



    private PalabrasEntity p1, p2, p3, p4;
    private PreguntasEntity preguntaActual;


    public static HerramientasFragment newInstance() {
        return new HerramientasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.herramientas_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ModosJuegosViewModel.class);
        findViewById();
        mViewModel.getAllPalabras().observe(getViewLifecycleOwner(), new Observer<List<PalabrasEntity>>() {
            @Override
            public void onChanged(List<PalabrasEntity> palabrasEntities) {
                allPalabras=setPalabras(palabrasEntities);
                NuevasPalabras();
            }
        });

        mViewModel.getAllPreguntas().observe(getViewLifecycleOwner(), new Observer<List<PreguntasEntity>>() {
            @Override
            public void onChanged(List<PreguntasEntity> preguntasEntities) {
                allPreguntas=setPreguntas(preguntasEntities);
               NuevaPregunta();
            }
        });
    return v;
    }

    private List<PreguntasEntity> setPreguntas(List<PreguntasEntity> preguntasEntities) {
        this.allPreguntas=preguntasEntities;
        return allPreguntas;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViewById();

        tiempo();

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
            countDownTimer.cancel();
            countDownTimer.onFinish();
            tiempo();
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
    }
    private void NuevaPregunta(){
        aleatorio= (int) (Math.random()*allPreguntas.size());
        pregunta.setText(allPreguntas.get(aleatorio).getPregunta());
        preguntaActual=allPreguntas.get(aleatorio);
    }

    private List<PalabrasEntity> setPalabras(List<PalabrasEntity> palabrasEntities) {
        this.allPalabras=palabrasEntities;
        return allPalabras;
    }

    private void findViewById() {
        op1=v.findViewById(R.id.CB_op1);
        op2=v.findViewById(R.id.CB_op2);
        op3=v.findViewById(R.id.CB_op3);
        op4=v.findViewById(R.id.CB_op4);
        tiempo=v.findViewById(R.id.TV_tiempo);
        pregunta=v.findViewById(R.id.TV_PreguntaHerramientas);
        comprobar=v.findViewById(R.id.BT_comprobarHerramientas);
        letrasconseguidas=v.findViewById(R.id.TV_LetrasConseguidas);
    }

    private void tiempo(){
         countDownTimer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                tiempo.setText(String.format(Locale.getDefault(), "%d S", millisUntilFinished / 1000L));
            }
            public void onFinish() {
                JuegoFinalizado();
                NuevasPalabras();
                NuevaPregunta();
            }
        }.start();
    }

    private void JuegoFinalizado(){
        contador++;
        if (contador == 9) {
            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
        }

    }

    private boolean comprobarrespuesta(PalabrasEntity p){
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
        if(valido==true) {
            letrasTotales++;
        }else{
            letrasTotales--;
        }
        return valido;
    }
}