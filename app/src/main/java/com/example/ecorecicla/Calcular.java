package com.example.ecorecicla;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONObject;
import java.io.FileOutputStream;

public class Calcular extends AppCompatActivity {

    private EditText metalWeight, metalPrice, cartonWeight, cartonPrice, paperWeight, paperPrice;
    private TextView metalProfit, cartonProfit, paperProfit, totalProfit;
    private Button registerButton, resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcular);

        // Referencias a los componentes de la interfaz
        metalWeight = findViewById(R.id.metal_weight);
        metalPrice = findViewById(R.id.metal_price);
        metalProfit = findViewById(R.id.metal_profit);

        cartonWeight = findViewById(R.id.carton_weight);
        cartonPrice = findViewById(R.id.carton_price);
        cartonProfit = findViewById(R.id.carton_profit);

        paperWeight = findViewById(R.id.paper_weight);
        paperPrice = findViewById(R.id.paper_price);
        paperProfit = findViewById(R.id.paper_profit);

        totalProfit = findViewById(R.id.total_profit);

        registerButton = findViewById(R.id.registerButton);
        resetButton = findViewById(R.id.resetButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateProfits();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetInputs();
            }
        });
    }

    private void calculateProfits() {
        double metalGain = calculateGain(metalWeight, metalPrice, metalProfit);
        double cartonGain = calculateGain(cartonWeight, cartonPrice, cartonProfit);
        double paperGain = calculateGain(paperWeight, paperPrice, paperProfit);

        double totalGain = metalGain + cartonGain + paperGain;
        totalProfit.setText(String.format("Ganancia total: %.2f COP", totalGain));

        saveToJSON(metalGain, cartonGain, paperGain, totalGain);
    }

    private double calculateGain(EditText weight, EditText price, TextView profitView) {
        double weightValue = Double.parseDouble(weight.getText().toString());
        double priceValue = Double.parseDouble(price.getText().toString());
        double gain = weightValue * priceValue;
        profitView.setText(String.format("Ganancia: %.2f COP", gain));
        return gain;
    }

    private void resetInputs() {
        metalWeight.setText("");
        metalPrice.setText("");
        metalProfit.setText("Ganancia: 0.0 COP");

        cartonWeight.setText("");
        cartonPrice.setText("");
        cartonProfit.setText("Ganancia: 0.0 COP");

        paperWeight.setText("");
        paperPrice.setText("");
        paperProfit.setText("Ganancia: 0.0 COP");

        totalProfit.setText("Ganancia total: 0.0 COP");
    }

    private void saveToJSON(double metalGain, double cartonGain, double paperGain, double totalGain) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("metalGain", metalGain);
            jsonObject.put("cartonGain", cartonGain);
            jsonObject.put("paperGain", paperGain);
            jsonObject.put("totalGain", totalGain);

            String jsonString = jsonObject.toString();
            FileOutputStream fos = openFileOutput("profits.json", MODE_PRIVATE);
            fos.write(jsonString.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
