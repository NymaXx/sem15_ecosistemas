package com.example.sem15_dulcey_ecosistemas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private EditText nombreRegistro;
    private EditText telefonoRegistro;
    private EditText correoRegistro;
    private EditText contrasenaRegistro;
    private EditText contrasenaAgainRegistro;
    private Button botonRegistrar;
    private TextView cuentaPrevia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombreRegistro=findViewById(R.id.nombreRegistro);
        telefonoRegistro=findViewById(R.id.telefonoRegistro);
        correoRegistro=findViewById(R.id.correoRegistro);
        contrasenaRegistro=findViewById(R.id.contrasenaRegistro);
        contrasenaAgainRegistro=findViewById(R.id.contrasenaAgainRegistro);
        botonRegistrar=findViewById(R.id.botonRegistrar);
        cuentaPrevia=findViewById(R.id.cuentaPrevia);

        botonRegistrar.setOnClickListener(
                (v)->{

                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);

                }
        );

        cuentaPrevia.setOnClickListener(
                (v)->{

                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);

                }
        );
    }
}