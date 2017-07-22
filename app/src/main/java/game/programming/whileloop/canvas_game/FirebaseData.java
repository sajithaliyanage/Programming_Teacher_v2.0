package game.programming.whileloop.canvas_game;

import android.content.Context;
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

    //DatabaseHelper mydb;


    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    DatabaseReference questionRef; //Question node
    DatabaseReference mQuestionDataRef; //QArray node
    int TOTAL_QUESTIONS = 0;
    boolean doneQuestionLoad = false;
    private List<NormalQuestion> allQuestions;

    FirebaseData(){
        allQuestions = new ArrayList<NormalQuestion>();
        questionRef = mRootRef.child("Questions");
        mQuestionDataRef = mRootRef.child("Questions").child("QArray");
        readAllQuestions();
    }

    public void readAllQuestions(){
        mQuestionDataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TOTAL_QUESTIONS=0;
                allQuestions = new ArrayList<NormalQuestion>();
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    NormalQuestion question = d.getValue(NormalQuestion.class);
                    allQuestions.add(question);
                    Log.v("QUESTION",question.getDescription());
                    TOTAL_QUESTIONS++;
                }
                doneQuestionLoad = true;
                Log.i("TOTAL QUESTIONS",TOTAL_QUESTIONS+"");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}