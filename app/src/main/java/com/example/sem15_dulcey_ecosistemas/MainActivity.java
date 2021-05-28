package com.example.sem15_dulcey_ecosistemas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseDatabase db;
    private TextView nombreUsuario;
    private Button addContactoBtn, signOut;
    private ListView listaContactos;
    private ContactosAdaptador adapter;
    private FirebaseUser user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addContactoBtn=findViewById(R.id.addContactoBtn);
        listaContactos=findViewById(R.id.listaContactos);
        nombreUsuario = findViewById(R.id.nombreUsuario);
        signOut = findViewById(R.id.signOut);


        adapter = new ContactosAdaptador();

        db = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        auth = FirebaseAuth.getInstance();

        addContactoBtn.setOnClickListener(this);
        signOut.setOnClickListener(this);
        nombreUsuario.setText(user.getDisplayName());


        String uid = auth.getCurrentUser().getUid();
        db.getReference().child("users").child(uid).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot data) {
                        User us = data.getValue(User.class);
                        nombreUsuario.setText(us.getName());
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                }
        );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addContactoBtn:
                Intent in = new Intent(this, AgregarActivity.class);
                startActivity(in);
                break;

            case R.id.signOut:
                auth.signOut();
                Intent inte = new Intent(this, LoginActivity.class);
                startActivity(inte);
                finish();
                break;

        }

    }

}

