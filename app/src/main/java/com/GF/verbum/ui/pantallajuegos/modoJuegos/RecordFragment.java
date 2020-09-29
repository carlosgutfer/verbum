package com.GF.verbum.ui.pantallajuegos.modoJuegos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.GF.verbum.R;
import com.GF.verbum.commun.user;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;


public class RecordFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "letras";
    private static final String ARG_PARAM2 = "urlPalabra";
    private static final String ARG_PARAM3 = "palabra";
    private static final String ARG_PARAM4 = "mode";
    private static final String ARG_PARAM5 = "dificultad";



    private InterstitialAd mInterstitialad;
    private TextView letrasYPalabra, TVdiccionario;
    private EditText subirPuntuacion;

    private String urlPalabra;
    private int letras;
    private int mode;
    private String Palabra;
    private ImageView diccionario,cloud;
    private View v;
    private int dificultad;
    private FirebaseFirestore db;
    private String nick;

    public RecordFragment() {
        // Required empty public constructor
    }


    public static RecordFragment newInstance(int letras,String urlPalabra,String Palabra,int mode,int dificultad) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, letras);
        args.putString(ARG_PARAM2, urlPalabra);
        args.putString(ARG_PARAM3,Palabra);
        args.putInt(ARG_PARAM4, mode);
        args.putInt(ARG_PARAM5, dificultad);
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       this.v= inflater.inflate(R.layout.fragment_record, container, false);



        findViewById(v);
        getArgumentsMethod();
        diccionario.setOnClickListener(this);
        cloud.setOnClickListener(this);
        setText(mode);
        db=FirebaseFirestore.getInstance();
        return v;
    }

    private void getArgumentsMethod() {
        this.mode=getArguments().getInt(ARG_PARAM4);
        this.dificultad=getArguments().getInt(ARG_PARAM5);

        if(mode==1){
            this.letras=getArguments().getInt(ARG_PARAM1);
            diccionario.setVisibility(View.INVISIBLE);
            TVdiccionario.setVisibility(View.INVISIBLE);
        }else{
            this.urlPalabra=getArguments().getString(ARG_PARAM2);
            this.Palabra=getArguments().getString(ARG_PARAM3);
            this.letras=getArguments().getInt(ARG_PARAM1);
        }
    }

    private void setText(int mode) {
    if(mode==1){
        letrasYPalabra.setText("¡Felicidades! Has conseguido " + letras + " letras.");
    }else {
    letrasYPalabra.setText("¡Felicidades! Has conseguido " + letras + " letras y la última palabra era " + Palabra + ". " +
            "Si quieres revisar que funciones puede tener, pulsa sobre el diccionario.");
    }
    }



    private void findViewById(View v) {
        letrasYPalabra=v.findViewById(R.id.TV_PalabraYgemas);
        diccionario=v.findViewById(R.id.IV_URLRAE);
        TVdiccionario=v.findViewById(R.id.TV_diccionario);
        cloud=v.findViewById(R.id.IV_SubirPuntuacion);
        subirPuntuacion=v.findViewById(R.id.ET_Nick);
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
            if(nick.length()>=4){
                    db.collection("users").whereEqualTo("name",nick)
                           .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    if(queryDocumentSnapshots.size()>0){
                                        subirPuntuacion.setError("El nick no esta disponible");
                                    }else{
                                        addNewLogin();
                                    }
                                }
                            });
            }else{
                subirPuntuacion.setError("El nick debe ser al menos de 4 caracteres");
            }
        }
    }

    private void addNewLogin() {
        db.collection("users")
                .add(new user(nick,letras,mode,dificultad))
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        getActivity().onBackPressed();
                        Toast.makeText(getActivity(),"Puntuación subida",Toast.LENGTH_SHORT).show();
                    }
                });

    }
}