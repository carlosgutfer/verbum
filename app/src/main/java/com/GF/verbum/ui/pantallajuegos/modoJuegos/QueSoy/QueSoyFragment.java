package com.GF.verbum.ui.pantallajuegos.modoJuegos.QueSoy;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.PreguntasEntity;
import com.GF.verbum.R;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.ModosJuegosViewModel;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.escaleraInfinita.PantallaEscaleraInfinitaFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.escaleraInfinita.pantallaEscaleraInfinitaMedioDificilFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.escaleraInfinita.pantallaEscaleraInfinitaPantallaPequeña;

import java.util.ArrayList;
import java.util.List;

public class QueSoyFragment extends Fragment implements View.OnClickListener {
    private  int sonido_de_tecla;
    private SoundPool sp;
    private ModosJuegosViewModel mpalabrasviewModel;
    private List<PalabrasEntity> allPalabras = new ArrayList<>();
    private List<PreguntasEntity> allPreguntas = new ArrayList<>();
    private int posicion, dificultad,aleatorio, letrasConseguidas=0, ronda=0;
    private PalabrasEntity palabraAleatoria;
    private PreguntasEntity preguntaActual;
    private TextView palabra, pregunta, letras;
    private Button si, no;
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
        this.v= inflater.inflate(R.layout.fragment_que_soy, container, false);
        findViewById();
        sp = new SoundPool(10, AudioManager.STREAM_MUSIC,1);
        sonido_de_tecla= sp.load(getActivity(),R.raw.tecla,1);
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
        si=v.findViewById(R.id.BT_si);
        no=v.findViewById(R.id.Bt_no);
        letras=v.findViewById(R.id.TV_LetrasConseguidasQueSoy);
    }
    private void onClick() {
        si.setOnClickListener(this);
        no.setOnClickListener(this);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mpalabrasviewModel = ViewModelProviders.of(this).get(ModosJuegosViewModel.class);
        onClick();
    }


    @Override
    public void onClick(View v) {
        int view = v.getId();
        sonido();
        if(view==R.id.BT_si){
            if(comprobarPalabra(palabraAleatoria,preguntaActual)){
                letrasConseguidas++;
                nuevaRonda();
            }else{
                letrasConseguidas--;
                nuevaRonda();
            }
        }
        if(view==R.id.Bt_no){
            if(comprobarPalabra(palabraAleatoria,preguntaActual)){
                letrasConseguidas--;
                nuevaRonda();
            }else{
                letrasConseguidas++;
                nuevaRonda();
            }
        }
    }

    private boolean comprobarPalabra(PalabrasEntity palabra, PreguntasEntity pregunta) {
        boolean valido=false;

        if(palabra.isArticulo()&&pregunta.isArticulo()){
         valido=true;
        }else if (palabra.isInterjeccion()&&pregunta.isInterjeccion()){
            valido=true;
        } else if(palabra.isConjuncion()&&pregunta.isConjuncion()){
            valido=true;
        } else if(palabra.isPreposicion()&&pregunta.isPreposicion()){
            valido=true;
        } else if(palabra.isVerbo()&&pregunta.isVerbo()){
            valido=true;
        } else if(palabra.isPronombre()&&pregunta.isPronombre()){
            valido=true;
        } else if(palabra.isSustantivo()&&pregunta.isSustantivo()){
            valido=true;
        } else if(palabra.isAdjetivo()&&pregunta.isAdjetivo()){
            valido=true;
        } else if(palabra.isAdverbio()&&pregunta.isAdverbio()){
            valido=true;
        }

        return valido;
    }

    private void nuevaRonda(){
        ronda++;
        if(ronda<6) {
            letras.setText(String.valueOf(letrasConseguidas));
            NuevaPregunta(allPreguntas);
        }else if (dificultad==1&&ronda==6){
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerJuegos, PantallaEscaleraInfinitaFragment.newInstance(palabraAleatoria.getPalabra(),letrasConseguidas))
                    .commit();
        }else if(dificultad!=1&&ronda==6){
            DisplayMetrics metrics = new DisplayMetrics();
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels; // ancho absoluto en pixels
            int height = metrics.heightPixels; // alto absoluto en pixels
            if (height<=600&&width<=900) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerJuegos, pantallaEscaleraInfinitaPantallaPequeña.newInstance(palabraAleatoria.getPalabra(), letrasConseguidas, dificultad, 2))
                        .commit();
            }else{
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerJuegos, pantallaEscaleraInfinitaMedioDificilFragment.newInstance(palabraAleatoria.getPalabra(), letrasConseguidas, dificultad, 2))
                        .commit();
            }
        }
    }
    private void sonido() {
        if(SharedPreferentManager.getIntegerValue("soundMode")==-1)
            sp.play(sonido_de_tecla,0.4f,0.4f,1,0,0);
    }

}