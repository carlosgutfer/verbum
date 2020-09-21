package com.GF.verbum.ui.pantallajuegos.modoJuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.GF.verbum.R;

public class EleccionJuegoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button herramientas, escalera, QS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_juego);
    findViewById();
        herramientas.setOnClickListener(this);
        escalera.setOnClickListener(this);
        QS.setOnClickListener(this);
    }

    private void findViewById() {
        herramientas=findViewById(R.id.BT_Herramientas);
        escalera=findViewById(R.id.BT_Escalera);
        QS=findViewById(R.id.BT_QueSoy);
    }

    @Override
    public void onClick(View v) {
      int view = v.getId();
        if(view==R.id.BT_Herramientas){
            Intent i = new Intent(this, pantalla_juegos.class);
            i.putExtra("ModoJuego",1);
            startActivity(i);
            finish();
        }
        if(view==R.id.BT_QueSoy){
            Intent i = new Intent(this, pantalla_juegos.class);
            i.putExtra("ModoJuego",2);
            startActivity(i);
            finish();

        }
        if(view==R.id.BT_Escalera){
            Intent i = new Intent(this, pantalla_juegos.class);
            i.putExtra("ModoJuego",3);
            startActivity(i);
            finish();

        }
        finish();
    }
}