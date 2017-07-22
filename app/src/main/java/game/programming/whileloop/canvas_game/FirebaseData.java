package game.programming.whileloop.canvas_game;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.pushpika.canvas_game.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseData {

    DatabaseHelper mydb;
    Context context;
    CallBack caller;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    DatabaseReference questionRef; //Question node
    DatabaseReference mQuestionDataRef; //QArray node
    DatabaseReference lastsyncRef; //lastsync node

    long lastfirebasesync;
    int TOTAL_QUESTIONS = 0;
    int currentpos;



    FirebaseData(Context context, CallBack caller){
        this.context = context;
        this.caller = caller;

        questionRef = mRootRef.child("Questions");
        mQuestionDataRef = mRootRef.child("Questions").child("QArray");
        lastsyncRef = mRootRef.child("Questions").child("lastsync");

        mydb = new DatabaseHelper(context);

        syncQuestions();
    }
    public void syncQuestions(){

        lastsyncRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lastfirebasesync = (long)dataSnapshot.getValue();

                SharedPreferences sp = context.getSharedPreferences("prefs",0);
                long devicesync = sp.getLong("lastsync",0);

                currentpos = sp.getInt("last_qpos",17);
                Log.v("Firebase Sync","Last question position is "+currentpos);
                Log.v("Firebase Sync","Questions updated to device on : "+devicesync);
                Log.v("Firebase Sync","Questions added to firebase on : "+lastfirebasesync);


                if(lastfirebasesync != devicesync){
                    readAllQuestions();
                }else{
                    Log.v("Firebase Sync","Questions are up to date");
                    caller.onComplete();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("Firebase Sync","Error Connecting");
                caller.onError();
            }
        });

    }
    private void readAllQuestions(){
        mQuestionDataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("Firebase Sync","Downloading Questions");


                TOTAL_QUESTIONS=0;
                int maxpos = currentpos;
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    NormalQuestion question = d.getValue(NormalQuestion.class);
                    if(question.getCurrentPos()>currentpos){
                        addQuestion(question);

                        if(question.getCurrentPos()>maxpos){
                            maxpos = question.getCurrentPos();
                        }

                        TOTAL_QUESTIONS++;
                    }
                }

                //update device sync time and question position of last sync
                SharedPreferences settings1 = context.getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editor = settings1.edit();
                editor.putLong("lastsync", lastfirebasesync);
                editor.putInt("last_qpos", maxpos);
                editor.commit();


                Log.i("Firebase Sync","Downloaded "+TOTAL_QUESTIONS+" questions. Last Question synced is "+maxpos);
                Log.v("Firebase Sync","Download Completed");
                caller.onComplete();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("Firebase Sync","Error Loading Data");
                caller.onError();
            }

        });

    }

    private void addQuestion(NormalQuestion ques){
        //Insert Main Tags
        for (NormalQuestion.Tag tag:ques.getMainTags()) {
            mydb.insert_Tag(tag.getTag_name(),"MAIN","None");
            mydb.insert_tag_que_assist(ques.getCurrentPos(),tag.getId());
        }
        //Insert Sub Tags
        for (NormalQuestion.Tag tag:ques.getSubTags()) {
            mydb.insert_Tag(tag.getTag_name(),"VARIABLE","None");
            mydb.insert_tag_que_assist(ques.getCurrentPos(),tag.getId());
        }

        //Insert question
        mydb.insert_Question(
                ques.getHeading(), //title
                ques.getDescription(), //description
                ques.getCurrentClass(), //Question class
                ques.getCorrectSequene(), //Answer Sequence (tag ids of correct answers) (multiple answer seperator is | )
                ques.getCurrentPos()+"", //start node (current place)
                ques.getSuccessPos()+"", //success node
                ques.getCurrentPos()+"", //punishment node
                ques.getSuccessClass(), //promotion class
                ques.getCurrentClass() //punishment class
        );


    }
}