package com.GF.verbum.ui.pantallajuegos.modoJuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.GF.verbum.R;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.CajaDeHerramientas.HerramientasFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.QueSoy.QueSoyFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.escaleraInfinita.PantallaEscaleraInfinitaFragment;

public class pantalla_juegos extends AppCompatActivity {
 private int modoJuego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.pantalla_juegos_activity);
        modoJuego=getIntent().getIntExtra("ModoJuego",0);

        if (savedInstanceState == null) {
            if (modoJuego==1) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, HerramientasFragment.newInstance())
                        .commitNow();
            }
            if(modoJuego==2){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, QueSoyFragment.newInstance())
                        .commitNow();
            }
            if(modoJuego==3){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,  PantallaEscaleraInfinitaFragment.newInstance())
                        .commitNow();
            }
        }
    }
}
