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

public class Login extends AppCompatActivity {
    EditText emailId;
    EditText passwordT;
    Button loginBtn;
    TextView CreateBtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailId = findViewById(R.id.emailText);
        passwordT = findViewById(R.id.PasswordText);
        loginBtn = findViewById(R.id.LoginBtn);
        CreateBtn=findViewById(R.id.create);
        firebaseAuth = FirebaseAuth.getInstance();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String Password = passwordT.getText().toString();
                firebaseAuth = FirebaseAuth.getInstance();
                if (email.isEmpty()) {
                    emailId.setError("Please Enter your Email id");
                    emailId.requestFocus();
                } else if (Password.isEmpty()) {
                    passwordT.setError("Please Enter your Password");
                    passwordT.requestFocus();
                }
                firebaseAuth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Login.this,"logged in successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(Login.this,"login failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        CreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }
}