package com.GF.verbum.ui.pantallajuegos.modoJuegos.analisis;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.frasesEntity;
import com.GF.verbum.DB.Entities.fratipEntity;
import com.GF.verbum.DB.Entities.palfraEntity;
import com.GF.verbum.DB.Entities.tiposEntity;
import com.GF.verbum.R;
import com.GF.verbum.commun.SharedPreferentManager;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.RecordFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.modosDeJuegoViewModel;
import com.GF.verbum.ui.pantallajuegos.nuevaOportunidad.nuevaOportunidadDialogFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.List;

import static com.GF.verbum.commun.Constantes.reward;


public class analisFragment extends Fragment implements View.OnClickListener, RewardedVideoAdListener {

    private static final String ARG_PARAM1 = "param1";
    //elementos layout
    private TextView TV_Frase, TV_oraciónTipo;
    private Button BT_op1, BT_op2, BT_op3,BT_op4, BT_op5,BT_op6;

    //variables
    private int idFrase;
    private analisisViewModel anaviewModel;
    private modosDeJuegoViewModel modoviewModel;
    private ArrayList<palfraEntity> palfra = new ArrayList<>();
    private ArrayList<tiposEntity> tiposFrase = new ArrayList<>();
    private frasesEntity fraseFinal;
    private String stFrase ="";
    private controlDeJuego newControl;
    public  static RewardedVideoAd mRewardedVideoAd;


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
        loadVideoRewar();
        if (getArguments() != null) {
             int dificultad = getArguments().getInt(ARG_PARAM1);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View  v =  inflater.inflate(R.layout.fragment_analis, container, false);
        findByID(v);
        onClick();


        modoviewModel = new ViewModelProvider(this).get(modosDeJuegoViewModel.class);
        modoviewModel.getAllFrases().observe(getActivity(), new Observer<List<frasesEntity>>() {
            @Override
            public void onChanged(List<frasesEntity> frasesEntities) {
               fraseFinal = getFrase(frasesEntities);

               anaviewModel = new ViewModelProvider(getActivity()).get(analisisViewModel.class);
               anaviewModel.getAllPalfra(idFrase).observe(getActivity(), new Observer<List<palfraEntity>>() {
                    @Override
                    public void onChanged(List<palfraEntity> palfraEntities) {

                        palfra = setPalFra(palfraEntities);
                        getStringPal();
                    }
               });
               // Llamada a la tabla fratip para tomar todos los registros que tengan el id proporcionado
                anaviewModel.getTipFra(idFrase).observe(getActivity(), new Observer<List<fratipEntity>>() {
                    @Override
                    public void onChanged(List<fratipEntity> fratip) {
                        getTipos(fratip);
                        newControl = new controlDeJuego(getActivity(),BT_op1,BT_op2,tiposFrase);
                    }
                });

            }
        });


        return v;
    }



    public void setText(ArrayList<String> s) {
        Invisible();
        BT_op1.setText(s.get(0));
            BT_op2.setText(s.get(1));
            if(s.size()>=3) {
                BT_op3.setText(s.get(2));
                BT_op3.setVisibility(View.VISIBLE);
                if(s.size() >=5){
                    BT_op4.setText(s.get(3));
                    BT_op4.setVisibility(View.VISIBLE);
                    BT_op5.setText(s.get(4));
                    BT_op5.setVisibility(View.VISIBLE);
                    if(s.size()>=6){
                        BT_op6.setText(s.get(5));
                        BT_op6.setVisibility(View.VISIBLE);
                    }
                }

            }
    }

    // añado al arrayList de tipos, los registros que pertenecen a la frase
    private void getTipos(List<fratipEntity> allfratip) {
        for(int i=0; i<allfratip.size();i++) {
            anaviewModel.getTipo(allfratip.get(i).getIdTipo()).observe(this, new Observer<List<tiposEntity>>() {
                @Override
                public void onChanged(List<tiposEntity> tiposEntities) {
                    for (tiposEntity newAdd: tiposEntities){
                        tiposFrase.add(newAdd);
                    }
                }
            });
        }
    }

        // Cojo una frase
    private frasesEntity getFrase(List<frasesEntity> frase) {
        int random = (int) (Math.random()*frase.size());
        this.idFrase = frase.get(random).getIdFrase();
        fraseFinal = frase.get(random);
        return fraseFinal;
    }

