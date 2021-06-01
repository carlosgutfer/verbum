package com.GF.verbum.ui.pantallajuegos.modoJuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.GF.verbum.R;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.MainActivity;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.morfologia.EleccionJuegoMorfologiaActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class eleccionSinMorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_sintaxis, bt_morfologia;
    private  int sonido_de_tecla;
    private SoundPool sp;
    private MediaPlayer md;
    private AdView mAdView;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_sin_mor);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        findView();
        onClickthis();

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        sp = new SoundPool(10, AudioManager.STREAM_MUSIC,1);
        sonido_de_tecla= sp.load(this,R.raw.espacio,1);
    }

    private void findView() {
        mAdView = findViewById(R.id.adViewBanner);
        bt_sintaxis = findViewById(R.id.BT_sintaxis_eleccion);
        bt_morfologia = findViewById(R.id.BT_morfologia);
    }

    private void onClickthis() {
        bt_morfologia.setOnClickListener(this);
        bt_sintaxis.setOnClickListener(this);
    }

    public void setSonido_de_tecla(){
        if(SharedPreferentManager.getIntegerValue("soundMode")==-1)
            sp.play(sonido_de_tecla,1,1,1,0,0);
    }

    private void startMusic() {
        md = MediaPlayer.create(this, R.raw.export);
        md.setVolume(0.5f, 0.5f);
        md.seekTo(getIntent().getIntExtra("position",-1));
        md.setLooping(true);
        md.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(SharedPreferentManager.getIntegerValue("soundMode")==-1)
            startMusic();

    }
    @Override
    protected void onPause() {
        super.onPause();
        if(md!=null)
            md.release();
        md = null;
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

    @Override
    public void onClick(View v) {
        setSonido_de_tecla();
        int view = v.getId();

        if (view == R.id.BT_morfologia){
            Intent i = new Intent(this, EleccionJuegoMorfologiaActivity.class);
            check_md(i);
            startActivity(i);
            finish();
        }if (view == R.id.BT_sintaxis_eleccion){
            Intent i = new Intent(this, pantalla_juegos.class);
            i.putExtra("ModoJuego",4);
            check_md(i);
            startActivity(i);
            finish();
        }

    }

    private void check_md(Intent i) {
        if(md!=null){
            position=md.getCurrentPosition();
            i.putExtra("position",position);}
    }
}