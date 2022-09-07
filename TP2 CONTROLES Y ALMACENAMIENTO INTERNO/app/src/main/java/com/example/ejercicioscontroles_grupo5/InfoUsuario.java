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

import java.util.HashMap;
import java.util.Map;

public class InfoUsuario extends AppCompatActivity {

    private TextView nombre, apellido, tipoTelefono, telefono, tipoEmail, email, direccion, fechaNacimiento, estudios, intereses;
    private Map<String, String> mapEstudios = new HashMap<String, String>()
    {
        {
            put("pCompleto", "Primario completo");
            put("pIncompleto", "Primario incompleto");
            put("sCompleto", "Secundario completo");
            put("sIncompleto", "Secundario incompleto");
            put("otros", "Otros");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);

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
        String Intereses = "";
        SharedPreferences preference = getSharedPreferences("contactos", Context.MODE_PRIVATE);
        nombre.setText(preference.getString("nombre",""));
        apellido.setText(preference.getString("apellido",""));
        tipoTelefono.setText(preference.getString("spTelefono",""));
        telefono.setText(preference.getString("telefono",""));
        tipoEmail.setText(preference.getString("spEmail",""));
        email.setText(preference.getString("email",""));
        direccion.setText(preference.getString("direccion",""));
        fechaNacimiento.setText(preference.getString("fechaNacimiento",""));
        for (Map.Entry<String, String> estudio : mapEstudios.entrySet()) {
            if(preference.getBoolean(estudio.getKey(), false) != false){
                estudios.setText(estudio.getValue());
                break;
            }
        }
        if (preference.getBoolean("deporte", false) != false)
            Intereses += "Deportes ";
        if (preference.getBoolean("arte", false) != false)
            Intereses += "arte ";
        if (preference.getBoolean("musica", false) != false)
            Intereses += "musica ";
        if (preference.getBoolean("tecnologica", false) != false)
            Intereses += "tecnologica ";
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