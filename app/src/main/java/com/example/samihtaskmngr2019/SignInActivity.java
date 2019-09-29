package com.example.samihtaskmngr2019;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignInActivity extends AppCompatActivity {

    private EditText etEmail,etPassWord;
    private Button btnLogIN,btnSignUp;

//    FirebaseAuth auth;//to establish sign in sign up
//    FirebaseUser user;//user
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
//
//        auth=FirebaseAuth.getInstance();
//        if(auth.getCurrentUser()!=null && auth.getCurrentUser().getEmail()!=null)
//        {
//            Intent intent=new Intent(SignInActivity.this,MainTabsActivity.class);
//            startActivity(intent);
//        }


        etEmail=findViewById(R.id .edEmail) ;
        etPassWord=findViewById(R.id .edPassWord) ;
        btnLogIN=findViewById(R.id .btnLogIn) ;
        btnSignUp= findViewById(R.id .btnSignUp) ;


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // كود الانتقال إلى الشاشة الأخرى
                Intent i=new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });
        //2.
        btnLogIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHandler();
            }
        });
    }
//1
    private void dataHandler()
    {
        String email=etEmail.getText().toString();
        String passw=etPassWord.getText().toString();
        boolean isok=true;
//        if(email.length()<4)
//        {
//            etEmail.setError("Email length error");
//            isok=false;
//        }
//        if(email.indexOf("@")<0 || email.indexOf(".")<0)
//        {
//            etEmail.setError("email wrong format");
//            isok=false;
//        }
        if(isValidEmailAddress(email)==false)
        {
            etEmail.setError("Invalid Email");
            isok=false;
        }
        if(passw.length()<8)
        {
            etPassWord.setError("min length 8");
            isok=false;
        }
        if(isok)
        {
            signIn(email,passw);
        }
       // signIn(email,passw);
    }
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private void signIn(String email, String passw) {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //todo go to ,mian  screen (all task activity)


                }
                else
                    {

                    etEmail.setError("email or password is wrong");
                }
            }
        });

    }
//        FirebaseAuth auth=FirebaseAuth.getInstance();
//        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful())
//                {
//                    Toast.makeText(SignInActivity.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
//                     Intent intent=new Intent(SignInActivity.this,MainTabsActivity.class);
//                       startActivity(intent);
//                    //  finish();
//                }
//                else
//                {
//                    Toast.makeText(SignInActivity.this, "signIn failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                    task.getException().printStackTrace();
//                }
//            }
//        });
//    }


}
