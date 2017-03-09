package com.example.pushpika.canvas_game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static int position_width=0,position_height=0,cur_position=1,is_increment=-1,current_pos=1,target_pos=1;
    public static String target_class="P"; // A for
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("prefs", 0);
        boolean firstRun = settings.getBoolean("firstRun", true);

        //set current class
        target_class = settings.getString("Current_class","P");
        current_pos = settings.getInt("Current_pos",1);
        target_pos = settings.getInt("Current_pos",1);
        cur_position=current_pos;

        Log.v("Data check","successfully");
        Log.v("happened current share",String.valueOf(settings.getString("Current_class","DefaultClass")));
        Log.v("happened current share",String.valueOf(settings.getInt("Current_pos",0)));
        if (firstRun) {
            // here run your first-time instructions, for example :
            SharedPreferences settings1 = getSharedPreferences("prefs", 0);
            SharedPreferences.Editor editor = settings1.edit();
            editor.putString("Current_class","P");
            editor.putInt("Current_pos",1);
            editor.commit();

            Intent intent = new Intent(this,database_data_entry_initial.class);
            startActivity(intent);
            finish();
        }

            SharedPreferences settings1 = getSharedPreferences("prefs", 0);
            SharedPreferences.Editor editor = settings1.edit();
            editor.putBoolean("firstRun", false);
            editor.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);

    }



    public void Go_Ques(View view){
        Intent transition_page = new Intent(this,NewQuestionView.class);
        startActivity(transition_page);
        finish();
    }

    public void Go_Help(View view){
        Intent intent = new Intent(this, HelpActivity.class);

        startActivity(intent);
        finish();
    }

    public void Go_About(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
        finish();
    }

    public void Go_Example(View view){
        Intent intent = new Intent(this, ExampleActivity.class);
        startActivity(intent);
        finish();
    }


}

