package com.GF.verbum.ui.pantallajuegos.modoJuegos.analisis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private TextView TV_Frase;
    private int idFrase;
    private analisisViewModel anaviewModel;
    private modosDeJuegoViewModel modoviewModel;
    private ArrayList<palfraEntity> palfra = new ArrayList<>();
    private ArrayList<frasesEntity> allFrases = new ArrayList<>();
    private ArrayList<PalabrasEntity> allPalabras = new ArrayList<>();

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
        findByID(v);
        modoviewModel = new ViewModelProvider(this).get(modosDeJuegoViewModel.class);
        modoviewModel.getAllFrases().observe(getActivity(), new Observer<List<frasesEntity>>() {
            @Override
            public void onChanged(List<frasesEntity> frasesEntities) {
               setFrases(frasesEntities);
               newID();
            }
        });

        anaviewModel = new ViewModelProvider(this).get(analisisViewModel.class);
        anaviewModel.getAllPalfra(idFrase).observe(getActivity(), new Observer<List<palfraEntity>>() {
           @Override
           public void onChanged(List<palfraEntity> palfraEntities) {
               palfra = setPalFra(palfraEntities);
           }
       });

        setTextToTV();

        return v;
    }


    private void findByID(View v) {
        TV_Frase = v.findViewById(R.id.TV_frasPal);
    }




    // metodo aleatorio
    private void newID() {
        int random = (int) (Math.random()*allFrases.size());
        this.idFrase = allFrases.get(random).getIdFrase();

    }

        // Cojo todas las frases
    private ArrayList<frasesEntity> setFrases(List<frasesEntity> frase) {
        for(int i=0;i<frase.size();i++){
            this.allFrases.add(frase.get(i));
        }
        return allFrases;
    }
    // tomo de la tabla palBra todos los resultados que tengan ID de la frase aleatoria
    private ArrayList<palfraEntity> setPalFra(List<palfraEntity> palfraEntities) {
        for(int i=0;i<palfraEntities.size();i++){
            this.palfra.add(palfraEntities.get(i));
        }

        return palfra;
    }
    //Del arrayList palfra tomo el idPalabra de cada registro y en la tabla palabra tomo el string que corresponde a ese ID
    private void setTextToTV() {
        String frase ="";
        for(int i =0;i<palfra.size();i++){
            frase += modoviewModel.getPalFrases(palfra.get(i).getIdPalabra());
        }
        TV_Frase.setText(frase);
    }




}