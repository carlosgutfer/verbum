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
            Intent i = new Intent(getActivity(), pantalla_juegos.class);
            i.putExtra("dificultad",1);
            i.putExtra("ModoJuego",modo);
            startActivity(i);


        }
        if(View==R.id.BT_Medio){
            Intent i = new Intent(getActivity(), pantalla_juegos.class);
            i.putExtra("dificultad",2);
            i.putExtra("ModoJuego",modo);
            startActivity(i);

        }
        if(View==R.id.BT_Dificil){
            Intent i = new Intent(getActivity(), pantalla_juegos.class);
            i.putExtra("dificultad",3);
            i.putExtra("ModoJuego",modo);
            startActivity(i);

        }



    }
}