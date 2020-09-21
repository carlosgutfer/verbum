package com.GF.verbum.ui.pantallajuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.GF.verbum.R;
import com.GF.verbum.ui.pantallajuegos.Sintaxis.SintaxisActivity;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.EleccionJuegoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button jugar,mejores, sintaxis;
    private  int sonido_de_tecla;
    SoundPool sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jugar=findViewById(R.id.BT_Jugar);
        mejores=findViewById(R.id.BT_Mejores);
        sintaxis=findViewById(R.id.Bt_Sintaxis);
        mejores.setOnClickListener(this);
        jugar.setOnClickListener(this);
        sintaxis.setOnClickListener(this);
         sp = new SoundPool(10, AudioManager.STREAM_MUSIC,1);
        sonido_de_tecla= sp.load(MainActivity.this,R.raw.espacio,1);
    }

    @Override
    public void onClick(View v) {
        int view=v.getId();
        sonido();
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

    public void sonido(){
     /*   if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                                                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
            SoundPool sp = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
            sonido_de_tecla= sp.load(this,R.raw.tecla,1);
            sp.play(sonido_de_tecla,1,1,1,0,0);
        }else{
*/

        sp.play(sonido_de_tecla,1,1,1,0,0);
       /* MediaPlayer md = MediaPlayer.create(this,R.raw.espacio);
        md.start();*/

    }
}
