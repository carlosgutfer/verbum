package com.GF.verbum.ui.pantallajuegos.modoJuegos.escaleraInfinita;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.R;
import com.GF.verbum.commun.Constantes;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.MainActivity;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.ModosJuegosViewModel;

import java.util.ArrayList;
import java.util.List;


public class pantallaEscaleraInfinitaMedioDificilFragment extends Fragment implements View.OnClickListener {


    private List<PalabrasEntity> allPalabras = new ArrayList<>();
    private PalabrasEntity palabraAleatoria;

    private ModosJuegosViewModel mpalabrasviewModel;
    private TextView palabra;
    private View v;
    private int letrasGanadas;
    private int  posicion;
    private int dificultad;

    private TextView letras;
    private CheckBox sust, adj, pro, adv, verb, pre, conj, inter, art;
    private Button comprobar;
    private String nombre;

    public static pantallaEscaleraInfinitaMedioDificilFragment newInstance(int dificultad) {
        pantallaEscaleraInfinitaMedioDificilFragment fragment = new pantallaEscaleraInfinitaMedioDificilFragment();
        Bundle args = new Bundle();
        args.putInt("dificultad", dificultad);
        fragment.setArguments(args);
        return fragment;
    }

    public static pantallaEscaleraInfinitaMedioDificilFragment newInstance(String palabra,int letras) {
        pantallaEscaleraInfinitaMedioDificilFragment fragment = new pantallaEscaleraInfinitaMedioDificilFragment();
        Bundle args = new Bundle();
        args.putString("palabra",palabra);
        args.putInt("letras", letras);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pantalla_escalera_infinita_medio_dificil, container, false);
        this.v=view;
        findViewById();
        nombre=getArguments().getString("palabra",null);

        mpalabrasviewModel=new ViewModelProvider(this).get(ModosJuegosViewModel.class);
        mpalabrasviewModel.getAllPalabras().observe(getActivity(), new Observer<List<PalabrasEntity>>() {
            @Override
            public void onChanged(List<PalabrasEntity> palabrasEntities) {
                if(nombre==null) {
                    allPalabras = setPalabras(palabrasEntities);
                    posicion = (int) (Math.random() * allPalabras.size());
                    palabraAleatoria = allPalabras.get(posicion);
                    palabra.setText(palabraAleatoria.getPalabra());
                }else{
                    buscarPalabra(palabrasEntities);
                }
            }
        });

