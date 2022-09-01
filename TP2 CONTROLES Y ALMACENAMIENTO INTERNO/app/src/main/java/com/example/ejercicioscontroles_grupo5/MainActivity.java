package com.example.ejercicioscontroles_grupo5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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