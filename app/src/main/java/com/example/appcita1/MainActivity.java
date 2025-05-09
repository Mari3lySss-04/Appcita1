package com.example.appcita1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btnImprimir;
    EditText txtMensaje;
    CalendarView micalendario;
    CheckBox check;
    String fechaCalendario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        micalendario = findViewById(R.id.calendarView2);
        btnImprimir = findViewById(R.id.button1);
        txtMensaje = findViewById(R.id.textView2);
        check = findViewById(R.id.checkBox2);
        ImageButton btnIrSegundo = findViewById(R.id.imageButton);

        // Fecha inicial (en caso de que no se seleccione ninguna)
        Date fechaActual = new Date(micalendario.getDate());
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        fechaCalendario = formato.format(fechaActual);

        // Actualizar fecha al cambiar en el calendario
        micalendario.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // sumamos 1 al mes porque empieza desde 0
            fechaCalendario = year + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", dayOfMonth);
        });

        btnImprimir.setOnClickListener(v -> {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat horaFormato = new SimpleDateFormat("HH:mm:ss");
            String hora = horaFormato.format(new Date());

            String mensaje = txtMensaje.getText().toString();
            String estadoCheck = check.isChecked() ? "True" : "false";

            String salida = "Texto: " + mensaje + " CheckBox: " + estadoCheck + " Hora: " + hora  + " Fecha: " + fechaCalendario;

            Toast.makeText(getApplicationContext(), salida, Toast.LENGTH_LONG).show();
        });

        btnIrSegundo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SegundoActivity.class);
            startActivity(intent);
        });

    }

}
