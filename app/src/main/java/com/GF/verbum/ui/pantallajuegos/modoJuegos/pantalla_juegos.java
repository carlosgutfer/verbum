package com.GF.verbum.ui.pantallajuegos.modoJuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.GF.verbum.R;
import com.GF.verbum.ui.pantallajuegos.MainActivity;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.CajaDeHerramientas.HerramientasFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.QueSoy.QueSoyFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.escaleraInfinita.PantallaEscaleraInfinitaFragment;

public class pantalla_juegos extends AppCompatActivity {
 private int modoJuego;
 private int dificultad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.pantalla_juegos_activity);
        dificultad = getIntent().getIntExtra("dificultad", -1);
        modoJuego = getIntent().getIntExtra("ModoJuego", -1);

        if (savedInstanceState == null&dificultad==-1) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerJuegos, EleccionDificultadFragment.newInstance(modoJuego))
                    .commitNow();

        }

        if (savedInstanceState == null&dificultad!=-1) {

            if (modoJuego == 1) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerJuegos, HerramientasFragment.newInstance(dificultad))
                        .commitNow();

            }
            if (modoJuego == 2) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerJuegos, QueSoyFragment.newInstance(dificultad))
                        .commitNow();
            }
            if (modoJuego == 3) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerJuegos, PantallaEscaleraInfinitaFragment.newInstance(dificultad))
                        .commitNow();

            }
        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }
}