    // tomo de la tabla palBra todos los resultados que tengan ID de la frase aleatoria
    private ArrayList<palfraEntity> setPalFra(List<palfraEntity> palfraEntities) {
        for(int i=0;i<palfraEntities.size();i++){
            this.palfra.add(palfraEntities.get(i));
        }
        return palfra;
    }
    //Del arrayList palfra tomo el idPalabra de cada registro y en la tabla palabra tomo el string que corresponde a ese ID
    private void getStringPal() {
        for(int i =0;i<palfra.size();i++){
            modoviewModel.getPalFrases(palfra.get(i).getIdPalabra()).observe(getActivity(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                 addString(s);
                 TV_Frase.setText(stFrase);
                }
            });
        }
    }

    private void addString(String s) {
        this.stFrase += s + " ";
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.BT_op1) {
            if (!newControl.checkTipo(BT_op1.getText().toString())) {
                if (SharedPreferentManager.getIntegerValue(reward) == -1)
                    nuevaOportunidad();
            }else {
                sonTips();
            }
        }

        if (id == R.id.BT_op2) {
            if (!newControl.checkTipo(BT_op2.getText().toString())) {
                if (SharedPreferentManager.getIntegerValue(reward) == -1)
                    nuevaOportunidad();
            }else{
                sonTips();
            }
        }
        if (id == R.id.BT_op3) {
            if (!newControl.checkTipo(BT_op3.getText().toString()))
                if (SharedPreferentManager.getIntegerValue(reward) == -1)
                    nuevaOportunidad();
        }
        if (id == R.id.BT_op4) {
            if (!newControl.checkTipo(BT_op4.getText().toString()))
                if (SharedPreferentManager.getIntegerValue(reward) == -1)
                    nuevaOportunidad();


        }
        if (id == R.id.BT_op5) {
            if (!newControl.checkTipo(BT_op5.getText().toString()))
                if (SharedPreferentManager.getIntegerValue(reward) == -1)
                    nuevaOportunidad();
        }
        if (id == R.id.BT_op6) {
            if (!newControl.checkTipo(BT_op6.getText().toString()))
                if (SharedPreferentManager.getIntegerValue(reward) == -1)
                    nuevaOportunidad();
        }


    }

    private boolean checkEndGame() {
        boolean endGame = false;

        return endGame;
    }

    // cambia el fragment por el de la pantalla de fin
    private void juegoFinalizado( boolean correcto) {
        SharedPreferentManager.setIntegerValue(reward,-1);

        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerJuegos, RecordFragment.newInstance(4,correcto))
                .commit();

    }
    //  cargar el anuncio por si el jugador falla poder mostrarlo
    private void loadVideoRewar() {
        MobileAds.initialize(getActivity(), "ca-app-pub-9592543293433576/6730215293");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        mRewardedVideoAd.loadAd("ca-app-pub-9592543293433576/6730215293", new AdRequest.Builder().build());
    }
    // instancia el dialogFragment de nueva oportunidad
    private void nuevaOportunidad() {
        nuevaOportunidadDialogFragment dialog = nuevaOportunidadDialogFragment.newInstance();
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
                juegoFinalizado(false);
            }
        }
    }
    private void onClick() {
        BT_op1.setOnClickListener(this);
        BT_op2.setOnClickListener(this);
        BT_op3.setOnClickListener(this);
        BT_op4.setOnClickListener(this);
        BT_op5.setOnClickListener(this);
        BT_op6.setOnClickListener(this);

    }
    private void findByID(View v) {
        TV_Frase = v.findViewById(R.id.TV_frasPal);
        TV_oraciónTipo = v.findViewById(R.id.tv_tipoOracion);
        BT_op1 = v.findViewById(R.id.BT_op1);
        BT_op2 = v.findViewById(R.id.BT_op2);
        BT_op3 = v.findViewById(R.id.BT_op3);
        BT_op4 = v.findViewById(R.id.BT_op4);
        BT_op5 = v.findViewById(R.id.BT_op5);
        BT_op6 = v.findViewById(R.id.BT_op6);
        Invisible();
    }
    private  void Invisible(){
        BT_op3.setVisibility(View.INVISIBLE);
        BT_op4.setVisibility(View.INVISIBLE);
        BT_op5.setVisibility(View.INVISIBLE);
        BT_op6.setVisibility(View.INVISIBLE);
    }

    // Tengo que llamar tomar el objetoActual de la clase control, y meterlo aqui. Hacer llamada al observer y dentor de ella llamar a los metodos de contros de juego 1º y después mandar el texto
    private void sonTips(){
        if (newControl.tiposFrase.size()==0)
            juegoFinalizado(true);
        anaviewModel.getSonTip(newControl.actual.getIdTipo()).observe(getActivity(), new Observer<List<tiposEntity>>() {
            @Override
            public void onChanged(List<tiposEntity> tiposEntities) {
                ArrayList<tiposEntity> sonTip = new ArrayList<>();
                sonTip.addAll(tiposEntities);
                setText(newControl.flujoDelJuego(sonTip));
                TV_oraciónTipo.setText(TV_oraciónTipo.getText()+" "+newControl.actual.getDescriptcion());

            }
        });
    }
}