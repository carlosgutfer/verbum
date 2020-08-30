package com.GF.verbum.ui.pantallajuegos.modoJuegos.escaleraInfinita;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.R;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.ModosJuegosViewModel;

import java.util.List;

public class PantallaEscaleraInfinitaFragment extends Fragment {


    List<PalabrasEntity> allPalabras;
    private ModosJuegosViewModel mpalabrasviewModel;
    private TextView palabra,pregunta;
    private RadioGroup RGSN;
    private RadioButton si,no;
    private Button comprobar;
    private int constante;
    private View v;

    public static PantallaEscaleraInfinitaFragment newInstance() {

        return new PantallaEscaleraInfinitaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pantalla_juegos_fragment, container, false);
        this.v=view;
        palabra=view.findViewById(R.id.TV_palabra);
        pregunta=view.findViewById(R.id.TV_pregunta);
        si=view.findViewById(R.id.RB_si);
        no=view.findViewById(R.id.RB_no);
        RGSN=view.findViewById(R.id.RG_SN);
        comprobar=view.findViewById(R.id.BT_comprobar);
        mpalabrasviewModel=new ViewModelProvider(this).get(ModosJuegosViewModel.class);
        mpalabrasviewModel.getAllPalabras().observe(getActivity(), new Observer<List<PalabrasEntity>>() {
            @Override
            public void onChanged(List<PalabrasEntity> palabrasEntities) {
                setSustantivos(palabrasEntities);
                palabra.setText(palabrasEntities.get(0).getPalabra());
                pregunta.setText("¿Tiene género?");
                RGSN.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (si.isChecked()) {
                            constante = 1;

                        }
                        if (no.isChecked()) {
                            constante = 2;

                        }

                    }
                });
            }
        });

        return view;
    }











    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        palabra=v.findViewById(R.id.TV_palabra);
        comprobar=v.findViewById(R.id.BT_comprobar);
        comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = allPalabras.size();
                String getPalabra="allPlabras.get";

                if(constante==1&allPalabras.get(0).getPalabra().equals(palabra.getText())) {
                    Toast.makeText(getActivity(), "Mensaje SI preuba", Toast.LENGTH_LONG).show();
                   int  numAleatorio=(int)Math.floor(Math.random()*(0-(size+1))+(size));
                    palabra.setText(allPalabras.get(numAleatorio).getPalabra());
                }else{
                    Toast.makeText(getActivity(), "Mensaje NO preuba", Toast.LENGTH_LONG).show();

                }

            }
        });
        // TODO: Use the ViewModel
    }



    private void setSustantivos(List<PalabrasEntity> palabras) {
        this.allPalabras=palabras;
    }
}
