package com.GF.verbum.ui.pantallajuegos.modoJuegos.morfologia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.GF.verbum.R;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.MainActivity;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.pantalla_juegos;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class EleccionJuegoMorfologiaActivity extends AppCompatActivity implements View.OnClickListener {
    private Button herramientas, escalera, QS;

    private  int sonido_de_tecla;
    SoundPool sp;
    private MediaPlayer md;
    private AdView mAdView;
    private ImageView dificultad;
    private TextView aclaracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_juego);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        findViewById();
        herramientas.setOnClickListener(this);
        escalera.setOnClickListener(this);
        QS.setOnClickListener(this);
        dificultad.setOnClickListener(this);
        mAdView = findViewById(R.id.adViewBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        sp = new SoundPool(10, AudioManager.STREAM_MUSIC,1);
        sonido_de_tecla= sp.load(EleccionJuegoMorfologiaActivity.this,R.raw.espacio,1);
    }

    private void findViewById() {
        herramientas=findViewById(R.id.BT_Herramientas);
        escalera=findViewById(R.id.BT_Escalera);
        QS=findViewById(R.id.BT_QueSoy);
        dificultad=findViewById(R.id.IV_setError);
        aclaracion=findViewById(R.id.TV_aclaración);
    }

    @Override
    public void onClick(View v) {
        sonido();
        int view = v.getId();
        if(view==R.id.BT_Herramientas){
            Intent i = new Intent(this, pantalla_juegos.class);
            i.putExtra("ModoJuego",1);
            if(md!=null)
                i.putExtra("position",md.getCurrentPosition());
            startActivity(i);
            finish();
        }
        if(view==R.id.BT_QueSoy){
            Intent i = new Intent(this, pantalla_juegos.class);
            i.putExtra("ModoJuego",2);
            if(md!=null)
                i.putExtra("position",md.getCurrentPosition());
            startActivity(i);
            finish();

        }
        if(view==R.id.BT_Escalera){
            Intent i = new Intent(this, pantalla_juegos.class);
            i.putExtra("ModoJuego",3);
            if(md!=null)
            i.putExtra("position",md.getCurrentPosition());
            startActivity(i);
            finish();

        }
        if(view==R.id.IV_setError){
                if(aclaracion.getVisibility()==View.VISIBLE){

                    aclaracion.setVisibility(View.INVISIBLE);
                }else if (aclaracion.getVisibility()==View.INVISIBLE){
                    String aclaraciones="";
                    aclaraciones=getString(R.string.aclaraciones)+'\n'+getString(R.string.aclaraciones2)+'\n'+getString(R.string.aclaraciones3);
                    aclaracion.setText(aclaraciones);
                    aclaracion.setVisibility(View.VISIBLE);
                }
        }
    }
    public void sonido(){
        if(SharedPreferentManager.getIntegerValue("soundMode")==-1)
            sp.play(sonido_de_tecla,1,1,1,0,0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(md!=null)
        md.release();
        md = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(SharedPreferentManager.getIntegerValue("soundMode")==-1){
        startMusic();}
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        if(md!=null)
            i.putExtra("position",md.getCurrentPosition());
        startActivity(i);
        finish();
    }

    private void startMusic() {
        md = MediaPlayer.create(this, R.raw.export);
        md.setVolume(0.5f, 0.5f);
        md.seekTo(getIntent().getIntExtra("position",-1));
        md.setLooping(true);
        md.start();
    }
}