        return view;
    }

    private void buscarPalabra(List<PalabrasEntity> palabrasEntities) {
        for (int i =0;i<palabrasEntities.size();i++ ){
            if(nombre.equals(palabrasEntities.get(i).getPalabra())){
                palabraAleatoria = palabrasEntities.get(i);
                palabra.setText(palabraAleatoria.getPalabra());
                break;
            }
        }
    }

    private void findViewById() {
        palabra=v.findViewById(R.id.TV_palabraInfinita);
        art=v.findViewById(R.id.CB_artiuclo);
        sust=v.findViewById(R.id.CB_sustantivo);
        pro=v.findViewById(R.id.CB_pronombre);
        adj=v.findViewById(R.id.CB_Adjetivo);
        adv=v.findViewById(R.id.CB_adverbio);
        verb=v.findViewById(R.id.CB_verbo);
        pre=v.findViewById(R.id.CB_preposicion);
        conj=v.findViewById(R.id.CB_conjuncion);
        inter=v.findViewById(R.id.CB_interjeccion);
        letras=v.findViewById(R.id.TV_LetrasConseguidasEscalera);
        comprobar=v.findViewById(R.id.BT_comprobar);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onClick();

    }

    private void onClick() {
    comprobar.setOnClickListener(this);
    }

    private List<PalabrasEntity> setPalabras(List<PalabrasEntity> palabrasEntities) {
        dificultad=getArguments().getInt("dificultad");

        for(int i=0;i<palabrasEntities.size();i++){
            if(funcionesPalabras(palabrasEntities.get(i),dificultad)){
                palabraAleatoria=palabrasEntities.get(i);
                allPalabras.add(palabraAleatoria);
            }
        }
        return allPalabras;
    }

    private boolean funcionesPalabras(PalabrasEntity palabra, int dificultad) {
        boolean valido=false;
        int contador =0;
        if(palabra.isArticulo())
            contador++;
        if(palabra.isInterjeccion())
            contador++;
        if(palabra.isConjuncion())
            contador++;
        if(palabra.isPreposicion())
            contador++;
        if(palabra.isVerbo())
            contador++;
        if(palabra.isPronombre())
            contador++;
        if(palabra.isSustantivo())
            contador++;
        if(palabra.isAdjetivo())
            contador++;
        if(palabra.isAdverbio())
            contador++;
        if(dificultad==1&&contador<2){
            valido=true;
        }else if(dificultad==2&&contador<3){
            valido=true;
        }else if(dificultad==3) {
            valido=true;
        }
        return valido;
    }

    @Override
    public void onClick(View v) {
        int view = v.getId();
       if(view==R.id.BT_comprobar){
           if(comprobar()){
               mostrar();
               nuevaPalabra();
           }else {
               juegoFinalizado();
           }

       }

    }

    private boolean comprobar() {
        boolean correcto=true;
        if(sust.isChecked()&&!palabraAleatoria.isSustantivo()){
            correcto=false;
        }else if(!sust.isChecked()&&palabraAleatoria.isSustantivo()) {
            correcto=false;
        }else if(adj.isChecked()&&!palabraAleatoria.isAdjetivo()){
            correcto=false;

        }else if(!adj.isChecked()&&palabraAleatoria.isAdjetivo()) {
            correcto=false;
        }else if(adv.isChecked()&&!palabraAleatoria.isAdverbio()){
            correcto=false;

        }else if(!adv.isChecked()&&palabraAleatoria.isAdverbio()) {
            correcto=false;
        }else if(art.isChecked()&&!palabraAleatoria.isArticulo()){
            correcto=false;

        }else if(!art.isChecked()&&palabraAleatoria.isArticulo()) {
            correcto=false;
        }else if(verb.isChecked()&&!palabraAleatoria.isVerbo()){
            correcto=false;

        }else if(!verb.isChecked()&&palabraAleatoria.isVerbo()) {
            correcto=false;
        }else if(pre.isChecked()&&!palabraAleatoria.isPreposicion()){
            correcto=false;

        }else if(!pre.isChecked()&&palabraAleatoria.isPreposicion()) {
            correcto=false;
        }else if(pro.isChecked()&&!palabraAleatoria.isPronombre()){
            correcto=false;

        }else if(!pro.isChecked()&&palabraAleatoria.isPronombre()) {
            correcto=false;
        }else if(conj.isChecked()&&!palabraAleatoria.isConjuncion()){
            correcto=false;

        }else if(!conj.isChecked()&&palabraAleatoria.isConjuncion()) {
            correcto=false;
        }else if(inter.isChecked()&&!palabraAleatoria.isInterjeccion()){
            correcto=false;
        }else if(!inter.isChecked()&&palabraAleatoria.isInterjeccion()) {
            correcto=false;
        }


        return correcto;
    }

    private void mostrar(){
        letrasGanadas++;
        letras.setText(String.valueOf(letrasGanadas));
        if(getArguments().getInt("letras",-50)!=-50){
            letrasGanadas=getArguments().getInt("letras")+letrasGanadas;
            juegoFinalizado();
        }
    }

    private void nuevaPalabra() {
            posicion= (int) (Math.random()*allPalabras.size());
            palabraAleatoria=allPalabras.get(posicion);
            palabra.setText(palabraAleatoria.getPalabra());
    }

    private void juegoFinalizado(){

        if(SharedPreferentManager.getIntegerValue(Constantes.MEJOR_ESCALERA)<letrasGanadas)
            SharedPreferentManager.setIntegerValue(Constantes.MEJOR_ESCALERA,letrasGanadas);

        getActivity().onBackPressed();


    }
}