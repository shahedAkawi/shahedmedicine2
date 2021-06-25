package com.example.shahedmedicine2.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils
{
    public   static FirebaseAuth auth=FirebaseAuth.getInstance();
    public static FirebaseDatabase db=FirebaseDatabase.getInstance();
    public static DatabaseReference getRefrence()
    {
        String uid = auth.getUid();
        return  db.getReference().child("tasks").child(uid);
    }
}
