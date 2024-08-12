package com.example.ecorecicla;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    public ProgressBarAnimation progressBarAnimation;
    public ProgressBarAnimation progressBarAnimation2;
    public ProgressBarAnimation progressBarAnimation3;
    public ProgressBarAnimation progressBarAnimation4;
    public ProgressBarAnimation progressBarAnimation5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView mihoja = findViewById(R.id.autumn_leaf);
        mihoja.setRotation(27);
        ImageView imageView = findViewById(R.id.House);
        imageView.setOnClickListener(v -> {Intent intent = new Intent(MainActivity.this, Matriz.class);
            startActivity(intent);
        });
    }

    private double Total() {
        SharedPreferences sharedPreferences = getSharedPreferences("Sums", MODE_PRIVATE);
        return sharedPreferences.getFloat("totalSum", 0.0f);
    }
    @Override
    protected void onResume() {
        super.onResume();
        updateTotalSum();
        Total();

        //los valores de las sumas desde el Intent
        SharedPreferences sharedPreferences = getSharedPreferences("Sums", MODE_PRIVATE);
        double sumaPlastico = sharedPreferences.getFloat("sumaPlastico", 0.0f);
        double sumaPapel = sharedPreferences.getFloat("sumaPapel", 0.0f);
        double sumaVidrio = sharedPreferences.getFloat("sumaVidrio", 0.0f);
        double sumaMetal = sharedPreferences.getFloat("sumaMetal", 0.0f);
        double sumaElectronicos = sharedPreferences.getFloat("sumaElectronicos", 0.0f);

        // Inicialización las barras de progreso
        ProgressBar progressBar1 = findViewById(R.id.electronicBar);
        ProgressBar progressBar2 = findViewById(R.id.plasticBar);
        ProgressBar progressBar3 = findViewById(R.id.glassBar);
        ProgressBar progressBar4 = findViewById(R.id.metalBar);
        ProgressBar progressBar5 = findViewById(R.id.paperBar);

        //Ubicación de los objetos tipo TextView
        TextView textView = findViewById(R.id.percent);
        TextView textView1 = findViewById(R.id.percent1);
        TextView textView2 = findViewById(R.id.percent2);
        TextView textView3 = findViewById(R.id.percent3);
        TextView textView4 = findViewById(R.id.percent4);

        textView.setText(String.valueOf(sumaPlastico + " gr")) ;
        textView1.setText(String.valueOf(sumaPapel + " gr"));
        textView2.setText(String.valueOf(sumaVidrio + " gr"));
        textView3.setText(String.valueOf(sumaMetal + " gr"));
        textView4.setText(String.valueOf(sumaElectronicos + " gr"));

        // Inicialización las animaciones de las barras de progreso
        progressBarAnimation = new ProgressBarAnimation(this, progressBar1, 0, sumaElectronicos);
        progressBarAnimation2 = new ProgressBarAnimation(this, progressBar2, 0, sumaPlastico);
        progressBarAnimation3 = new ProgressBarAnimation(this, progressBar3, 0, sumaVidrio);
        progressBarAnimation4 = new ProgressBarAnimation(this, progressBar4, 0, sumaMetal);
        progressBarAnimation5 = new ProgressBarAnimation(this, progressBar5, 0, sumaPapel);

        //la duración de las animaciones
        progressBarAnimation.setDuration(3000);
        progressBarAnimation2.setDuration(3000);
        progressBarAnimation3.setDuration(3000);
        progressBarAnimation4.setDuration(3000);
        progressBarAnimation5.setDuration(3000);

        // Inicio las animaciones
        progressBar1.startAnimation(progressBarAnimation);
        progressBar2.startAnimation(progressBarAnimation2);
        progressBar3.startAnimation(progressBarAnimation3);
        progressBar4.startAnimation(progressBarAnimation4);
        progressBar5.startAnimation(progressBarAnimation5);

//-----------Atributos del BarChart-----------------------
        BarChart barChart = new BarChart(this);
        double totalSum = Total();
        barChart.bar(totalSum);
    }
    //Actualización de la suma total
    private void updateTotalSum() {
        TextView number = findViewById(R.id.number);
        final Handler increment = new Handler();
        // Obtener la suma total desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("Sums", MODE_PRIVATE); // esta línea de código obtiene una instancia de SharedPreferences llamada "Sums"-
        // que solo es accesible por la aplicación actual
        final double finalValue = sharedPreferences.getFloat("totalSum", 0.0f);
        Log.d("Main", "Suma total: " + finalValue);
        // El valor final al que va llegar el numero
        final int[] curretnValue = {0};
        //El valor actual, inicializado en 0
        final Runnable runner = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            //metodo de animación de los progressBar
            public void run() {
                if (curretnValue[0] < finalValue) {
                    curretnValue[0]++;
                    number.setText(curretnValue[0] + "gr");
                    //incrementa en 1 cada vez que se menor al valor final
                    increment.postDelayed(this, 30);
                    //un delay de 30 ms
                } else {
                    increment.removeCallbacks(this);
                }
            }
        };
        increment.post(runner);
    }
}