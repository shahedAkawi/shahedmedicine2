package com.example.shahedmedicine2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.shahedmedicine2.data.MyUser;
import com.example.shahedmedicine2.data.MyUserAdapetr;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//5 new activity
//5.1 add Listview to the XML
public class MyUsersActivity extends AppCompatActivity {

    //5.2
    private ListView listView;
    private MyUserAdapetr myUserAdapetr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_users);
        //5.3
        listView=findViewById(R.id.listview);
        myUserAdapetr=new MyUserAdapetr(getBaseContext(),R.layout.task_item2);
        //5.4
        listView.setAdapter(myUserAdapetr);
    }

    //6.
    @Override
    protected void onResume() {
        super.onResume();
        readFromFirebase();
    }

    //5.5 download data from firebase
    public void readFromFirebase()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();//to connect to database
        FirebaseAuth auth=FirebaseAuth.getInstance();//to get current user UID
        String uid = auth.getUid();
        DatabaseReference reference = database.getReference();

        //5.6 add listener to update us about any change
        //change group name:
        reference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                //5.7
                myUserAdapetr.clear();
                for (DataSnapshot d : dataSnapshot.getChildren())
                {
                    //5.8
                    MyUser t=d.getValue(MyUser.class);
                    Log.d("MyUser",t.toString());
                    myUserAdapetr.add(t);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
