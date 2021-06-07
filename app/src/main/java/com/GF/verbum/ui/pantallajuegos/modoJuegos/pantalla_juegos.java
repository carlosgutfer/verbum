package com.GF.verbum.ui.pantallajuegos.modoJuegos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.GF.verbum.DB.Entities.tiposEntity;
import com.GF.verbum.R;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.MainActivity;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.analisis.analisisViewModel;

import java.util.List;

public class pantalla_juegos extends AppCompatActivity {
    private int modoJuego;
    private MediaPlayer md;
    private analisisViewModel aViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_juegos);
        modoJuego = getIntent().getIntExtra("ModoJuego", -1);

        aViewModel = new ViewModelProvider(this).get(analisisViewModel.class);
        aViewModel.getAllTipos().observe(this, new Observer<List<tiposEntity>>() {
            @Override
            public void onChanged(List<tiposEntity> tiposEntities) {

            }
        });


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerJuegos, EleccionDificultadFragment.newInstance(modoJuego))
                    .commitNow();
        }

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

    private void startMusic() {
        md = MediaPlayer.create(this, R.raw.export);
        md.setVolume(0.5f, 0.5f);
        md.seekTo(getIntent().getIntExtra("position",-1));
        md.setLooping(true);
        md.start();
    }
}
