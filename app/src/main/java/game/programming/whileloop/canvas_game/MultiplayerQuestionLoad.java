package game.programming.whileloop.canvas_game;

import android.os.Debug;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prabushitha on 5/8/17.
 */

public class MultiplayerQuestionLoad {
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference questionRef;
    int TOTAL_QUESTIONS = 0;
    boolean doneQuestionLoad = false;
    private List<MultiplayerQuestion> allQuestions;
    MultiplayerQuestionLoad(){
        allQuestions = new ArrayList<MultiplayerQuestion>();
        questionRef = mRootRef.child("questions");
        readAllQuestions();
    }
    public MultiplayerQuestion[] getQuestions(int...question_ids){
        int paper_size = question_ids.length;
        MultiplayerQuestion[] ques = new MultiplayerQuestion[paper_size];
        if(allQuestions.size()>=paper_size){
            for(int i=0;i<paper_size;i++){
                ques[i] = allQuestions.get(question_ids[i]);
            }
        }else{
            return null;
        }

        return ques;
    }

    public void readAllQuestions(){
        questionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TOTAL_QUESTIONS=0;
                allQuestions = new ArrayList<MultiplayerQuestion>();
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    MultiplayerQuestion question = d.getValue(MultiplayerQuestion.class);
                    allQuestions.add(question);
                    Log.v("QUESTION",question.getQuestion());
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
