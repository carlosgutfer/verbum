package com.GF.verbum.ui.pantallajuegos.modoJuegos.analisis;

import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.GF.verbum.DB.Entities.tiposEntity;
import com.GF.verbum.R;

import java.util.ArrayList;
import java.util.List;

public class controlDeJuego {
    public ArrayList<tiposEntity> tiposFrase;
    public String fraseFinal = "O.";
    private analisisViewModel anaViewModel;
    private ArrayList<tiposEntity> allTipos = new ArrayList<>();
    private ArrayList<String> stFlujoJuego = new ArrayList<>();
    public tiposEntity actual;

    public controlDeJuego(FragmentActivity activity, final Button op1, final Button op2, ArrayList<tiposEntity> tipos) {
        this.tiposFrase = tipos;
        anaViewModel = new ViewModelProvider(activity).get(analisisViewModel.class);
        anaViewModel.getAllTipos().observe(activity, new Observer<List<tiposEntity>>() {
            @Override
            public void onChanged(List<tiposEntity> tiposEntities) {
                getTipos(tiposEntities);
                op1.setText(allTipos.get(0).getDescriptcion());
                op2.setText(allTipos.get(25).getDescriptcion());

            }
        });
    }

    private void getTipos(List<tiposEntity> tiposEntities) {
        allTipos.addAll(tiposEntities);
    }

    public boolean checkTipo(String s){
        boolean valido = false;
            for (int i=0;i<tiposFrase.size();i++){
                 actual = tiposFrase.get(i);
                if( actual.getDescriptcion().equals(s)){
                    fraseFinal+=actual.getDescriptcion()+" ";
                    tiposFrase.remove(i);
                    valido = true;
                    break;
                }
            }
        return valido;
    }

    public ArrayList<String> flujoDelJuego(ArrayList<tiposEntity> sonTips) {
            stFlujoJuego.clear();

        if (!sonTips.isEmpty()){
            for (tiposEntity t: sonTips){
                stFlujoJuego.add(t.getDescriptcion());
            }
        }else {
            stFlujoJuego.add(allTipos.get(10).getDescriptcion());
            stFlujoJuego.add(allTipos.get(13).getDescriptcion());
            stFlujoJuego.add(allTipos.get(20).getDescriptcion());
            stFlujoJuego.add(allTipos.get(21).getDescriptcion());
            stFlujoJuego.add(allTipos.get(22).getDescriptcion());
            stFlujoJuego.add(allTipos.get(23).getDescriptcion());
        }
        return stFlujoJuego;
    }


}
