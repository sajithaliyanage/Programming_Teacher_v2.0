package com.example.pushpika.canvas_game;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.games.Games;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.example.games.basegameutils.BaseGameActivity;

import java.util.Arrays;
import java.util.List;

public class NewQuestionView extends BaseGameActivity {

    DatabaseHelper mydb;
    LinearLayout compulsary_words_field,optional_words_field;
    TextView textView,pos_view;
    String cur_text="",quest_topic="",quest_desc="",cur_seq="",answer_get="",start_node="",promotion_node="",punishment_node="",promotion_class="",punishment_class="";
    String text="",id="";
    String text_arr [] = new String [100];
    String id_arr [] = new String[100];
    int cur_len=0;
    int score;

    //multiple answers
    String answers[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //share current stage
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Current_class",MainActivity.target_class);
        editor.putInt("Current_pos",MainActivity.current_pos);
        editor.commit();
        Log.v("happened current share",String.valueOf(settings.getInt("Current_pos",0)));
        Log.v("happened cu","there is current share");
        if(MainActivity.target_class.equals("V")){
            //when got 100 marks
            Intent intent = new Intent(this, WinActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_new_question_view);
        pos_view = (TextView) findViewById(R.id.position_number);
        compulsary_words_field = (LinearLayout) findViewById(R.id.mainArea);
        optional_words_field = (LinearLayout) findViewById(R.id.optArea);
        textView = (TextView) findViewById(R.id.textView2);
        dynamic_content();
        show_message(quest_topic,quest_desc);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSignInSucceeded() {

    }

    @Override
    public void onSignInFailed() {

    }

    public void dynamic_content(){
        mydb = new DatabaseHelper(this);
        final Animation animation_fade_out = AnimationUtils.loadAnimation(this, R.anim.fade_out_anim);
        // create the layout params that will be used to define how your
        // button will be displayed
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        final Question_object question_object = mydb.get_data_object(MainActivity.target_class); // we can change class according to question

        quest_topic=question_object.question_topic;
        quest_desc = question_object.question_desc;
        answer_get = question_object.answer_sequence;
        start_node = question_object.start_node;
        promotion_node = question_object.promotion_node;
        punishment_node = question_object.punishment_node;
        promotion_class =question_object.promotion_class;
        punishment_class = question_object.punishment_class;
        score = Integer.parseInt(start_node);

        //split answer and set multiple answers
        answers = answer_get.split("\\|");
        for(int i=0;i<answers.length;i++){
            answers[i] = answers[i].replaceAll(",", "");
        }

        Log.i("TAG", "promotion class "+ promotion_class);
        Log.i("TAG", "punishment class "+ punishment_class);
        LinearLayout ll = null;
        //Create
        for(int j=0;j<question_object.keywords.length;j++)
        {
            // Create LinearLayout
            ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            // Create Button
            final Button btn = new Button(this);
            Log.i("TAG", "The index is"+ question_object.keywords[j]);

            final int finalJ = j;
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.i("TAG", "The index is" + question_object.keywords[finalJ]); //set text for button action
                    //cur_text = textView.getText().toString();
                    //cur_text = cur_text + " " + question_object.keywords[finalJ];
                    //cur_seq = cur_seq + "," + question_object.keywords_id[finalJ];
                    //textView.setText(cur_text);
                    text = question_object.keywords[finalJ];
                    id= question_object.keywords_id[finalJ];
                    add_tag(text, id);
                    v.startAnimation(animation_fade_out);
                }
            });

            // Give button an ID
            btn.setId(j);
            btn.setText((question_object.keywords[j]));
            btn.setTransformationMethod(null);
            btn.setTextSize(Float.parseFloat("12"));
            //btn.setBackgroundColor(Color.parseColor("#CBE32D"));
            // set the layoutParams on the button
            btn.setLayoutParams(params);
            //Add button to LinearLayout
            ll.addView(btn);

            //Add button to LinearLayout defined in XML
            compulsary_words_field.addView(ll);
        }

