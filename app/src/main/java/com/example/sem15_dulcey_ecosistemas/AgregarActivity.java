package com.example.sem15_dulcey_ecosistemas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AgregarActivity extends AppCompatActivity {

    private FirebaseDatabase db;
    private EditText nombreContacto;
    private EditText telefonoContacto;
    private Button botonAgregarContacto;
    private ContactosAdaptador adapter;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        nombreContacto=findViewById(R.id.nombreContacto);
        telefonoContacto=findViewById(R.id.telefonoContacto);
        botonAgregarContacto=findViewById(R.id.botonAgregarContacto);

        adapter = new ContactosAdaptador();

        db = FirebaseDatabase.getInstance();


        loadDatabase();
        /*botonAgregarContacto.setOnClickListener(
                (v)->{

                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);

                }
        );*/
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.botonAgregarContacto :
            String id = db.getReference().child("contactos").child(userId).push().getKey();
                DatabaseReference reference = db.getReference().child("contactos").child(userId).child(id);
                Contacto contactos = new Contacto (
                        id,
                        userId,
                        nombreContacto.getText().toString(),
                        telefonoContacto.getText().toString()
                );
                reference.setValue(contactos);

                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }

        }


    private void loadDatabase() {
        DatabaseReference ref = db.getReference().child("contactos").child(userId);
        ref.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot data) {
                        adapter.clear();
                        for (DataSnapshot child : data.getChildren()){
                            Contacto contactos = child.getValue(Contacto.class);
                            adapter.addContacto(contactos);
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
            }
        );
    }
}