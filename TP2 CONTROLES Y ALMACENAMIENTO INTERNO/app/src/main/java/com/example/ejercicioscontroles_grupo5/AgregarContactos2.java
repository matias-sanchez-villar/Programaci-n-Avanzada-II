package com.example.ejercicioscontroles_grupo5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class AgregarContactos2 extends AppCompatActivity {

    private RadioButton primarioIncompleto;
    private RadioButton primarioCompleto;
    private RadioButton secundarioCompleto;
    private RadioButton secundarioIncompleto;
    private RadioButton otros;
    private CheckBox deporte;
    private CheckBox arte;
    private CheckBox musica;
    private CheckBox tecnologia;
    private Switch recibeInformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contactos2);

        primarioIncompleto = (RadioButton) findViewById(R.id.rbPrimarioIncompleto);
        primarioCompleto = (RadioButton) findViewById(R.id.rbPrimarioCompleto);
        secundarioCompleto = (RadioButton) findViewById(R.id.rbSecundarioCompleto);
        secundarioIncompleto = (RadioButton) findViewById(R.id.rbSecundarioIncompleto);
        otros = (RadioButton) findViewById(R.id.rbOtros);
        deporte = (CheckBox) findViewById(R.id.cbDeporte);
        arte = (CheckBox) findViewById(R.id.cbArte);
        musica = (CheckBox) findViewById(R.id.cbMusica);
        tecnologia = (CheckBox) findViewById(R.id.cbTecnologia);
        recibeInformacion = (Switch) findViewById(R.id.recibeInformacion);
    }

    public void Guardar(View view){
        String nombre = getIntent().getStringExtra("nombre");
        String apellido = getIntent().getStringExtra("apellido");
        String telefono = getIntent().getStringExtra("telefono");
        String email = getIntent().getStringExtra("email");
        String direccion = getIntent().getStringExtra("direccion");
        String fechaNacimiento = getIntent().getStringExtra("fechaNacimiento");
        String spTelefono = getIntent().getStringExtra("spTelefono");
        String spEmail = getIntent().getStringExtra("spEmail");

        SharedPreferences preferences = getSharedPreferences("contactos", Context.MODE_PRIVATE);

        int cantidadContactos = preferences.getInt("cantidadContactos", -1);

        if (cantidadContactos == -1){
            cantidadContactos = 1;
        } else {
            cantidadContactos++;
        }

        SharedPreferences.Editor editor = preferences.edit();


        editor.putString("nombre" + cantidadContactos, nombre);
        editor.putString("apellido" + cantidadContactos, apellido);
        editor.putString("telefono" + cantidadContactos, telefono);
        editor.putString("email" + cantidadContactos, email);
        editor.putString("direccion" + cantidadContactos, direccion);
        editor.putString("fechaNacimiento" + cantidadContactos, fechaNacimiento);
        editor.putString("spTelefono" + cantidadContactos, spTelefono);
        editor.putString("spEmail" + cantidadContactos, spEmail);
        editor.putBoolean("pCompleto" + cantidadContactos, primarioCompleto.isChecked());
        editor.putBoolean("pIncompleto" + cantidadContactos, primarioIncompleto.isChecked());
        editor.putBoolean("sCompleto" + cantidadContactos, secundarioCompleto.isChecked());
        editor.putBoolean("sIncompleto" + cantidadContactos, secundarioIncompleto.isChecked());
        editor.putBoolean("otros" + cantidadContactos, otros.isChecked());
        editor.putBoolean("deporte" + cantidadContactos, deporte.isChecked());
        editor.putBoolean("arte" + cantidadContactos, arte.isChecked());
        editor.putBoolean("musica" + cantidadContactos, musica.isChecked());
        editor.putBoolean("tecnologica" + cantidadContactos, tecnologia.isChecked());
        editor.putBoolean("recibeInformacion" + cantidadContactos, recibeInformacion.isChecked());

        editor.putInt("cantidadContactos", cantidadContactos);

        editor.commit();

        Toast.makeText(this, "El contacto ha sido guardado", Toast.LENGTH_SHORT).show();

        iniciarMainActivity();
    }

    public void iniciarMainActivity(){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
}