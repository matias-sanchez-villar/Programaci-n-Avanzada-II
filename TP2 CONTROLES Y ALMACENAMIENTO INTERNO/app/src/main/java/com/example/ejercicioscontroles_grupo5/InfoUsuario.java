package com.example.ejercicioscontroles_grupo5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class InfoUsuario extends AppCompatActivity {

    private TextView nombre, apellido, tipoTelefono, telefono, tipoEmail, email, direccion, fechaNacimiento, estudios, intereses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);
        setContentView(R.layout.activity_main);

        findViewById();
        getShared();
    }

    public void findViewById(){
        nombre = (TextView)findViewById(R.id.txtNombre);
        apellido = (TextView)findViewById(R.id.txtApellido);
        tipoTelefono = (TextView)findViewById(R.id.txtTipoTelefono);
        telefono = (TextView)findViewById(R.id.txtTelefono);
        tipoEmail = (TextView)findViewById(R.id.txtTipoEmail);
        email = (TextView)findViewById(R.id.txtEmail);
        direccion = (TextView)findViewById(R.id.txtDireccion);
        fechaNacimiento = (TextView)findViewById(R.id.txtFechaNacimiento);
        estudios = (TextView)findViewById(R.id.txtEstudios);
        intereses = (TextView)findViewById(R.id.txtEstudios);
    }

    private void getShared (){
        String text;
        //Falta la comprobar si existe
        SharedPreferences preference = getSharedPreferences("contactos", Context.MODE_PRIVATE);
        nombre.setText(preference.getString("nombre",""));
        apellido.setText(preference.getString("apellido",""));
        tipoTelefono.setText(preference.getString("spTelefono",""));
        telefono.setText(preference.getString("telefono",""));
        tipoEmail.setText(preference.getString("spEmail",""));
        email.setText(preference.getString("email",""));
        direccion.setText(preference.getString("direccion",""));
        fechaNacimiento.setText(preference.getString("fechaNacimiento",""));
        estudios.setText(preference.getString("nombre",""));
        intereses.setText(preference.getString("nombre",""));
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

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == R.id.agregar) agregarActivity(null);
        if (id == R.id.listar) listadoActivity(null);
        if (id == R.id.listar || id == R.id.agregar) return true;
        return super.onOptionsItemSelected(item);
    }

}