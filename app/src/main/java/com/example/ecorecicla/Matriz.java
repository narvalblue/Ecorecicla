package com.example.ecorecicla;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
public class Matriz extends AppCompatActivity {
    public Spinner spinner;
    public Button button;
    private EditText editText;
    private TableLayout tableLayout;
    private int selectedColumn = 0;
    private Set<String> addedData = new HashSet<>();
    private double sumaPlastico = 0.0;
    private double sumaPapel = 0.0;
    private double sumaVidrio = 0.0;
    private double sumaMetal = 0.0;
    private double sumaElectronicos = 0.0;
    private  double totalsuma = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matriz);
        editText = findViewById(R.id.read);
        button = findViewById(R.id.botn1);
        tableLayout = findViewById(R.id.table1);
        spinner = findViewById(R.id.spineer);
        ImageView imageView = findViewById(R.id.statistics);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(Matriz.this, MainActivity.class);
            startActivity(intent);
        });
        // Si alguno de los datos que se vayan a guardan vayan a ser nulos
        if (editText == null || button == null || tableLayout == null || spinner == null) {
            Log.e("Matriz", "Uno o mas daots son nulos");
            return;
        }
        // Creamos una lista para almcenar los daot sleidos y poder iterar sobre ellos con sus respectivos id .
        List<String[]> values = readFile();
        for (String[] parts : values) {
            if (parts.length == 3) {
                int column = Integer.parseInt(parts[0]);
                String id = parts[1];
                String text = parts[2];
                if (!addedData.contains(id)) {
                    AddtoTable(text, column);
                    addedData.add(id); // Agregar al registro
                }
            }
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.options_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedColumn = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //Boton de añadir
        button.setOnClickListener(v -> {
            String text = editText.getText().toString().replaceAll("\\s+", "");
            String uniqueId = UUID.randomUUID().toString(); // Generar un identificador único
            AddtoTable(text, selectedColumn);
            addedData.add(uniqueId); // Agregar al registro
            storage(text, selectedColumn, uniqueId); // Almacenar con el identificador único
            Toast.makeText(this,"Añadido correctamente",Toast.LENGTH_SHORT).show();
            editText.setText("");
        });
        storeSums();
        //Botón de eliminar datos
        Button Delete = findViewById(R.id.botn2);
        Delete.setOnClickListener(v ->{
            String encabezado = spinner.getSelectedItem().toString();
            Headings(encabezado);
        });
    }
    public void Headings(String encabezado){
        int columnIndex = -1;
        //Encontramos el indice de la columna basada en el encabezado
        TableRow headerRow = (TableRow) tableLayout.getChildAt(0);
        for(int i = 0; i <headerRow.getChildCount(); i++){
            TextView textView = (TextView) headerRow.getVirtualChildAt(i);
            if(textView.getText().toString().equalsIgnoreCase(encabezado)){
                columnIndex = i;
                break;
            }
        }
        if(columnIndex == -1){
            Toast.makeText(this, "Encabezado no encotrado",Toast.LENGTH_SHORT).show();
            return;
        }
        //Aqui inlcuimos un for para eliminar los datos de la columna correspondiente
        for(int i = 1; i < tableLayout.getChildCount(); i++){ //Comienza desde para 1 para omitir el encabezado.
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            TextView textView = (TextView) row.getChildAt(columnIndex);
            textView.setText("");
        }
        Toast.makeText(this,"Datos elminados de la columna: " +encabezado,Toast.LENGTH_SHORT).show();
    }
    private void AddtoTable(String text, int column) {
        //Creamos una variable booleana inicializada en false
        boolean added = false;
        for (int i = 1; i < tableLayout.getChildCount(); i++) { // Comienza desde 1 para omitir la fila de encabezado
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            TextView textView = (TextView) row.getChildAt(column);
            if (textView.getText().toString().isEmpty()) {
                double value = Double.parseDouble(text);
                textView.setText(String.valueOf(value));
                textView.setTextColor(0xFFFFFFFF);
                updateColumnSum(column, value); // Actualiza la suma de la columna correspondiente
                added = true;
                break;
            }
        }
        //condiocional si es true para que los ñada al TableLayout
        if (!added) {
            TableRow row = new TableRow(this);
            for (int i = 0; i < 5; i++) {
                TextView textView = new TextView(this);
                if (i == column) {
                    double value = Double.parseDouble(text);
                    textView.setText(String.valueOf(value));
                    textView.setTextColor(0xFFFFFFFF);
                    updateColumnSum(column, value); // Actualiza la suma de la columna correspondiente
                } else {
                    textView.setText("");
                }
                row.addView(textView);
            }
            tableLayout.addView(row);
        }
        storeSums();
    }
    private void updateColumnSum(int column, double value) {
        switch (column) {
            case 0:
                sumaPapel += value;
                break;
            case 1:
                sumaMetal += value;
                break;
            case 2:
                sumaPlastico += value;
                break;
            case 3:
                sumaVidrio += value;
                break;
            case 4:
                sumaElectronicos += value;
                break;
        }
        totalSum();
        // Log para verificar las sumas
        Log.d("Matriz", "Suma Plástico: " + sumaPlastico);
        Log.d("Matriz", "Suma Papel: " + sumaPapel);
        Log.d("Matriz", "Suma Vidrio: " + sumaVidrio);
        Log.d("Matriz", "Suma Metal: " + sumaMetal);
        Log.d("Matriz", "Suma Electrónicos: " + sumaElectronicos);
    }

    //Almacenamos los datos
    private void storage(String text, int column, String uniqueId) {
        try {
            File file = new File(getFilesDir(), "my_file.txt");
            FileWriter writer = new FileWriter(file, true);
            writer.write(column + ":" + uniqueId + ":" + text + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Leemos los arichivos y los almacenamos en un array de tipo string
    public List<String[]> readFile() {
        List<String[]> values = new ArrayList<>();
        try {
            File file = new File(getFilesDir(), "my_file.txt"); // el nombre de nuestro archivo.txt
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                values.add(line.split(":"));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }
    //Metodo para evitar que los intent se mezclen entre si.
    private void storeSums() {
        SharedPreferences sharedPreferences = getSharedPreferences("Sums", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("sumaPlastico", (float) sumaPlastico);
        editor.putFloat("sumaPapel", (float) sumaPapel);
        editor.putFloat("sumaVidrio", (float) sumaVidrio);
        editor.putFloat("sumaMetal", (float) sumaMetal);
        editor.putFloat("sumaElectronicos", (float) sumaElectronicos);
        editor.putFloat("totalSum", (float) totalsuma);
        editor.apply();
    }
    // metodo para la suma total de todos los materiales
    public void totalSum(){
        totalsuma = sumaElectronicos + sumaMetal + sumaPapel + sumaPlastico + sumaVidrio;
    }
}