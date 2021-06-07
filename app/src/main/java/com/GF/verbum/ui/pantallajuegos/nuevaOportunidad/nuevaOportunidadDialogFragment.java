package com.GF.verbum.ui.pantallajuegos.nuevaOportunidad;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.GF.verbum.R;

import com.GF.verbum.ui.pantallajuegos.modoJuegos.morfologia.escaleraInfinita.PantallaEscaleraInfinitaFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.morfologia.escaleraInfinita.pantallaEscaleraInfinitaMedioDificilFragment;
import com.GF.verbum.ui.pantallajuegos.modoJuegos.morfologia.escaleraInfinita.pantallaEscaleraInfinitaPantallaPequeña;


public class nuevaOportunidadDialogFragment extends DialogFragment {
    private String nombre;
    private TextView name;
    private Button off;
    private ImageButton showVideo;
    private Activity actividad;
    private int mood=1;

    public static nuevaOportunidadDialogFragment newInstance(String nombre) {

        Bundle args = new Bundle();
        nuevaOportunidadDialogFragment fragment = new nuevaOportunidadDialogFragment();
        args.putString("nombre",nombre);
        fragment.setArguments(args);
        return fragment;
    }

    public static nuevaOportunidadDialogFragment newInstance() {
        nuevaOportunidadDialogFragment fragment = new nuevaOportunidadDialogFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_nueva_oportunidad, container, false);
        findviewbyId(view);
        onClick();
       try {
           nombre=getArguments().getString("nombre");
           name.setText("Vaya, parece que has fallado en la palabra "+ (char)34+nombre+(char)34+ ". Puedes ver el siguiente video para tener una vida extra.");
       }catch (Exception e){
           name.setText("Vaya, parece que has fallado. Puedes ver el siguiente video para tener una vida extra.");
       }
        return view;
    }

    private void onClick() {
        off.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
               dismiss();
            }
        });
        showVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( PantallaEscaleraInfinitaFragment.mRewardedVideoAd!=null){
                    PantallaEscaleraInfinitaFragment.mRewardedVideoAd.show();
                }
                else if(pantallaEscaleraInfinitaPantallaPequeña.mRewardedVideoAd!=null){
                    pantallaEscaleraInfinitaPantallaPequeña.mRewardedVideoAd.show();
                }else if(pantallaEscaleraInfinitaMedioDificilFragment.mRewardedVideoAd!=null){
                    pantallaEscaleraInfinitaMedioDificilFragment.mRewardedVideoAd.show();
                }
                mood=2;
                dismiss();

            }
        });
    }

    private void findviewbyId(View v) {
        off=v.findViewById(R.id.BT_continue);
        showVideo=v.findViewById(R.id.BT_showVideo);
        name=v.findViewById(R.id.TV_name);


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad=(Activity) context;
        }else{
            throw new RuntimeException(context.toString()+ "must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        getTargetFragment().onActivityResult(getTargetRequestCode(), mood, getActivity().getIntent());
        dismiss();
    }
}
