package game.programming.whileloop.canvas_game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.pushpika.canvas_game.R;


public class AnimationActivity2 extends Activity {
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
        //setContentView(activity_animation_layout);
        setContentView(bd_layout);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, NewQuestionView.class);
        startActivity(intent);
        finish();
    }
}