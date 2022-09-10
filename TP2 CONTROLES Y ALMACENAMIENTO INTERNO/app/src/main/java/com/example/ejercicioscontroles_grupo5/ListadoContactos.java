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
import java.util.Objects;

public class ListadoContactos extends AppCompatActivity {

    private final List<String> listUsuarios = new ArrayList<>();
    private ListView lvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_contactos);

        getShared();
        setAdapter();
        Intent intent = new Intent(this, InfoUsuario.class);
        onClickAdapter(intent);
    }

    private void getShared (){
        SharedPreferences preference = getSharedPreferences("contactos", Context.MODE_PRIVATE);

        int cantidadContactos = preference.getInt("cantidadContactos", Context.MODE_PRIVATE);
        if (cantidadContactos == 0) {
            listUsuarios.add("Aun no tiene registros de contactos guardados");
        }
        else {
            for (int x=1; x <= cantidadContactos; x++){
                String text = "";
                text += preference.getString("nombre"+x,"") + " ";
                text += preference.getString("apellido"+x,"") + " - ";
                text += preference.getString("email"+x,"");
                listUsuarios.add(text);
            }
        }
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

    private void onClickAdapter(Intent intent){
        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id){
                String completo = (String)lvUsers.getItemAtPosition(position);
                String[] parts = completo.split(" "); // [0] nombre, [1] apellido, [2] email
                intent.putExtra("id", position+1);
                startActivity(intent);
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