package com.example.sem15_dulcey_ecosistemas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText logEmail, logPassword;
    private Button loginBtn;
    private TextView toRegLink;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logEmail=findViewById(R.id.logEmail);
        logPassword=findViewById(R.id.logPassword);
        loginBtn=findViewById(R.id.loginBtn);
        toRegLink=findViewById(R.id.toRegLink);

        auth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(this);
        toRegLink.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                Intent in = new Intent(this, MainActivity.class);
                startActivity(in);

              /*  auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = auth.getCurrentUser();
                                    updateUI(user);
                                        Intent intent = new Intent(this, MainActivity.class);
                                        startActivity(intent);
                                } else {
                                    updateUI(null);
                                }
                            }
                        });*/


                break;

            case R.id.toRegLink:
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
        }
    }
}