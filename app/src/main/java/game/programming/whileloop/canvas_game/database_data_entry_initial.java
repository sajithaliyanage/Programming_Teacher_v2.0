package game.programming.whileloop.canvas_game;

import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.pushpika.canvas_game.R;

public class database_data_entry_initial extends Activity implements CallBack{

    FirebaseData fd;

    SharedPreferences settings1;

    Button btn;
    boolean isRetry = true;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_database_data_entry_initial);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        btn = (Button) findViewById(R.id.go_firstpage);
        btn.setVisibility(View.GONE);

        settings1 = getSharedPreferences("prefs", 0);


        //show loading
        loading = new ProgressDialog(this);
        loading.setTitle("Questions are downloading...");
        loading.setMessage("Please wait... \nConnect to the internet if not connected");
        loading.setCancelable(false);
        loading.show();

        fd = new FirebaseData(this);
        fd.syncQuestions(this,true);

    }

    public void onBtnClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onComplete(){
        loading.dismiss();

        //false the 1st run
        SharedPreferences.Editor editor = settings1.edit();
        editor.putBoolean("firstRun", false);
        editor.commit();

        //appear goto 1st page button
        isRetry = false;
        btn.setText("Get Started");
        btn.setVisibility(View.VISIBLE);

    }

    @Override
    public void onError(){
        //loading.dismiss();
        Toast.makeText(this,"Error connecting internet",Toast.LENGTH_SHORT);

        //Exit the application

    }

}