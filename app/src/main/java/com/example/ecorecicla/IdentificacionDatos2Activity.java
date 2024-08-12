package com.example.ecorecicla;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class IdentificacionDatos2Activity extends AppCompatActivity {
    private EditText editTipoMaterial;
    private EditText editCantidadMaterial;
    private EditText editFecha;
    private Button btnNavigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.identificaciondatos2); // Asegúrate de que el nombre del archivo de layout sea correcto

        // Conectar vistas
        editTipoMaterial = findViewById(R.id.editTipoMaterial);
        editCantidadMaterial = findViewById(R.id.editCantidadMaterial);
        editFecha = findViewById(R.id.editFecha);
        btnNavigate = findViewById(R.id.btnNavigate);

        // Configurar el listener para el botón
        btnNavigate.setOnClickListener(v -> {
            String tipoMaterial = editTipoMaterial.getText().toString().trim();
            String cantidadMaterial = editCantidadMaterial.getText().toString().trim();
            String fecha = editFecha.getText().toString().trim();

            // Puedes agregar validaciones aquí si es necesario

            // Mostrar mensaje de confirmación
            Toast.makeText(IdentificacionDatos2Activity.this, "Material añadido", Toast.LENGTH_SHORT).show();

            // Opcional: limpiar campos después de añadir el material
            editTipoMaterial.setText("");
            editCantidadMaterial.setText("");
            editFecha.setText("");
        });
    }
}
