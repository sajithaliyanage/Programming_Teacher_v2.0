package game.programming.whileloop.canvas_game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.pushpika.canvas_game.R;

public class WinActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
    }
    public void main_menu(View view){
        onBackPressed();
    }
    public void reset_data(View view){
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Current_class","z1");
        editor.putInt("Current_pos",1);
        editor.commit();
        MainActivity.target_class = settings.getString("Current_class","z1");
        MainActivity.current_pos = settings.getInt("Current_pos",1);
        MainActivity.target_pos = settings.getInt("Current_pos",1);
        MainActivity.cur_position=MainActivity.current_pos;
        Log.v("Data reset","successfully");
        Log.v("happened current share",String.valueOf(settings.getString("Current_class","DefaultClass")));
        Log.v("happened current share",String.valueOf(settings.getInt("Current_pos",0)));
        MainActivity.position_height=0;
        MainActivity.position_width=0;
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
       /* Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        startActivity(i);
        finish();
        */
        onBackPressed();
       /*
        finish();
        System.exit(0);
        */
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
