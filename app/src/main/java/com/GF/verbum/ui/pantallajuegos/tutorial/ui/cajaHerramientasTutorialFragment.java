package com.GF.verbum.ui.pantallajuegos.tutorial.ui;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.GF.verbum.R;


public class cajaHerramientasTutorialFragment extends Fragment {
    private TextView cajaHerramientas, texto;
    private View v;
    public static cajaHerramientasTutorialFragment newInstance() {
        Bundle args = new Bundle();
        cajaHerramientasTutorialFragment fragment = new cajaHerramientasTutorialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       this.v= inflater.inflate(R.layout.fragment_caja_herramientas_tutorial, container, false);
        cajaHerramientas=v.findViewById(R.id.TV_tutorialCajaHerramientas);
        texto=v.findViewById(R.id.TV_cajaHerramientas);
        texto.setText(getResources().getString(R.string.tutorialCajaHerramientas));
        cajaHerramientas.setText(Html.fromHtml(getResources().getString(R.string.cajaHerramientasTutorialSubrayado)));

        return v;
    }


}