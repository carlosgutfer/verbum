package com.GF.verbum.ui.pantallajuegos.Sintaxis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.GF.verbum.R;

public class explicacionSintaxisActivity extends AppCompatActivity {
private String nombre, texto;
private TextView titulo, informacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicacion_sintaxis);
        getIntentMethod();
        findView();
        titulo.setText(nombre);
        informacion.setText(texto);
    }

    private void findView() {
        titulo=findViewById(R.id.TV_tituloSintaxis);
        informacion=findViewById(R.id.TV_textoPreguntaSintaxis);
    }

    private void getIntentMethod() {
        nombre=getIntent().getStringExtra("nombre");
        texto=getIntent().getStringExtra("texto");
    }

}