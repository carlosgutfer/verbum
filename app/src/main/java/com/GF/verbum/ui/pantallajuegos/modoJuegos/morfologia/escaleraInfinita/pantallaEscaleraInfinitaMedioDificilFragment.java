package com.GF.verbum.ui.pantallajuegos.modoJuegos.morfologia.escaleraInfinita;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.R;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.modosDeJuegoViewModel;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.RecordFragment;
import com.GF.verbum.ui.pantallajuegos.nuevaOportunidad.nuevaOportunidadDialogFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;


import java.util.ArrayList;
import java.util.List;

import static com.GF.verbum.commun.Constantes.reward;


public class pantallaEscaleraInfinitaMedioDificilFragment extends Fragment implements View.OnClickListener {


    private List<PalabrasEntity> allPalabras = new ArrayList<>();
    private PalabrasEntity palabraAleatoria;

    private modosDeJuegoViewModel mpalabrasviewModel;
    private TextView palabra;
    private View v;
    private int letrasGanadas=0;
    private int  posicion;
    private int dificultad;
    private boolean correcto;
    private TextView letras;
    private CheckBox sust, adj, pro, adv, verb, pre, conj, inter, art;
    private Button comprobar;
    private ProgressBar upProgressBar;
    private String nombre;
    private int mode;
    public  static RewardedAd mRewardedVideoAd;
    private int progresoBar=0;

    public static pantallaEscaleraInfinitaMedioDificilFragment newInstance(int dificultad) {
        pantallaEscaleraInfinitaMedioDificilFragment fragment = new pantallaEscaleraInfinitaMedioDificilFragment();
        Bundle args = new Bundle();
        args.putInt("dificultad", dificultad);
        fragment.setArguments(args);
        return fragment;
    }

    public static pantallaEscaleraInfinitaMedioDificilFragment newInstance(String palabra,int letras, int dificultad, int mode) {
        pantallaEscaleraInfinitaMedioDificilFragment fragment = new pantallaEscaleraInfinitaMedioDificilFragment();
        Bundle args = new Bundle();
        args.putString("palabra",palabra);
        args.putInt("letras", letras);
        args.putInt("dificultad",dificultad);
        args.putInt("mode",mode);
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
        loadVideoRewar();

        mpalabrasviewModel=new ViewModelProvider(this).get(modosDeJuegoViewModel.class);
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
                    mostrar();
                }
            }
        });

        return view;
    }

    private void buscarPalabra(List<PalabrasEntity> palabrasEntities) {
        this.dificultad=getArguments().getInt("dificultad");
        this.mode=getArguments().getInt("mode");
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
        upProgressBar=v.findViewById(R.id.PB_multiplicadorLetras);
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
        this.dificultad=getArguments().getInt("dificultad");

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
       if(view==R.id.BT_comprobar) {
           if (comprobar()) {
               mostrar();
               nuevaPalabra();
           } else {
               if (SharedPreferentManager.getIntegerValue(reward) == -1) {
                   nuevaOportunidad();
               } else {
                   correcto=false;
                   juegoFinalizado();
               }

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
        if(getArguments().getInt("letras",-50)!=-50){
            letrasGanadas=getArguments().getInt("letras");
            letras.setText(String.valueOf(letrasGanadas));
        }else {
            progreso();
            letras.setText(String.valueOf(letrasGanadas));
        }
    }

    private void nuevaPalabra() {
        if(nombre==null) {
            checkOut();
            posicion = (int) (Math.random() * allPalabras.size());
            palabraAleatoria = allPalabras.get(posicion);
            palabra.setText(palabraAleatoria.getPalabra());
        }else {
            correcto=true;
            progreso();
            juegoFinalizado();
        }
    }

    private void checkOut() {
        sust.setChecked(false);
        adj.setChecked(false);
        pro.setChecked(false);
        adv.setChecked(false);
        verb.setChecked(false);
        pre.setChecked(false);
        conj.setChecked(false);
        inter.setChecked(false);
        art.setChecked(false);
    }

    private void juegoFinalizado(){
        SharedPreferentManager.setIntegerValue(reward,-1);
        /*if(mInterstitialad.isLoaded())
            mInterstitialad.show();*/
            if(nombre==null)
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerJuegos, RecordFragment.newInstance(correcto,letrasGanadas, palabraAleatoria.getUrlRae(), palabraAleatoria.getPalabra(), 3, dificultad))
                        .commit();
            else
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerJuegos, RecordFragment.newInstance(correcto,letrasGanadas, palabraAleatoria.getUrlRae(), palabraAleatoria.getPalabra(),mode, dificultad))
                        .commit();





    }
    private void loadVideoRewar() {
            final FullScreenContentCallback fullScreenContentCallback =
                    new FullScreenContentCallback() {
                        @Override
                        public void onAdShowedFullScreenContent() {
                            // Code to be invoked when the ad showed full screen content.
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            mRewardedVideoAd = null;
                            // Code to be invoked when the ad dismissed full screen content.
                        }
                    };

            RewardedAd.load(
                    getActivity(),
                    "adUnitId",
                    new AdRequest.Builder().build(),
                    new RewardedAdLoadCallback() {
                        @Override
                        public void onAdLoaded(RewardedAd ad) {
                            mRewardedVideoAd = ad;
                            mRewardedVideoAd.setFullScreenContentCallback(fullScreenContentCallback);
                        }
                    });
        }



    private void nuevaOportunidad() {
        nuevaOportunidadDialogFragment dialog = nuevaOportunidadDialogFragment.newInstance(palabraAleatoria.getPalabra(),getActivity());
        dialog.setTargetFragment(this, 1);
        dialog.show(requireActivity().getSupportFragmentManager(), "Fragment");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==1){
            if(resultCode==1) {
                correcto=false;
                juegoFinalizado();
            }
        }
    }
    public void progreso(){
        progresoBar++;
        if(progresoBar<3)
            letrasGanadas=letrasGanadas+10;
        else if(progresoBar>3&&progresoBar<7)
            letrasGanadas = letrasGanadas + 20;
        else if(progresoBar>5&&progresoBar<10)
            letrasGanadas = letrasGanadas + 30;
        else
            letrasGanadas = letrasGanadas + 50;

        if(progresoBar<11)
            upProgressBar.setProgress(progresoBar*10);
    }
}