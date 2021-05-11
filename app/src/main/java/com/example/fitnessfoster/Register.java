package com.example.fitnessfoster;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText fullName;
    EditText phone;
    EditText emailId;
    EditText passwordT;
    Button BtnSign;
    TextView loginBtn;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullName=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        emailId=findViewById(R.id.emailText);
        passwordT=findViewById(R.id.PasswordText);
        BtnSign=findViewById(R.id.SignupBtn);
        loginBtn=findViewById(R.id.textview1);


        BtnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailId.getText().toString();
                String Password=passwordT.getText().toString();
                firebaseAuth=FirebaseAuth.getInstance();
                if(email.isEmpty())
                {
                    emailId.setError("Please Enter your Email id");
                    emailId.requestFocus();
                }else if(Password.isEmpty())
                {
                    passwordT.setError("Please Enter your Password");
                    passwordT.requestFocus();
                }else if(!email.isEmpty() && !Password.isEmpty())
                {
                    firebaseAuth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(getApplicationContext(),HomePage.class));

                            }else
                            {
                                Toast.makeText(Register.this,"signup failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}