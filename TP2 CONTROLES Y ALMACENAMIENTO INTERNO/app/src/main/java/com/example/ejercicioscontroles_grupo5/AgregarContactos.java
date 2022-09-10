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
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        //validaciones
        if(!validaNombreyApellido(nombre.getText().toString())) {
            Toast.makeText(this,"Nombre incorrecto", Toast.LENGTH_SHORT).show();
        }
        else if(!validaNombreyApellido(apellido.getText().toString())) {
            Toast.makeText(this,"Apellido incorrecto", Toast.LENGTH_SHORT).show();
        }
        else if(!validaMail(email.getText().toString())) {
            Toast.makeText(this,"Mail incorrecto", Toast.LENGTH_SHORT).show();
        }
        else if(!android.util.Patterns.PHONE.matcher(telefono.getText().toString()).matches()) {
            Toast.makeText(this,"Telefono incorrecto", Toast.LENGTH_SHORT).show();
        }
        else if(!validafecha(fechaNacimiento.getText().toString())) {
            Toast.makeText(this,"Fecha incorrecta", Toast.LENGTH_SHORT).show();
        } else {
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
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == R.id.agregar) agregarActivity(null);
        if (id == R.id.listar) listadoActivity(null);
        if (id == R.id.listar || id == R.id.agregar) return true;
        return super.onOptionsItemSelected(item);
    }

    public boolean validaMail(String mail) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(mail);
        return mather.matches();
    }
    public boolean validafecha(String fecha) {
        if (fecha == null || !fecha.matches("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((?:19|20)[0-9][0-9])$"))
            return false;
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.parse(fecha);
            return true;
        }catch (ParseException e){
            return false;
        }
    }

    public boolean validaNombreyApellido(String nombreyapellido) {
        Pattern pattern = Pattern.compile("^[A-Za-z]{1,20}$");
        Matcher mather = pattern.matcher(nombreyapellido);
        return mather.matches();
    }



}