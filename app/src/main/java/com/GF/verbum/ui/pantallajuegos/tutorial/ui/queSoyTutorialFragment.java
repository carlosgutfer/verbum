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


public class queSoyTutorialFragment extends Fragment {
    private TextView queSoy;
    public static queSoyTutorialFragment newInstance() {

        Bundle args = new Bundle();

        queSoyTutorialFragment fragment = new queSoyTutorialFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_que_soy_tutorial, container, false);
        queSoy=root.findViewById(R.id.TV_tutorialQueSoy);
        queSoy.setText(Html.fromHtml(getResources().getString(R.string.QueSoyTutorialSubrayado)));
        return root;
    }
}