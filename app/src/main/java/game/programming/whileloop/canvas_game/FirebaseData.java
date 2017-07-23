package game.programming.whileloop.canvas_game;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import android.support.v7.app.AlertDialog;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FirebaseData {

    DatabaseHelper mydb;
    SharedPreferences settings1;

    Context context;
    CallBack caller;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    DatabaseReference mQuestionDataRef; //QArray node
    DatabaseReference lastsyncRef; //lastsync node

    long lastfirebasesync;
    int TOTAL_QUESTIONS = 0;
    int currentpos;

    ProgressDialog loading;

    FirebaseData(Context context){
        this.context = context;
        settings1 = context.getSharedPreferences("prefs", 0);


        mQuestionDataRef = mRootRef.child("Questions").child("QArray");
        lastsyncRef = mRootRef.child("Questions").child("lastsync");


    }
    public void syncQuestions(final CallBack caller, boolean isForceDownload){
        this.caller = caller;
        final boolean force = isForceDownload;



        lastsyncRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lastfirebasesync = (long)dataSnapshot.getValue();

                SharedPreferences sp = context.getSharedPreferences("prefs",0);
                long devicesync = sp.getLong("lastsync",0);

                currentpos = sp.getInt("last_qpos",0);
                Log.v("Firebase Sync","Last question position is "+currentpos);
                Log.v("Firebase Sync","Questions updated to device on : "+devicesync);
                Log.v("Firebase Sync","Questions added to firebase on : "+lastfirebasesync);


                if(lastfirebasesync != devicesync){
                    if(force){
                        //download questions without user permission.
                        readAllQuestions(true);
                    }else{
                        //prompt user to download.
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        //prepare loader for non force downloads. FORCE DOWNLOADS loader should be explicitly defined
                                        loading = new ProgressDialog(context);
                                        loading.setTitle("Questions are downloading...");
                                        loading.setMessage("Please wait... \nConnect to the internet if not connected");
                                        loading.setCancelable(false);
                                        loading.show();

                                        readAllQuestions(false);
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        caller.onError();
                                        break;
                                }
                            }
                        };
                        //some times user navigate to another activity before this get fired
                        try{
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("New Questions are Available").setPositiveButton("Update Now!", dialogClickListener)
                                    .setNegativeButton("Remind Later", dialogClickListener).show();
                        }catch (Exception e){

                            Log.v("Firebase Sync",e.getMessage());
                        }

                    }

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

    private void readAllQuestions(boolean isForceDownload){
        final boolean force = isForceDownload;

        mydb = new DatabaseHelper(context, settings1.getInt("dbversion",1)+1);
        mQuestionDataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("Firebase Sync","Downloading Questions");


                TOTAL_QUESTIONS=0;
                int maxpos = currentpos;
                String endclass = "z1";

                for(DataSnapshot d:dataSnapshot.getChildren()){
                    NormalQuestion question = d.getValue(NormalQuestion.class);

                    addQuestion(question);

                    if(question.getCurrentPos()>=maxpos){
                        maxpos = question.getCurrentPos();
                        endclass = question.getSuccessClass();
                    }

                    TOTAL_QUESTIONS++;
                }

                //update device sync time and question position of last sync
                int mydb_version = settings1.getInt("dbversion",1)+1;
                SharedPreferences.Editor editor = settings1.edit();
                editor.putLong("lastsync", lastfirebasesync);
                editor.putInt("last_qpos", maxpos);
                editor.putInt("dbversion", mydb_version);
                editor.putString("endclass",endclass);
                editor.commit();


                Log.i("Firebase Sync","Downloaded "+TOTAL_QUESTIONS+" questions. Last Question synced is "+maxpos+". End class is"+endclass);
                Log.v("Firebase Sync","Download Completed");

                if(!force){
                    //dismis loader
                    loading.dismiss();
                }
                caller.onComplete();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("Firebase Sync","Error Loading Data");
                if(!force){
                    //dismis loader
                    loading.dismiss();
                }
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

        String q = ques.getHeading()+","+ //title
        ques.getDescription()+","+ //description
                ques.getCurrentClass()+","+ //Question class
                ques.getCorrectSequene()+","+ //Answer Sequence (tag ids of correct answers) (multiple answer seperator is | )
                ques.getCurrentPos()+","+ //start node (current place)
                ques.getSuccessPos()+","+ //success node
                ques.getCurrentPos()+","+ //punishment node
                ques.getSuccessClass()+","+ //promotion class
                ques.getCurrentClass(); //punishment class
        Log.v("DB QUESTION",q);
    }
}