package com.example.sem15_dulcey_ecosistemas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText regName, regTelefono, regEmail, regPassword;
    private Button registerBtn;
    private FirebaseDatabase db;
    private FirebaseAuth auth;
    private TextView toLoginLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        regName = findViewById(R.id.regName);
        regTelefono = findViewById(R.id.regTelefono);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        toLoginLink = findViewById(R.id.toLoginLink);
        registerBtn = findViewById(R.id.registerBtn);


        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        toLoginLink.setOnClickListener(this);

        /*if(regPassword.getText()==regPasswordConfirm.getText()){
            registerBtn.setOnClickListener(this);
            Toast.makeText(this, "SIRVE", Toast.LENGTH_SHORT).show();
        }*/


        registerBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toLoginLink:
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.registerBtn:

                    auth.createUserWithEmailAndPassword(
                            regEmail.getText().toString(),
                            regPassword.getText().toString()
                    ).addOnSuccessListener(
                            response -> {
                                String uid = auth.getCurrentUser().getUid();
                                User user = new User(
                                        uid,
                                        regName.getText().toString(),
                                        regTelefono.getText().toString(),
                                        regEmail.getText().toString()
                                );
                                //Escribir objeto en db
                                db.getReference().child("users").child(user.id).setValue(user)
                                        .addOnSuccessListener(
                                                dbresponse -> {
                                                    Intent in = new Intent(this, MainActivity.class);
                                                    startActivity(in);
                                                }
                                        );
                            }
                    ).addOnFailureListener(
                            error -> {
                                Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                    );

                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                

                break;
        }
    }
}