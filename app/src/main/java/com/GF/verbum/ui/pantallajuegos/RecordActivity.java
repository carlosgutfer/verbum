package com.GF.verbum.ui.pantallajuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.GF.verbum.R;
import com.GF.verbum.commun.Constantes;
import com.GF.verbum.commun.SharedPreferentManager;

public class RecordActivity extends AppCompatActivity {

    private TextView herramientas, escalera, que;
    private int mejorHerramientas, mejorEscalera, mejorQue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        getSupportActionBar().hide();
        findViewById();
        setText();
    }

    private void setText() {
        mejorHerramientas= SharedPreferentManager.getIntegerValue(Constantes.MEJOR_HERRAMIENTAS);
        mejorEscalera=SharedPreferentManager.getIntegerValue(Constantes.MEJOR_ESCALERA);
        mejorQue=SharedPreferentManager.getIntegerValue(Constantes.MEJOR_QUE);
        if(mejorHerramientas!=-1|mejorEscalera!=-1|mejorQue!=-1){
            if(mejorHerramientas!=-1){
                herramientas.setText("Tú mejor puntuación en Caja de herramientas es de: "+mejorHerramientas+" ¡Intenta mejorarlo!");
            }else{
                herramientas.setText("");
            }
            if(mejorEscalera!=-1){
                escalera.setText("Tú mejor puntuación en Escalera infinita es de: "+mejorEscalera+" ¡Intenta mejorarlo!");
            }else{
                escalera.setText("");
            }
            if(mejorQue!=-1){
                que.setText("Tú mejor puntuación en ¿Qué soy? es de: "+mejorQue+" ¡Intenta mejorarlo!");
            }else{
                que.setText("");
            }
        }
    }

    private void findViewById() {
    herramientas=findViewById(R.id.TV_RecordHerramientas);
    escalera=findViewById(R.id.TV_RecordEscalera);
    que=findViewById(R.id.TV_RecordQue);
    }
}