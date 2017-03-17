package com.example.pushpika.canvas_game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;

public class MainActivity extends BaseGameActivity implements View.OnClickListener {

    public static int position_width=0,position_height=0,cur_position=1,is_increment=-1,current_pos=1,target_pos=1;
    public static String target_class="P"; // A for
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
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

        //check the internet connection
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            beginUserInitiatedSignIn();
        } else{
            Toast.makeText(MainActivity.this, "Check your internet connection!", Toast.LENGTH_LONG).show();
        }


        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.show_leaderboard).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.sign_in_button) {
//            beginUserInitiatedSignIn();
//        }
        // else
        if (view.getId() == R.id.sign_out_button) {
            signOut();
//            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);

        }
        if(view.getId() == R.id.show_leaderboard){
            Log.v("Click","Leaderboard");
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(getApiClient(), getString(R.string.number_guesses_leaderboard)), 2);
        }
    }
//
    public void onSignInSucceeded() {
//        findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
    }

    @Override
    public void onSignInFailed() {
//        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
        findViewById(R.id.sign_out_button).setVisibility(View.GONE);
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

