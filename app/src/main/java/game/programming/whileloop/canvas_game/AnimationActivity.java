package game.programming.whileloop.canvas_game;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.pushpika.canvas_game.R;


public class AnimationActivity extends AppCompatActivity {
    //Activity_animation_layout activity_animation_layout;
    board_layout bd_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bd_layout = new board_layout(this);
        bd_layout.setBackgroundResource(R.drawable.board_new2);
        //setContentView(activity_animation_layout);4
        setContentView(bd_layout);
        Log.i("TAG", "current pos-- "+ MainActivity.current_pos);
        Log.i("TAG", "target pos" + MainActivity.target_pos);
        Log.i("TAG", "Target class " + MainActivity.target_class);
        Handler handler = new Handler();
        //final Intent intent = new Intent(this, MainActivity.class);
        final Intent intent = new Intent(this, NewQuestionView.class);
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(intent);
                //bd_layout.setBackgroundColor(Color.WHITE);
                finish();
            }
        }, 5000);


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please wait .....", Toast.LENGTH_SHORT).show();
    }


}