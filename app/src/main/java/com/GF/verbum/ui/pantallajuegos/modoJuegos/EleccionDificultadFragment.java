package com.GF.verbum.ui.pantallajuegos.modoJuegos;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.GF.verbum.R;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.morfologia.CajaDeHerramientas.HerramientasFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.morfologia.QueSoy.QueSoyFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.morfologia.escaleraInfinita.PantallaEscaleraInfinitaFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.morfologia.escaleraInfinita.pantallaEscaleraInfinitaMedioDificilFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.morfologia.escaleraInfinita.pantallaEscaleraInfinitaPantallaPequeña;


public class EleccionDificultadFragment extends Fragment implements View.OnClickListener {


 private Button facil, medio, dificil;
 private View v;
 private static String MODODEJUEGO;
 private int modo;
   private SoundPool sp;
   private  int sonido_de_tecla;


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
        sp = new SoundPool(10, AudioManager.STREAM_MUSIC,1);
        sonido_de_tecla= sp.load(getActivity(),R.raw.espacio,1);
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
        sonido();
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

            DisplayMetrics metrics = new DisplayMetrics();
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels; // ancho absoluto en pixels
            int height = metrics.heightPixels; // alto absoluto en pixels

            if (height<=900&&width<=500) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerJuegos, pantallaEscaleraInfinitaPantallaPequeña.newInstance(i))
                        .commitNow();

            } else{
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerJuegos, pantallaEscaleraInfinitaMedioDificilFragment.newInstance(i))
                        .commitNow();
            }

        }

    }
    public void sonido(){
        if(SharedPreferentManager.getIntegerValue("soundMode")==-1)
            sp.play(sonido_de_tecla,1,1,1,0,0);
    }
}