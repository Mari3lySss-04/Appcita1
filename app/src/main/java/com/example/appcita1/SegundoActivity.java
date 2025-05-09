package com.example.appcita1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SegundoActivity extends AppCompatActivity {
    Button closeButton, whatsappButton;
    EditText numberInput;

    // Variables para validar longitud
    String defaultLocal = "+507";
    int limiteCodeLocal = defaultLocal.length();
    int limitePhone = 8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        closeButton = findViewById(R.id.btnVolver);
        whatsappButton = findViewById(R.id.whatsappBtn);
        numberInput = findViewById(R.id.numberInput);

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(v -> finish());
        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = numberInput.getText().toString().trim();

                if (input.length() == (limiteCodeLocal + limitePhone)) {
                    Toast.makeText(getApplicationContext(), "Número ingresado: " + input, Toast.LENGTH_SHORT).show();
                    sendMessageWhatsapp(input);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Por favor ingrese un número válido de " + (limiteCodeLocal + limitePhone) + " dígitos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendMessageWhatsapp(String phone){
        String myphone = phone;
        if (myphone.length() == (limiteCodeLocal + limitePhone)) {
            String numeroPhone = myphone.substring(myphone.lastIndexOf(defaultLocal));
            System.out.println("numeroPhone = " + numeroPhone);
            Handler handler = new Handler();

            handler.post(new Runnable() {
                public void run() {
                    String url = "https://api.whatsapp.com/send?phone=" + phone;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    finish();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "FAVOR INGRESE UN CONTACTO VÁLIDO", Toast.LENGTH_SHORT).show();
        }
    }
}
