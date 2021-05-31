package com.GF.verbum.ui.pantallajuegos.modoJuegos.analisis;

import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.frasesEntity;
import com.GF.verbum.DB.Entities.palfraEntity;
import com.GF.verbum.R;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.modosDeJuegoViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link analisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class analisFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int idFrase;
    private analisisViewModel anaviewModel;
    private modosDeJuegoViewModel modoviewModel;
    private ArrayList<palfraEntity> palfra;
    private ArrayList<frasesEntity> allFrases;
    public analisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment analisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static analisFragment newInstance(int param1) {
        analisFragment fragment = new analisFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
             int dificultad = getArguments().getInt(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  v =  inflater.inflate(R.layout.fragment_analis, container, false);
        modoviewModel = new ViewModelProvider(this).get(modosDeJuegoViewModel.class);
        modoviewModel.getAllFrases().observe(getActivity(), new Observer<List<frasesEntity>>() {
            @Override
            public void onChanged(List<frasesEntity> frasesEntities) {
                setPalabras(frasesEntities);
                newID();
            }
        });
        anaviewModel = new analisisViewModel(getActivity().getApplication(), idFrase);
        anaviewModel.getallPALFRA().observe(getActivity(), new Observer<List<palfraEntity>>() {
           @Override
           public void onChanged(List<palfraEntity> palfraEntities) {
                setFrases(palfraEntities);
           }
       });


        return v;
    }

    private void newID() {
        int random = (int) (Math.random()*allFrases.size());
        this.idFrase = allFrases.get(random).getIdFrase();

    }

    private void setFrases(List<palfraEntity> palfraEntities) {
        for(int i=0;i<palfraEntities.size();i++){
            this.palfra.add(palfraEntities.get(i));
        }

    }

    private void setPalabras(List<frasesEntity> frase) {
        for(int i=0;i<frase.size();i++){
                this.allFrases.add(frase.get(i));
        }
    }

}