package com.example.samihtaskmngr2019;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.samihtaskmngr2019.data.MyTask;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    private EditText etTitle,etSubject;
    private SeekBar skbrImportant;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etTitle=findViewById(R.id.etTitle);
       etSubject=findViewById(R.id.etSubject);
//        etDueDate=findViewById(R.id.etDueDate);
        skbrImportant=findViewById(R.id.skbrImportant);
//        skbrNecessary=findViewById(R.id.skbrNeccesary);
        btnSave=findViewById(R.id.btnSaveTask);
//        btnDatePicker=findViewById(R.id.btnDatePicker);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();
            }
        });
//        btnDatePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    private void dataHandler() {
        boolean isok=true;
        String title=etTitle.getText().toString();
//        String text=etText.getText().toString();
//        String  date=etDueDate.getText().toString();
        int important=skbrImportant.getProgress();
//        int necessary=skbrNecessary.getProgress();
        if(title.length()==0)
        {
            etTitle.setError("Title can not be empty");
            isok=false;

        }
//        if(text.length()==0)
//        {
//            etText.setError("Text can not be empty");
//            isok=false;
//
//        }
        if(isok)
        {
            MyTask t=new MyTask();
            t.setTitle(title);
            t.setImportant(important);
            createTask(t);
//            MyTask task=new MyTask();
//            task.setCreatedAt(new Date());
//            //task.setDueDate(new Date(date));
//            task.setText(text);
//            task.setTitle(title);
//            task.setImportant(important);
//            task.setNecessary(necessary);
//
//            //get user email to set is as the owner of this task
//            FirebaseAuth auth = FirebaseAuth.getInstance();
//            task.setOwner(auth.getCurrentUser().getEmail());
//// to get the database root reference
//            DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
//
//           //to get uid(universal id)
//            String key=reference.child("MyTasks").push().getKey();
//            task.setKey(key);
//
//            reference.child("MyTasks").child(key).setValue(task).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if(task.isSuccessful())
//                    {
//                        Toast.makeText(AddTaskActivity.this, "Add Successful", Toast.LENGTH_LONG).show();
//                        Intent intent=new Intent(getBaseContext(),AddTaskActivity.class);
//                        startActivity(intent);
//                    }
//                    else
//                    {
//                        Toast.makeText(AddTaskActivity.this, "Add Faild", Toast.LENGTH_LONG).show();
//
//                    }
//                }
//            });
//
//


        }


    }

    private void createTask(MyTask t)
    {
        //.1
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //.2
        DatabaseReference reference =
                database.getReference();
        //to get the user uid (or other details like email)
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        t.setOwner(uid);

        String key = reference.child("tasks").push().getKey();
        reference.child("tasks").child(uid).child(key).setValue(t).addOnCompleteListener(AddTaskActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(AddTaskActivity.this, "add successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(AddTaskActivity.this, "add failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }

            }
        });



    }
}
