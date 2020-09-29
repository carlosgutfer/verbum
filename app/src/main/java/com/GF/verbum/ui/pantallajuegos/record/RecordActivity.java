package com.GF.verbum.ui.pantallajuegos.record;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.GF.verbum.R;
import com.GF.verbum.commun.Constantes;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.commun.user;
import com.GF.verbum.ui.pantallajuegos.MainActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    private TextView modosDeJuego,dificultad;
    private Button buscar, btmodo, btdificultad;
    private int mode=0,difficulty=0;
    private FirebaseFirestore firestore;
    public static  List<user> alluser = new ArrayList<>();
    private SoundPool sp;
    private MediaPlayer md;
    private  int sonido_de_tecla;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        sp = new SoundPool(10, AudioManager.STREAM_MUSIC,1);
        sonido_de_tecla= sp.load(RecordActivity.this,R.raw.espacio,1);
        findViewById();
        setOnClick();
        mAdView = findViewById(R.id.adViewBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void setOnClick() {
        buscar.setOnClickListener(this);
        btmodo.setOnClickListener(this);
        btdificultad.setOnClickListener(this);
    }


    private void findViewById() {
        buscar=findViewById(R.id.BT_buscar);
        modosDeJuego=findViewById(R.id.TV_modosDeJuego);
        dificultad=findViewById(R.id.TV_dificultad);
        btmodo=findViewById(R.id.BT_modoJuego);
        btdificultad=findViewById(R.id.BT_dificultad);
    }

    @Override
    public void onClick(View v) {
        sonido();
        int view =v.getId();
        if(view==R.id.BT_buscar){
            if(difficulty==0&mode==0) {
                modosDeJuego.setError(getString(R.string.modoJuegoVacio));
                dificultad.setError(getString(R.string.dificultadVacio));
            }else if(difficulty==0) {
                dificultad.setError(getString(R.string.dificultadVacio));
            }else if(mode==0) {
                modosDeJuego.setError(getString(R.string.modoJuegoVacio));
            }else{

                /*Si crece mucho, en un futuro puedo hacer que la lista carge todos los elementos y después filtrar*/
                alluser.clear();
                firestore = FirebaseFirestore.getInstance();
                firestore.collection("users")
                        .whereEqualTo("difficulty",difficulty)
                        .whereEqualTo("mode",mode)
                        .orderBy("points", Query.Direction.DESCENDING)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                for (DocumentSnapshot document : task.getResult()) {
                                    user userItem = document.toObject(user.class);
                                    alluser.add(userItem);

                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.container_record, playersFragment.newInstance(1))
                                            .commit();
                                }

                            }
                        });
            }


        }
        if(view==R.id.BT_modoJuego){
            showMenuModoJuego(v);
        }
        if(view==R.id.BT_dificultad){
            showMenuDificultad(v);
        }

    }
    public void showMenuModoJuego(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.modosdejuego);
        popup.show();
    }

    public void showMenuDificultad(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.dificultad);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.escaleraInfinitaMenu:
                modosDeJuego.setText(R.string.EscaleraInfinita);
                this.mode=3;
                return true;
            case R.id.queSoyMenu:
                modosDeJuego.setText(R.string.QueSoy);
                this.mode=2;
                return true;
            case R.id.cajaHerramientasMenu:
                modosDeJuego.setText(R.string.CajaDeHerramientas);
                this.mode=1;
                return true;
            case R.id.facilMenu:
                dificultad.setText(R.string.Facil);
                this.difficulty=1;
                return true;
            case R.id.medioMenu:
                dificultad.setText(R.string.Medio);
                this.difficulty=2;
                return true;
            case R.id.dificilMenu:
                dificultad.setText(R.string.Dificil);
                this.difficulty=3;
                return true;
        }

        return false;
    }

    public void sonido(){
        if(SharedPreferentManager.getIntegerValue("soundMode")==-1)
            sp.play(sonido_de_tecla,1,1,1,0,0);
    }
    @Override
    protected void onPause() {
        super.onPause();
        Intent i = new Intent(this, MainActivity.class);
        if(md!=null)
        i.putExtra("position",md.getCurrentPosition());
        startActivity(i);
        finish();
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