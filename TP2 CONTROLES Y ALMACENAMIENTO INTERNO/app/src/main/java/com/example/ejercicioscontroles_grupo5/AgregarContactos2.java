package com.example.ejercicioscontroles_grupo5;

import android.content.Context;
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
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre", nombre);
        editor.putString("apellido", apellido);
        editor.putString("telefono", telefono);
        editor.putString("email", email);
        editor.putString("direccion", direccion);
        editor.putString("fechaNacimiento", fechaNacimiento);
        editor.putString("spTelefono", spTelefono);
        editor.putString("spEmail", spEmail);
        editor.putBoolean("pCompleto", primarioCompleto.isChecked());
        editor.putBoolean("pIncompleto", primarioIncompleto.isChecked());
        editor.putBoolean("sCompleto", secundarioCompleto.isChecked());
        editor.putBoolean("sIncompleto", secundarioIncompleto.isChecked());
        editor.putBoolean("otros", otros.isChecked());
        editor.putBoolean("deporte", deporte.isChecked());
        editor.putBoolean("arte", arte.isChecked());
        editor.putBoolean("musica", musica.isChecked());
        editor.putBoolean("tecnologica", tecnologia.isChecked());
        editor.putBoolean("recibeInformacion", recibeInformacion.isChecked());

        editor.commit();

        Toast.makeText(this, "El contacto ha sido guardado", Toast.LENGTH_SHORT).show();
    }
}