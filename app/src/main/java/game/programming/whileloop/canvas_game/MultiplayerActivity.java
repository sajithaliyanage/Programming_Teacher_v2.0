package game.programming.whileloop.canvas_game;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pushpika.canvas_game.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class MultiplayerActivity extends AppCompatActivity {
    Context context;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference routeRef;
    int myroom = -1;

    @Override
    protected void onStart(){
        super.onStart();
        routeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //mRootRef.getKey("lastaccess");
                for(DataSnapshot d:dataSnapshot.getChildren()){

                    GameRoom room = d.getValue(GameRoom.class);
                    if(room.getStatus().equals("WAITING") ){
                        //connect to this
                        myroom = room.getRoomid();
                    }
                    Log.i("FIREBASE_Status:",room.getStatus()+"");
                    Log.i("FIREBASE_Time:",room.getTimestamp()+"");
                    Log.i("USER_TIME:",ServerValue.TIMESTAMP+"");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        String dbRouteKey = "rooms";
        routeRef = mRootRef.child(dbRouteKey);
        mRootRef.child("lastaccess").setValue(ServerValue.TIMESTAMP);
        routeRef.child("0/timestamp").setValue(ServerValue.TIMESTAMP);
    }

}
