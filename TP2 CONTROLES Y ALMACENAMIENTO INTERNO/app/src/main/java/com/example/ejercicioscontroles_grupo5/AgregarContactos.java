package com.example.ejercicioscontroles_grupo5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AgregarContactos extends AppCompatActivity {

    Spinner spTelefono;
    Spinner spEmail;
    private EditText nombre;
    private EditText apellido;
    private EditText telefono;
    private EditText email;
    private EditText direccion;
    private EditText fechaNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_agregar_contactos);


        spTelefono=findViewById(R.id.spTelefono);
        spEmail=findViewById(R.id.spMail);

        nombre = (EditText) findViewById(R.id.editTextNombre);
        apellido = (EditText) findViewById(R.id.editTextApellido);
        telefono = (EditText) findViewById(R.id.editTextPhone);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        direccion = (EditText) findViewById(R.id.editTextDireccion);
        fechaNacimiento = (EditText) findViewById(R.id.editTextDate);

        String [] opciones = {"Casa", "Trabajo", "Personal"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spTelefono.setAdapter(adapter);
        spEmail.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void agregarActivity (View view){
        Intent agregar = new Intent(this, AgregarContactos.class);
        startActivity(agregar);
    }

    public void listadoActivity (View view){
        Intent listado = new Intent(this, ListadoContactos.class);
        startActivity(listado);
    }

    public void agregarActivity2 (View view){
        Intent agregar2 = new Intent(this, AgregarContactos2.class);
        agregar2.putExtra("nombre", nombre.getText().toString());
        agregar2.putExtra("apellido", apellido.getText().toString());
        agregar2.putExtra("telefono", telefono.getText().toString());
        agregar2.putExtra("email", email.getText().toString());
        agregar2.putExtra("direccion", direccion.getText().toString());
        agregar2.putExtra("fechaNacimiento", fechaNacimiento.getText().toString());
        agregar2.putExtra("spTelefono", spTelefono.getSelectedItem().toString());
        agregar2.putExtra("spEmail", spEmail.getSelectedItem().toString());
        startActivity(agregar2);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == R.id.agregar) agregarActivity(null);
        if (id == R.id.listar) listadoActivity(null);
        if (id == R.id.listar || id == R.id.agregar) return true;
        return super.onOptionsItemSelected(item);
    }




}