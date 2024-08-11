package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText password;
    EditText confirm_password;
    Button buttonSignup;
    boolean accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        buttonSignup = findViewById(R.id.buttonSignup);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }
    private void registerUser(){

        String name = username.getText().toString().trim();
        String email1 = email.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        String confirmPassword = confirm_password.getText().toString().trim();

        accept = ((CheckBox) findViewById(R.id.accept_terms)).isChecked();

        if (name.isEmpty() || email1.isEmpty() || password1.isEmpty() || confirmPassword.isEmpty()){
            Toast.makeText(this,"Datos imcompletos",Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            Toast.makeText(this,"Correo electronico no valido",Toast.LENGTH_SHORT).show();
        } else if (!password1.equals(confirmPassword)){
                Toast.makeText(this, "Contraseñas no coinciden",Toast.LENGTH_SHORT).show();
        } else if (!accept) {
            Toast.makeText(this,"Debe aceptar terminos y condiciones",Toast.LENGTH_SHORT).show();
        } else{
                Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        }
    }
