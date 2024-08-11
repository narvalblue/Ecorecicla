
package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginButton;
    Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.buttonLogin);
        registerButton = findViewById(R.id.buttonRegister);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String emailInput = email.getText().toString();
                String passwordInput = password.getText().toString();

                if (validateCredentials(emailInput,passwordInput)){
                    Toast.makeText(Login.this,"Credenciales validas",Toast.LENGTH_SHORT)
                            .show();

                    Intent intent = new Intent(Login.this, Main.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(Login.this,"Credenciales invalidas",Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });


        Button buttonLogin= findViewById(R.id.buttonRegister);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
    private boolean validateCredentials(String email, String password){
        return "user123@gmail.com".equals(email) && "password123".equals(password);
    }
    }



