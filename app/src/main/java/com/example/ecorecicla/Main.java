package com.example.ecorecicla;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton; // Importa la clase ImageButton



public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Obtén una referencia al ImageButton dentro de onCreate
        ImageButton btnMap = findViewById(R.id.btn_map);

        // Configura el OnClickListener para el ImageButton
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// Crea un Intent para iniciar la actividad CalcularReciclaje
                Intent intent = new Intent(Main.this, Calcular.class);
                startActivity(intent); // Inicia la actividad
            }
        });
    }
}