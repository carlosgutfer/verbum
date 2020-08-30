package com.GF.verbum.ui.pantallajuegos.Sintaxis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.GF.verbum.R;
import com.GF.verbum.ui.pantallajuegos.Sintaxis.tiposSintaxisFragment;

public class SintaxisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintaxis);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, tiposSintaxisFragment.newInstance(2))
                .commit();
    }
}