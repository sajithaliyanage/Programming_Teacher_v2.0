package com.example.pushpika.canvas_game;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;


public class CheckLogged {
    Context context;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    CheckLogged(Context context){
        this.context = context;
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public boolean getStatus(){
        boolean logged = sharedpreferences.getBoolean("nowStatus", false);
        return logged;
    }

    public void setLogged(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("nowStatus", true);
        editor.commit();
    }

    public void changeStatus(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("nowStatus", false);
        editor.commit();
    }
}