        LinearLayout ll1 = null;
        //Create
        for(int i=0;i<question_object.variable.length;i++)
        {

            // Create LinearLayout
            ll1 = new LinearLayout(this);
            ll1.setOrientation(LinearLayout.HORIZONTAL);

            // Create Button
            final Button btn = new Button(this);
            Log.i("TAG", "The index is"+ question_object.variable[i]);
            final int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.i("TAG", "The index is" + question_object.variable[finalI]); //set text for button action

                    //cur_text = textView.getText().toString();
                    //cur_text = cur_text + " " + question_object.variable[finalI];
                    //cur_seq = cur_seq + "," + question_object.variable_id[finalI];
                    text = question_object.variable[finalI];
                    id= question_object.variable_id[finalI];
                    add_tag(text, id);
                    //textView.setText(cur_text);
                    v.startAnimation(animation_fade_out);

                }
            });

            // Give button an ID
            btn.setId(i);
            btn.setText(question_object.variable[i]);
            btn.setTransformationMethod(null);
            btn.setTextSize(Float.parseFloat("12"));
            // set the layoutParams on the button
            btn.setLayoutParams(params);
            //Add button to LinearLayout
            ll1.addView(btn);

            //Add button to LinearLayout defined in XML
            optional_words_field.addView(ll1);
        }
        //add position
        //String pos = (String) pos_view.getText();
        pos_view.setText("Position - "+start_node);

    }
    public void add_tag(String tag, String tag_id){
        String temp_str="";
        int i;
        text_arr[cur_len]=tag;
        id_arr[cur_len]=tag_id;
        cur_len+=1;
        for (i=0;i<cur_len;i++){
            temp_str= temp_str+ " " +text_arr[i];
        }
        textView.setText(temp_str);

    }

    public void remove_tag(View view){
        String temp_str="";
        int i;
        if(cur_len!=0) {
            text_arr[cur_len] = null;
            id_arr[cur_len] = null;
            cur_len = cur_len - 1;
        }
        for (i=0;i<cur_len;i++){
            temp_str= temp_str+ " " +text_arr[i];
        }
        textView.setText(temp_str);

    }



    public void view_question(View view){
        show_message(quest_topic,quest_desc);

    }

    public void Go_Board(View view){
        Intent transition_page1 = new Intent(this,AnimationActivity2.class);
        startActivity(transition_page1);
        finish();

    }

    public void show_message(String title, String message){

        AlertDialog.Builder dialog_box = new AlertDialog.Builder(this);
        dialog_box.setCancelable(true);
        dialog_box.setTitle(title);
        dialog_box.setMessage(message);
        dialog_box.show();
    }

    public void Go_Home(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void check_answer(View view){
        //answer_get = answer_get.replaceAll(",", "");
        cur_seq="";
        int i;
        for(i=0;i<cur_len;i++){
            cur_seq =cur_seq+ ","+id_arr[i];
        }
        cur_seq = cur_seq.replaceAll(",", "");
        Log.i("TAG", "The answer sequence is" +answer_get); //set text for button action
        Log.i("TAG", "The current sequence is" + cur_seq ); //set text for button action

        //multiple answer check
        List<String> answerlist = Arrays.asList(answers);

        if(answerlist.contains(cur_seq)){ //return true
            //correct notification + increment board
            this.upgrade_leaderboard_score(score);
            correctAnswer(view);
            //textView.setBackgroundColor(Color.GREEN);
            //increment_board();

        }
        else if(cur_seq.equals("")){
            //input field is empty
        }
        else{ //return false
            wrongAnswer(view);
        }

    }


    public void increment_board(){
        //Intent transition_page = new Intent(this,Question_view.class);
        Intent transition_page = new Intent(this,AnimationActivity.class);
        MainActivity.current_pos=Integer.parseInt(start_node);
        if(Integer.parseInt(start_node)<Integer.parseInt(promotion_node)){
            MainActivity.is_increment = 1;
            MainActivity.target_pos = Integer.parseInt(promotion_node);
            MainActivity.target_class = promotion_class;
        }else{
            MainActivity.is_increment = 0;
            MainActivity.target_pos = Integer.parseInt(promotion_node);
            MainActivity.target_class = promotion_class;
        }


        Log.i("TAG", "current pos-- "+ MainActivity.current_pos);
        Log.i("TAG", "target pos"+ MainActivity.target_pos);
        Log.i("TAG", "Target class "+ MainActivity.target_class);
        this.upgrade_leaderboard_score(score);
        startActivity(transition_page);
        finish();

    }

    public void upgrade_leaderboard_score(int value){

        int score = value *100;
        if(getApiClient().isConnected()){
            Games.Leaderboards.submitScore(getApiClient(), getString(R.string.number_guesses_leaderboard),score);
        }

        if(value == 1){
            if(getApiClient().isConnected())
                Games.Achievements.unlock(getApiClient(),getString(R.string.correct_guess_achievement));
        }

    }

    public void decrement_board(){
        Intent transition_page = new Intent(this,AnimationActivity.class);
        MainActivity.current_pos=Integer.parseInt(start_node);
        MainActivity.is_increment = 0;
        MainActivity.target_pos = Integer.parseInt(punishment_node);
        MainActivity.target_class = punishment_class;
        Log.i("TAG", "current pos-- "+ MainActivity.current_pos);
        Log.i("TAG", "target pos"+ MainActivity.target_pos);
        Log.i("TAG", "Target class "+ MainActivity.target_class);
        startActivity(transition_page);
        finish();
    }



    public void correctAnswer(View view) {

        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.check);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).
                        setMessage("Congratulaions!! Your answer is correct.").
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                increment_board();
                            }
                        }).
                        setView(image);
        builder.create().show();

    }

    public void wrongAnswer(View view) {

        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.cancel);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).
                        setMessage("Error!! Your answer is incorrect.").
                        setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                decrement_board();
                            }
                        }).
                        setView(image);
        builder.create().show();

    }


}

