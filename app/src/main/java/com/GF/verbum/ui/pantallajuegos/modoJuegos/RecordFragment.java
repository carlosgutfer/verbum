package com.GF.verbum.ui.pantallajuegos.modoJuegos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.GF.verbum.R;
import com.GF.verbum.commun.user;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;



public class RecordFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "letras";
    private static final String ARG_PARAM2 = "urlPalabra";
    private static final String ARG_PARAM3 = "palabra";
    private static final String ARG_PARAM4 = "mode";
    private static final String ARG_PARAM5 = "dificultad";
    private static final String ARG_PARAM6 = "correcto";



    private TextView letrasYPalabra, TVdiccionario, TVtextoSubir;
    private EditText subirPuntuacion;

    private String urlPalabra;
    private int letras;
    private int mode;
    private String Palabra;
    private ImageView diccionario,cloud,back;
    private View v;
    private int dificultad;
    private FirebaseFirestore db;
    private String nick;
    private Boolean correcto;

    public RecordFragment() {
    }


    public static RecordFragment newInstance(Boolean correcto,int letras,String urlPalabra,String Palabra,int mode,int dificultad) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, letras);
        args.putString(ARG_PARAM2, urlPalabra);
        args.putString(ARG_PARAM3,Palabra);
        args.putInt(ARG_PARAM4, mode);
        args.putInt(ARG_PARAM5, dificultad);
        args.putBoolean(ARG_PARAM6,correcto);
        fragment.setArguments(args);
        return fragment;
    }


    public static RecordFragment newInstance(int letras,int mode,int dificultad) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, letras);
        args.putInt(ARG_PARAM4, mode);
        args.putInt(ARG_PARAM5, dificultad);
        fragment.setArguments(args);
        return fragment;
    }

    public static RecordFragment newInstance(int mode, Boolean correcto, String Palabra){
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM3,Palabra);
        args.putInt(ARG_PARAM4, mode);
        args.putBoolean(ARG_PARAM6,correcto);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       this.v= inflater.inflate(R.layout.fragment_record, container, false);



        findViewById(v);
        this.mode=getArguments().getInt(ARG_PARAM4);
        if (mode == 4) {
            mode4(v);
            setText(mode);
        }else {

            getArgumentsMethod();
            if (mode == 2) {
                subirPuntuacion.setVisibility(View.INVISIBLE);
                cloud.setVisibility(View.INVISIBLE);
                TVtextoSubir.setVisibility(View.INVISIBLE);

            }
            diccionario.setOnClickListener(this);
            cloud.setOnClickListener(this);
            back.setOnClickListener(this);
            setText(mode);
            db = FirebaseFirestore.getInstance();
        }
        return v;

    }

    private void mode4(View v) {
        this.correcto=getArguments().getBoolean(ARG_PARAM6);
        this.Palabra=getArguments().getString(ARG_PARAM3);

        subirPuntuacion.setVisibility(v.INVISIBLE);
        cloud.setVisibility(v.INVISIBLE);
        TVtextoSubir.setVisibility(v.INVISIBLE);
        diccionario.setVisibility(v.INVISIBLE);
        TVdiccionario.setVisibility(v.INVISIBLE);
        back.setOnClickListener(this);

    }


    private void getArgumentsMethod() {
        this.dificultad=getArguments().getInt(ARG_PARAM5);

        if(mode==1){
            this.letras=getArguments().getInt(ARG_PARAM1);
            diccionario.setVisibility(View.INVISIBLE);
            TVdiccionario.setVisibility(View.INVISIBLE);
        }else{
            this.urlPalabra=getArguments().getString(ARG_PARAM2);
            this.Palabra=getArguments().getString(ARG_PARAM3);
            this.letras=getArguments().getInt(ARG_PARAM1);
            this.correcto=getArguments().getBoolean(ARG_PARAM6);
        }
    }

    private void setText(int mode) {
        String texto1 = getResources().getString(R.string.recorduno);
        String texto3 = getResources().getString(R.string.recordtres);
        String frase ="";
    if(mode==1&&letras!=0) {
        String texto2 = getResources().getString(R.string.recordos);
        frase = texto1 + " " + letras + " " + texto2;
    }else if(mode==1){
        String texto7=getResources().getString(R.string.recordsiete);
        frase = texto7 + " " + letras;
    }else if (mode==3&&letras!=0) {
        String texto4 = getResources().getString(R.string.recordcuatro);
        frase = texto1 + " " + letras + " " + texto4 + " " + Palabra + " " + texto3;
    }else if(mode==3){
        String texto4 = getResources().getString(R.string.recordcuatro);
        String texto8 = getResources().getString(R.string.recordocho);
        frase = texto8+ " " + letras + " " + texto4 + " " + Palabra + " " + texto3;
    }else if (mode==2&&correcto) {
        String texto5=getResources().getString(R.string.recordcinco);
        frase= texto5+" "+Palabra+" "+texto3;
    }else if(mode ==2&&!correcto){
        String texto6=getResources().getString(R.string.recordseis);
        frase= texto6 +" "+Palabra+" "+texto3;
    }else if(mode == 4&&!correcto){
        frase = getResources().getString(R.string.falloAnalisis);
    }else if(mode == 4&&correcto){
        String texto7 = getResources().getString(R.string.aciertoAnalisis);
        String texto8 = getResources().getString(R.string.aciertoAnalisis2);
        frase = texto7 + " "+Palabra+ " " +texto8;
    }
        letrasYPalabra.setText(frase);

    }

    private void findViewById(View v) {
        letrasYPalabra=v.findViewById(R.id.TV_PalabraYgemas);
        diccionario=v.findViewById(R.id.IV_URLRAE);
        TVdiccionario=v.findViewById(R.id.TV_diccionario);
        cloud=v.findViewById(R.id.IV_SubirPuntuacion);
        subirPuntuacion=v.findViewById(R.id.ET_Nick);
        TVtextoSubir=v.findViewById(R.id.TV_textoSubir);
        back=v.findViewById(R.id.IV_Back);
    }

    @Override
    public void onClick(View v) {
        int view = v.getId();
        if(view==R.id.IV_URLRAE) {
            Uri uri = Uri.parse(urlPalabra);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        if(view==R.id.IV_SubirPuntuacion){
            nick=subirPuntuacion.getText().toString();
            if(nick.length()>=4&&nick.length()<18){
                    db.collection("users")
                           .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        addNewLogin();

                                }
                            });
            }else{
                subirPuntuacion.setError(getResources().getString(R.string.error));
            }
        }
        if(view==R.id.IV_Back){
            requireActivity().onBackPressed();
        }
    }

    private void addNewLogin() {
        db.collection("users")
                .add(new user(nick,letras,mode,dificultad))
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        requireActivity().onBackPressed();
                        Toast.makeText(getActivity(),getResources().getString(R.string.upload),Toast.LENGTH_SHORT).show();
                    }
                });

    }



}