package com.GF.verbum.ui.pantallajuegos.nuevaOportunidad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.GF.verbum.R;


public class nuevaOportunidadDialogFragment extends DialogFragment {
    private String nombre;

    public static nuevaOportunidadDialogFragment newInstance(String nombre) {

        Bundle args = new Bundle();
        nuevaOportunidadDialogFragment fragment = new nuevaOportunidadDialogFragment();
        args.putString("nombre",nombre);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_nueva_oportuniad, container, false);
        nombre=getArguments().getString("nombre");


        return view;
    }
}
