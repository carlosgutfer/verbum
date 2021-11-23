package com.GF.verbum.commun;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

public class MyApp extends MultiDexApplication {

    private static MyApp instance;



    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        instance=this;
        super.onCreate();
    }
}
