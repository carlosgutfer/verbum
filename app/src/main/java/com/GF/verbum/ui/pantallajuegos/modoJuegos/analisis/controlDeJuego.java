package com.GF.verbum.ui.pantallajuegos.modoJuegos.analisis;

import android.view.View;

import java.util.ArrayList;

public class controlDeJuego {
    private View v;
    private ArrayList<String> tipos = new ArrayList<>();
    private int ronda;

    public controlDeJuego(View v, ArrayList<String> tipos) {
        this.v = v;
        this.tipos = tipos;
    }

    public boolean checkTipo(String s){
        boolean valido = false;
            for (int i=0;i<tipos.size();i++){
                if( tipos.get(i).equals(s)){
                    valido =true;
                    break;
                }
            }
        return valido;
    }

}
