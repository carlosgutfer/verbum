package com.GF.verbum.ui.pantallajuegos.modoJuegos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.GF.verbum.R;


public class RecordFragment extends Fragment {

    private static final String ARG_PARAM1 = "letras";
    private static final String ARG_PARAM2 = "urlPalabra";
    private static final String ARG_PARAM3 = "palabra";


    private TextView letrasYPalabra;

    private String urlPalabra;
    private int letras;
    private String Palabra;

    public RecordFragment() {
        // Required empty public constructor
    }


    public static RecordFragment newInstance(int letras,String urlPalabra,String Palabra) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, letras);
        args.putString(ARG_PARAM2, urlPalabra);
        args.putString(ARG_PARAM3,Palabra);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBundle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_record, container, false);
        findViewById(v);
        letrasYPalabra.setText("¡Felicidades! Has conseguido "+letras+ " letras y la última palabra era "+ Palabra+"." +
                "Si quieres revisar que funciones puede tener, pulsa sobre el diccionario.");
        return v;
    }

    private void getBundle() {
        urlPalabra=getArguments().getString(ARG_PARAM2);
        Palabra=getArguments().getString(ARG_PARAM3);
        letras=getArguments().getInt(ARG_PARAM1);

    }

    private void findViewById(View v) {
        letrasYPalabra=v.findViewById(R.id.TV_PalabraYgemas);

    }
}