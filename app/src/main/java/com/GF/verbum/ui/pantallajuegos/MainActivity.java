package com.GF.verbum.ui.pantallajuegos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.R;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.Sintaxis.SintaxisActivity;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.EleccionJuegoActivity;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.ModosJuegosViewModel;
import com.GF.verbum.ui.pantallajuegos.record.RecordActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button jugar,mejores, sintaxis;
    private ImageView sound;
    private  int sonido_de_tecla;
    private SoundPool sp;
    private MediaPlayer md;
    private List<PalabrasEntity> allPalabras = new ArrayList<>();
    private int position;

    private ModosJuegosViewModel mpalabrasviewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        mejores.setOnClickListener(this);
        jugar.setOnClickListener(this);
        sintaxis.setOnClickListener(this);
        sound.setOnClickListener(this);
        sp = new SoundPool(10, AudioManager.STREAM_MUSIC,1);
        sonido_de_tecla= sp.load(MainActivity.this,R.raw.espacio,1);

        if(SharedPreferentManager.getIntegerValue("soundMode")==1){
            sound.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_volume_off_24));
        }
        mpalabrasviewModel = new ViewModelProvider(this).get(ModosJuegosViewModel.class);
        mpalabrasviewModel.getAllPalabras().observe(this, new Observer<List<PalabrasEntity>>() {
            @Override
            public void onChanged(List<PalabrasEntity> palabrasEntities) {

            }
        });



    }

    private void findViewById() {
        jugar=findViewById(R.id.BT_Jugar);
        mejores=findViewById(R.id.BT_Mejores);
        sintaxis=findViewById(R.id.Bt_Sintaxis);
        sound=findViewById(R.id.IB_sound);
    }

    @Override
    public void onClick(View v) {
        int view=v.getId();
        sonido();
        if(view==R.id.BT_Jugar){
            Intent i = new Intent(this, EleccionJuegoActivity.class);
            if(md!=null){
            position=md.getCurrentPosition();
            i.putExtra("position",position);}

            startActivity(i);
            finish();
           }
        if(view==R.id.BT_Mejores){
            Intent i = new Intent(this, RecordActivity.class);
            if(md!=null){
                position=md.getCurrentPosition();
            i.putExtra("position",position);}
            startActivity(i);
            finish();
        }
        if(view==R.id.Bt_Sintaxis){
            Intent i = new Intent(this, SintaxisActivity.class);
            if(md!=null){
                position=md.getCurrentPosition();
            i.putExtra("position",position);}
            startActivity(i);
            finish();
        }
        if(view==R.id.IB_sound){
          soundMode();
        }
    }

    private void soundMode() {
        int soundMode= SharedPreferentManager.getIntegerValue("soundMode");
        if(soundMode==1){
            sound.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_volume_up_24));
            SharedPreferentManager.setIntegerValue("soundMode",-1);
            startMusic();
        }
        if(soundMode==-1){
            if(md.isPlaying()&&md!=null){
                md.release();
                md=null;
            }
            sound.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_volume_off_24));
            SharedPreferentManager.setIntegerValue("soundMode",1);
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
        startMusic();
    }

    private void startMusic() {
        if(SharedPreferentManager.getIntegerValue("soundMode")==-1) {
            md = MediaPlayer.create(this, R.raw.export);
            md.setVolume(0.5f, 0.5f);
            int soundPosition = getIntent().getIntExtra("position", 0);
            md.seekTo(soundPosition);
            md.setLooping(true);
            md.start();
        }
    }

}
