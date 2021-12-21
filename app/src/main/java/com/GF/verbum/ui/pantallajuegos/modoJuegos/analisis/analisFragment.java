package com.GF.verbum.ui.pantallajuegos.modoJuegos.analisis;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.GF.verbum.DB.Entities.frasesEntity;
import com.GF.verbum.DB.Entities.fratipEntity;
import com.GF.verbum.DB.Entities.tiposEntity;
import com.GF.verbum.R;
import com.GF.verbum.commun.SharedPreferentManager;
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


public class analisFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    //elementos layout
    private TextView TV_Frase, TV_oracionTipo, TV_pre1, TV_pre2;
    private Button BT_op1, BT_op2, BT_op3,BT_op4, BT_op5,BT_op6;

    //variables
    private int idFrase;
    private analisisViewModel anaviewModel;
    private ArrayList<tiposEntity> tiposFrase = new ArrayList<>();
    private ArrayList<tiposEntity> pre1Frase = new ArrayList<>();
    private ArrayList<tiposEntity> pre2Frase = new ArrayList<>();

    private frasesEntity fraseFinal;
    private String stFrase ="";
    private controlDeJuego newControl;
    public  static RewardedAd mRewardedVideoAd;
    private boolean compuesta;


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
        final View  v =  inflater.inflate(R.layout.fragment_analisis, container, false);
        findByID(v);
        onClick();

        anaviewModel = new ViewModelProvider(getActivity()).get(analisisViewModel.class);
        anaviewModel.getAllFrases().observe(getActivity(), new Observer<List<frasesEntity>>() {
            @Override
            public void onChanged(List<frasesEntity> frasesEntities) {
               getFrase(frasesEntities);
               getStringPal();
               // Llamada a la tabla fratip para tomar todos los registros que tengan el id proporcionado
                anaviewModel.getTipFra(idFrase).observe(getActivity(), new Observer<List<fratipEntity>>() {
                    @Override
                    public void onChanged(List<fratipEntity> fratip) {
                        getTipos(fratip);
                        newControl = new controlDeJuego(getActivity(),BT_op1,BT_op2,tiposFrase,compuesta);
                    }
                });

            }
        });
        return v;
    }


    public void setText(@NonNull ArrayList<String> s) {
        Invisible();
        BT_op1.setText(s.get(0));

        if(s.size()==1)
            BT_op2.setText("pasiva");
        else
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
    private void getTipos(@NonNull List<fratipEntity> allfratip) {

            anaviewModel.getTipo(idFrase).observe(this, new Observer<List<tiposEntity>>() {
                @Override
                public void onChanged(List<tiposEntity> tiposEntities) {
                    boolean pre1 = false;
                    boolean pre2 = false;
                        for (tiposEntity newAdd : tiposEntities) {
                            if(compuesta) {
                                    if (!pre1 && !pre2) {
                                        if (newAdd.getIdTipo() != 1)
                                            tiposFrase.add(newAdd);
                                        else {
                                            pre1 = true;
                                        }
                                    } else if (pre1) {
                                        if (newAdd.getIdTipo() != 1)
                                            pre1Frase.add(newAdd);
                                        else {
                                            pre1 = false;
                                            pre2 = true;
                                        }
                                    } else if (pre2)
                                        pre2Frase.add(newAdd);

                                }else{
                                tiposFrase.add(newAdd);
                            }

                            if (newAdd.getIdTipo() == 14)
                                TV_Frase.setText("¿" + TV_Frase.getText() + "?");
                            else if (newAdd.getIdTipo() == 21)
                                TV_Frase.setText("¡" + TV_Frase.getText() + "!");
                        }


                }
            });


    }

        // Cojo una frase
    private void getFrase(@NonNull List<frasesEntity> frase) {
        int random = (int) (Math.random()*frase.size());
        this.fraseFinal = frase.get(random);
        this.idFrase = fraseFinal.getIdFrase();
        this.compuesta = fraseFinal.isoCompuesta();

    }

    //Del arrayList palfra tomo el idPalabra de cada registro y en la tabla palabra tomo el string que corresponde a ese ID
    private void getStringPal() {

            anaviewModel.getPalFrases(idFrase).observe(getActivity(), new Observer<List<String>>() {
                @Override
                public void onChanged(List<String> strings) {
                    ArrayList<String> frase = new ArrayList<>();
                    frase.addAll(strings);
                    addString(frase);
                    TV_Frase.setText(stFrase);
                }
            });
        }


    private void addString(@NonNull ArrayList<String> s) {

        for (int i =0;i<s.size();i++) {
            this.stFrase += s.get(i) + " ";
        }
       stFrase =  stFrase.substring(0,1).toUpperCase()+ stFrase.substring(1);

    }


    @Override
    public void onClick(@NonNull View v) {
        int id = v.getId();

        if (id == R.id.BT_op1) {
            if (!newControl.checkTipo(BT_op1.getText().toString())) {
                if (SharedPreferentManager.getIntegerValue(reward) == -1)
                    nuevaOportunidad();
                else
                    juegoFinalizado(false);
            }else {
                sonTips();
            }
        }

        if (id == R.id.BT_op2) {
            if (!newControl.checkTipo(BT_op2.getText().toString())) {
                if (SharedPreferentManager.getIntegerValue(reward) == -1)
                    nuevaOportunidad();
                else
                    juegoFinalizado(false);
            }else{
                sonTips();
            }
        }
        if (id == R.id.BT_op3) {
            if (!newControl.checkTipo(BT_op3.getText().toString())) {
                if (SharedPreferentManager.getIntegerValue(reward) == -1)
                    nuevaOportunidad();
            }else {
                sonTips();
            }
        }
        if (id == R.id.BT_op4) {
            if (!newControl.checkTipo(BT_op4.getText().toString())) {
                if (SharedPreferentManager.getIntegerValue(reward) == -1)
                    nuevaOportunidad();
            }else {
                sonTips();
            }

        }
        if (id == R.id.BT_op5) {
            if (!newControl.checkTipo(BT_op5.getText().toString())) {
                if (SharedPreferentManager.getIntegerValue(reward) == -1)
                    nuevaOportunidad();
            }else {
                sonTips();
            }
        }
        if (id == R.id.BT_op6) {
            if (!newControl.checkTipo(BT_op6.getText().toString())) {
                if (SharedPreferentManager.getIntegerValue(reward) == -1)
                    nuevaOportunidad();
            }else {
                sonTips();
            }
        }


    }



    // cambia el fragment por el de la pantalla de fin
    private void juegoFinalizado( boolean correcto) {
        SharedPreferentManager.setIntegerValue(reward,-1);
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerJuegos, RecordFragment.newInstance(4,correcto,newControl.fraseFinal))
                .commit();
    }
    //  cargar el anuncio por si el jugador falla poder mostrarlo
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
        RewardedAd.load(getActivity(), "ca-app-pub-9592543293433576/6730215293", new AdRequest.Builder().build(), new RewardedAdLoadCallback() {
            @Override
            public void onAdLoaded(RewardedAd ad) {
                mRewardedVideoAd = ad;
                mRewardedVideoAd.setFullScreenContentCallback(fullScreenContentCallback);
            }
        });
        }

    // instancia el dialogFragment de nueva oportunidad
    private void nuevaOportunidad() {
        nuevaOportunidadDialogFragment dialog = nuevaOportunidadDialogFragment.newInstance(getActivity());
        dialog.setTargetFragment(this, 1);
        dialog.show(requireActivity().getSupportFragmentManager(), "Fragment");
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
        TV_oracionTipo = v.findViewById(R.id.tv_tipoOracion);
        TV_pre1 =v.findViewById(R.id.tv_pre1);
        TV_pre2 = v.findViewById(R.id.tv_pre2);
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

        int idTipo = newControl.actual.getIdTipo();

        if (newControl.tiposFrase.size()==0&&!compuesta)
            juegoFinalizado(true);
        else {
            if(newControl.tiposFrase.size()==0&&!pre1Frase.isEmpty()) {
                newControl.tiposFrase.addAll(pre1Frase);
                pre1Frase.clear();
                TV_oracionTipo.setText(newControl.fraseFinal);
                TV_oracionTipo=TV_pre1;
                TV_oracionTipo.setVisibility(View.VISIBLE);
                newControl.fraseFinal = "";

            }else if(newControl.tiposFrase.size()==0&&!pre2Frase.isEmpty()) {
                newControl.tiposFrase.addAll(pre2Frase);
                compuesta = false;
                TV_oracionTipo.setText(newControl.fraseFinal);
                TV_oracionTipo=TV_pre2;
                TV_oracionTipo.setVisibility(View.VISIBLE);
                newControl.fraseFinal = "";
            }

            if(newControl.tiposFrase.size()==2&&idTipo==4)
                idTipo = 9;

            anaviewModel.getSonTip(idTipo).observe(getActivity(), tiposEntities -> {
                ArrayList<tiposEntity> sonTip = new ArrayList<>();
                sonTip.addAll(tiposEntities);
                setText(newControl.flujoDelJuego(sonTip));
                TV_oracionTipo.setText(newControl.fraseFinal);

            });
        }
    }

}