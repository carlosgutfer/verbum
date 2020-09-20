package com.GF.verbum.ui.pantallajuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.GF.verbum.R;
import com.GF.verbum.ui.pantallajuegos.Sintaxis.SintaxisActivity;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.EleccionJuegoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button jugar,mejores, sintaxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        jugar=findViewById(R.id.BT_Jugar);
        mejores=findViewById(R.id.BT_Mejores);
        sintaxis=findViewById(R.id.Bt_Sintaxis);

        mejores.setOnClickListener(this);
        jugar.setOnClickListener(this);
        sintaxis.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int view=v.getId();
        if(view==R.id.BT_Jugar){
        Intent i = new Intent(this, EleccionJuegoActivity.class);
        startActivity(i);
        finish();}
        if(view==R.id.BT_Mejores){
            Intent i = new Intent(this, RecordActivity.class);
            startActivity(i);
            finish();
        }
        if(view==R.id.Bt_Sintaxis){
            Intent i = new Intent(this, SintaxisActivity.class);
            startActivity(i);
            finish();
        }
    }
}
