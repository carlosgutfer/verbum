package com.GF.verbum.ui.pantallajuegos.tutorial.ui;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.GF.verbum.R;


public class escaleraInfinitaTutorial extends Fragment {

    private TextView escalera;
    public static escaleraInfinitaTutorial newInstance() {

        Bundle args = new Bundle();

        escaleraInfinitaTutorial fragment = new escaleraInfinitaTutorial();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_escalera_infinita_tutorial, container, false);
        escalera=root.findViewById(R.id.TV_tutorialCajaHerramientas);
        escalera.setText(Html.fromHtml(getResources().getString(R.string.escaleraTutorialSubrayado)));
        return root;
    }
}