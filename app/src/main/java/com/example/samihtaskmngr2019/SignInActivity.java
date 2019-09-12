package com.example.samihtaskmngr2019;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



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
        btnLogIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();
            }
        });
    }

    private void dataHandler()
    {
        String email=etEmail.getText().toString();
        String passw=etPassWord.getText().toString();
        signIn(email,passw);
    }


    private void signIn(String email, String passw) {
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
    }

}
