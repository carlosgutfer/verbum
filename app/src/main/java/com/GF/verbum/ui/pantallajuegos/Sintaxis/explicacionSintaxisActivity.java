package com.GF.verbum.ui.pantallajuegos.Sintaxis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.GF.verbum.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class explicacionSintaxisActivity extends AppCompatActivity {
private String nombre, texto;
private TextView titulo, informacion;
private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicacion_sintaxis);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        getIntentMethod();
        findView();
        titulo.setText(nombre);
        informacion.setText(texto);
        mAdView = findViewById(R.id.adViewBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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