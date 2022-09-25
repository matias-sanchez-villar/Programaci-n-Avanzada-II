package com.example.unidad3_tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombreUsuario, txtContasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombreUsuario = (EditText) findViewById(R.id.txtNombreUsuario);
        txtContasena = (EditText) findViewById(R.id.txtContasena);
    }

    public void login(View view){
        if(!ValidarUsuario.nombre(txtNombreUsuario.getText().toString())) {
            toast("Nombre de usuario incorrecto");
            return;
        }
        if(!ValidarUsuario.password(txtContasena.getText().toString())){
            toast("Contraseña incorrecta");
            return;
        }
        buscarBD();
    }

    public void registrar(View view){
        Intent intente = new Intent(this, RegistrarUsuario.class);
        startActivity(intente);
    }

    public void buscarBD(){
        SQLiteOpenHelper admin = new AdminSQLite(this, "db", null, 1);
        SQLiteDatabase database = admin.getReadableDatabase();

        String name = txtNombreUsuario.getText().toString();
        String password = txtContasena.getText().toString();
        Cursor fila = database.rawQuery
                ("select id, Nombre, Mail, password from usuarios where nombre="
                        + name + " password=" + password, null);
        if(fila.moveToFirst()) {
            database.close();
            activityUser(
                    fila.getString(0),
                    fila.getString(1),
                    fila.getString(2),
                    fila.getString(3)
            );
        }
        else
            toast("El usuario ingresado no existe en nuestra base de datos");
        database.close();
    }

    public void activityUser(String id, String nombre, String Mail, String password){
        Intent intente = new Intent(this, RegistrarUsuario.class);
        intente.putExtra("id", id);
        intente.putExtra("Nombre", nombre);
        intente.putExtra("Mail", Mail);
        intente.putExtra("password", password);
        startActivity(intente);
    }

    public void toast(String txt) {Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();}
}