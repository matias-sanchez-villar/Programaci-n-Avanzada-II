package com.example.ejercicioscontroles_grupo5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class AgregarContactos extends AppCompatActivity {

    Spinner spTelefono;
    Spinner spEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contactos);


        spTelefono=findViewById(R.id.spTelefono);
        spEmail=findViewById(R.id.spMail);

        String [] opciones = {"Casa", "Trabajo", "Personal"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spTelefono.setAdapter(adapter);
        spEmail.setAdapter(adapter);


    }




}