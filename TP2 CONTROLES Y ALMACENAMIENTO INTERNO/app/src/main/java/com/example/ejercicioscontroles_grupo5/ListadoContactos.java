package com.example.ejercicioscontroles_grupo5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListadoContactos extends AppCompatActivity {

    private List<String> listUsuarios = new ArrayList<String>();

    private ListView lvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_contactos);
        setContentView(R.layout.activity_main);

        getShared();
        setAdapter();
        onClickAdapter();
    }

    private void getShared (){
        String text;
        //Falta la comprobar si existe
        SharedPreferences preference = getSharedPreferences("contactos", Context.MODE_PRIVATE);
        //Falta un whilee
        text = preference.getString("nombre","") + " ";
        text += preference.getString("apellido","") + " - ";
        text += preference.getString("email","");
        listUsuarios.add(text);
    }

    private void setAdapter (){
        lvUsers = (ListView)findViewById(R.id.lvUsers);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item_contactos,
                listUsuarios
        );
        lvUsers.setAdapter(adapter);
    }

    private void onClickAdapter(){
        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id){
                //Me retorna el string entero
                lvUsers.getItemAtPosition(position);
            }
        });
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