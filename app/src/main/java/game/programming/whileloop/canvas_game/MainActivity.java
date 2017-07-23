package game.programming.whileloop.canvas_game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;


import com.example.pushpika.canvas_game.R;
import com.google.android.gms.games.Games;

import game.google.example.games.basegameutils.BaseGameActivity;

public class MainActivity extends BaseGameActivity implements View.OnClickListener {

    public static int position_width=0,position_height=0,cur_position=1,is_increment=-1,current_pos=1,target_pos=1;
    public static String target_class="P"; // A for
    CheckLogged sessionLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        sessionLogin = new CheckLogged(this);

        SharedPreferences settings = getSharedPreferences("prefs", 0);
        boolean firstRun = settings.getBoolean("firstRun", true);

        //set current class
        target_class = settings.getString("Current_class","z1");
        current_pos = settings.getInt("Current_pos",1);
        target_pos = settings.getInt("Current_pos",1);
        cur_position=current_pos;

        Log.i("Data check","successfully");
        Log.i("happened current share",String.valueOf(settings.getString("Current_class","DefaultClass")));
        Log.i("happened current share",String.valueOf(settings.getInt("Current_pos",0)));

        if (firstRun) {
            // here run your first-time instructions, for programming :
            SharedPreferences settings1 = getSharedPreferences("prefs", 0);
            SharedPreferences.Editor editor = settings1.edit();
            editor.putString("Current_class", "z1");
            editor.putInt("Current_pos", 1);
            editor.commit();

            //initialize database in the 1st run
            Intent intent = new Intent(this, database_data_entry_initial.class);
            startActivity(intent);
            finish();
        }else{
            //not the first run, check for database updates WITHOUT force downloading
            FirebaseData fd = new FirebaseData(this);
            fd.syncQuestions(new CallBack() {
                @Override
                public void onComplete() {}

                @Override
                public void onError() {}
            },false);
        }


        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.show_leaderboard).setOnClickListener(this);
        findViewById(R.id.show_achievements).setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_button) {

            beginUserInitiatedSignIn();

        } else if (view.getId() == R.id.sign_out_button) {
            signOut();
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);
            findViewById(R.id.show_leaderboard).setVisibility(View.GONE);
            findViewById(R.id.show_achievements).setVisibility(View.GONE);

        }else if(view.getId() == R.id.show_leaderboard){
            Log.v("Click","Leaderboard >>>>>>");
            int score = current_pos*100;
            Games.Leaderboards.submitScore(getApiClient(), getString(R.string.number_guesses_leaderboard),score);
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(getApiClient(), getString(R.string.number_guesses_leaderboard)), 2);

        }else if (view.getId() == R.id.show_achievements){
            startActivityForResult(Games.Achievements.getAchievementsIntent(getApiClient()), 1);
        }
    }

    @Override
    public void onSignInSucceeded() {
        findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
        findViewById(R.id.show_leaderboard).setVisibility(View.VISIBLE);
        findViewById(R.id.show_achievements).setVisibility(View.VISIBLE);
    }

    @Override
    public void onSignInFailed() {
        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
        findViewById(R.id.sign_out_button).setVisibility(View.GONE);
        findViewById(R.id.show_leaderboard).setVisibility(View.GONE);
        findViewById(R.id.show_achievements).setVisibility(View.GONE);
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
    public void Go_Mult(View view){
        Intent transition_page = new Intent(this,MultiplayerActivity.class);
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

