package com.GF.verbum.ui.pantallajuegos.modoJuegos.QueSoy;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.GF.verbum.R;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.ModosJuegosViewModel;

public class QueSoyFragment extends Fragment {

    private ModosJuegosViewModel mViewModel;

    public static QueSoyFragment newInstance() {
        return new QueSoyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.que_soy_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ModosJuegosViewModel.class);
        // TODO: Use the ViewModel
    }

}