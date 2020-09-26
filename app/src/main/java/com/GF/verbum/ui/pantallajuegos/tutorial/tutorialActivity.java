package com.GF.verbum.ui.pantallajuegos.tutorial;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;

import com.GF.verbum.R;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.MainActivity;
import com.GF.verbum.ui.pantallajuegos.tutorial.ui.cajaHerramientasTutorialFragment;
import com.GF.verbum.ui.pantallajuegos.tutorial.ui.escaleraInfinitaTutorial;
import com.GF.verbum.ui.pantallajuegos.tutorial.ui.queSoyTutorialFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class tutorialActivity extends AppCompatActivity {
    private MediaPlayer md;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment f = null;
            switch (item.getItemId()) {
                case R.id.navigation_escaleraInfinita:
                    f= escaleraInfinitaTutorial.newInstance();
                    break;
                case R.id.navigation_queSoy:
                    f= queSoyTutorialFragment.newInstance();
                    break;
                case R.id.navigation_cajaHerramientas:
                    f= cajaHerramientasTutorialFragment.newInstance();
                    break;
            }

            if (f!=null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,f)
                        .commit();
                return  true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);


        BottomNavigationView navView = findViewById(R.id.view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.nav_host_fragment, escaleraInfinitaTutorial.newInstance())
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Intent i = new Intent(this, MainActivity.class);
        if(md!=null){
            i.putExtra("position",md.getCurrentPosition());
            md.release();
            md = null;
        }
        startActivity(i);
        finish();
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