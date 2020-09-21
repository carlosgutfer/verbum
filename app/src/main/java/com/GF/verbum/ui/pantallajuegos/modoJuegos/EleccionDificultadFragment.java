package com.GF.verbum.ui.pantallajuegos.modoJuegos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.GF.verbum.R;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.CajaDeHerramientas.HerramientasFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.QueSoy.QueSoyFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.escaleraInfinita.PantallaEscaleraInfinitaFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.escaleraInfinita.pantallaEscaleraInfinitaMedioDificilFragment;


public class EleccionDificultadFragment extends Fragment implements View.OnClickListener {


 private Button facil, medio, dificil;
 private View v;
 private static String MODODEJUEGO;
 private int modo;



    public EleccionDificultadFragment() {
    }


    // TODO: Rename and change types and number of parameters
    public static EleccionDificultadFragment newInstance(int modojuego) {
        EleccionDificultadFragment fragment = new EleccionDificultadFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(MODODEJUEGO, modojuego);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_eleccion_dificultad, container, false);
        modo=getArguments().getInt(MODODEJUEGO);
        findViewById();
        setOnClick();
        return v;
    }

    private void setOnClick() {
        facil.setOnClickListener(this);
        medio.setOnClickListener(this);
        dificil.setOnClickListener(this);
    }

    private void findViewById() {
        facil=v.findViewById(R.id.BT_Facil);
        medio=v.findViewById(R.id.BT_Medio);
        dificil=v.findViewById(R.id.BT_Dificil);
    }

    @Override
    public void onClick(View v) {
        int View=v.getId();
        if(View==R.id.BT_Facil){
           eleccionDificultad(1);
        }
        if(View==R.id.BT_Medio){
            eleccionDificultad(2);
        }
        if(View==R.id.BT_Dificil){
            eleccionDificultad(3);
        }

    }

    private void eleccionDificultad(int i) {
        if (modo == 1) {
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerJuegos, HerramientasFragment.newInstance(i))
                    .commitNow();
        } else if (modo == 2) {
            getActivity()
                    .getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerJuegos, QueSoyFragment.newInstance(i))
                    .commitNow();
        } else if (modo == 3&&i==1) {
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerJuegos, PantallaEscaleraInfinitaFragment.newInstance(i))
                    .commitNow();
        }else if(modo==3){
           getActivity()
                   .getSupportFragmentManager()
                   .beginTransaction()
                    .replace(R.id.containerJuegos, pantallaEscaleraInfinitaMedioDificilFragment.newInstance(i))
                    .commitNow();
        }

    }
}