package com.GF.verbum.ui.pantallajuegos.Sintaxis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.GF.verbum.R;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.MainActivity;
import com.GF.verbum.ui.pantallajuegos.Sintaxis.tiposSintaxisFragment;

public class SintaxisActivity extends AppCompatActivity implements tiposSintaxisFragment.OnListFragmentInteractionListener {
    private MediaPlayer md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startMusic();
        setContentView(R.layout.activity_sintaxis);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, tiposSintaxisFragment.newInstance(2))
                .commit();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(md!=null)
        md.release();
        finish();
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