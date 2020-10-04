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
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.ModosJuegosViewModel;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.RecordFragment;
import com.GF.verbum.ui.pantallajuegos.nuevaOportunidad.nuevaOportunidadDialogFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.List;

import static com.GF.verbum.commun.Constantes.reward;


public class pantallaEscaleraInfinitaMedioDificilFragment extends Fragment implements View.OnClickListener, RewardedVideoAdListener {


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
    private int mode;
    private InterstitialAd mInterstitialad;
    public  static RewardedVideoAd mRewardedVideoAd;

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
        mInterstitialad = new InterstitialAd(getActivity());
        mInterstitialad.setAdUnitId("ca-app-pub-9592543293433576/3091063629");
        mInterstitialad.loadAd(new AdRequest.Builder().build());
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
            letrasGanadas++;
            letras.setText(String.valueOf(letrasGanadas));
        }
    }

    private void nuevaPalabra() {
        if(nombre==null) {
            posicion = (int) (Math.random() * allPalabras.size());
            palabraAleatoria = allPalabras.get(posicion);
            palabra.setText(palabraAleatoria.getPalabra());
        }else {
            letrasGanadas++;
            juegoFinalizado();
        }
    }

    private void juegoFinalizado(){
        SharedPreferentManager.setIntegerValue(reward,-1);

        if(letrasGanadas==0){
            getActivity().onBackPressed();
        }else {

            if(nombre==null) {
                if(mInterstitialad.isLoaded())
                    mInterstitialad.show();
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerJuegos, RecordFragment.newInstance(letrasGanadas, palabraAleatoria.getUrlRae(), palabraAleatoria.getPalabra(), 3, dificultad))
                        .commit();
            }else{
                if(mInterstitialad.isLoaded())
                    mInterstitialad.show();
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerJuegos, RecordFragment.newInstance(letrasGanadas, palabraAleatoria.getUrlRae(), palabraAleatoria.getPalabra(),mode, dificultad))
                        .commit();
            }

        }


    }
    private void loadVideoRewar() {
        MobileAds.initialize(getActivity(), "ca-app-pub-9592543293433576/6730215293");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        mRewardedVideoAd.loadAd("ca-app-pub-9592543293433576/6730215293", new AdRequest.Builder().build());
    }

    private void nuevaOportunidad() {
        nuevaOportunidadDialogFragment dialog = nuevaOportunidadDialogFragment.newInstance(palabraAleatoria.getPalabra());
        dialog.setTargetFragment(this, 1);
        dialog.show(requireActivity().getSupportFragmentManager(), "Fragment");
    }
    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        SharedPreferentManager.setIntegerValue(reward,rewardItem.getAmount());
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        SharedPreferentManager.setIntegerValue(reward,1);
    }


    @Override
    public void onRewardedVideoCompleted() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==1){
            if(resultCode==1) {
                juegoFinalizado();
            }
        }
    }
}