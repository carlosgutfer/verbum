package com.GF.verbum.commun;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferentManager {

    private SharedPreferentManager() {

    }
    private static final String APP_SETTINGS_FILE="APP_SETTINGS";

    private static SharedPreferences getSharePreferences(){
        return MyApp.getContext()
                .getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE);
    }

    public static void  setIntegerValue(String dataLabel, Integer dataValue){
        SharedPreferences.Editor editor = getSharePreferences().edit();
        editor.putInt(dataLabel,dataValue);
        editor.commit();
    }

    public static Integer getIntegerValue(String dataLabel){
        return getSharePreferences().getInt(dataLabel, -1);
    }

}
