package com.example.samihtaskmngr2019;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.samihtaskmngr2019.data.MyTask;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class AddTaskActivity extends AppCompatActivity {
    private EditText etTitle,etSubject;
    private SeekBar skbrImportant;
    private Button btnSave;

    //upload: 0.1 add firebase storage
    //upload: 0.2 add this per,issions to manifest xml
//          <uses-permission android:name="android.permission.INTERNET" />
//          <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

    //upload: 1 add Xml image view or butn and uplaod button
    //upload: 2
    private ImageButton imgBtnl;
    private Button btnUpload;
    private TextView tvImgUrl;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //upload: 3
        imgBtnl=findViewById(R.id.imgBtn);
        btnUpload=findViewById(R.id.btnUpload);
        tvImgUrl=findViewById(R.id.tvImgURL);

        //upload: 4
        imgBtnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), 99);
            }
        });
        ////upload: 6
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

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

    //upload: 5
    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            FirebaseStorage storage= FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99 && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imgBtnl.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
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
        t.setKey(key);
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
