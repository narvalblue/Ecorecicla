package com.example.ecorecicla;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IdentificacionDatos2 extends AppCompatActivity {

    private EditText editTipoMaterial;
    private EditText editCantidadMaterial;
    private EditText editFecha;
    private Button btnNavigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identificaciondatos2);

        // Inicializar las vistas
        editTipoMaterial = findViewById(R.id.editTipoMaterial);
        editCantidadMaterial = findViewById(R.id.editCantidadMaterial);
        editFecha = findViewById(R.id.editFecha);
        btnNavigate = findViewById(R.id.btnNavigate);

        // Configurar el listener para el botón
        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los campos
                String tipoMaterial = editTipoMaterial.getText().toString();
                String cantidadMaterial = editCantidadMaterial.getText().toString();
                String fecha = editFecha.getText().toString();

                // Validar los campos
                if (tipoMaterial.isEmpty() || cantidadMaterial.isEmpty() || fecha.isEmpty()) {
                    // Mostrar un mensaje de error si algún campo está vacío
                    Toast.makeText(IdentificacionDatos2.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                } else {
                    // Mostrar los datos en un Toast (o realizar cualquier otra acción)
                    Toast.makeText(IdentificacionDatos2.this, "Tipo de Material: " + tipoMaterial + "\nCantidad: " + cantidadMaterial + "\nFecha: " + fecha, Toast.LENGTH_SHORT).show();

                    // Aquí puedes agregar la lógica para manejar los datos del formulario, como enviarlos a una base de datos o a otro activity
                }
            }
        });
    }
}


