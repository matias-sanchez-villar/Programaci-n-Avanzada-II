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
    private String loggedUserMail;
    private String loggedUserName;
    private int userId;
    private boolean validUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombreUsuario = (EditText) findViewById(R.id.txtNombreUsuario);
        txtContasena = (EditText) findViewById(R.id.txtContasena);
    }

    public void login(View view){
        if(!ValidarUsuario.nombre(txtNombreUsuario.getText().toString())) {
            toast("Nombre de usuario incorrecto, intente nuevamente");
            return;
        }

        if(!ValidarUsuario.password(txtContasena.getText().toString())) {
            toast("Contraseña incorrecto, intente nuevamente");
            return;
        }

        buscarBD();

        if(this.loggedUserName != null && !this.loggedUserName.isEmpty()
                && this.loggedUserMail != null && !this.loggedUserMail.isEmpty()){
            this.activityParqueo();
        }
    }

    public void registrar(View view){
        Intent intente = new Intent(this, RegistrarUsuario.class);
        startActivity(intente);
    }

    public void buscarBD(){
        SQLiteOpenHelper admin = new AdminSQLite(this, "DB_TP3", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        String name = txtNombreUsuario.getText().toString();
        String password = txtContasena.getText().toString();
        Cursor fila = database.rawQuery
                ("select id, Nombre, Mail, password from usuarios where Nombre="
                        + Utilities.SQLString(name) +
                        " AND password=" + Utilities.SQLString(password), null);

        this.validUser = fila.moveToFirst();

        database.close();

        if(this.validUser) {
            this.userId = fila.getInt(0);
            this.loggedUserName = fila.getString(1);
            this.loggedUserMail = fila.getString(2);
        }
        else
            toast("El usuario ingresado no existe en nuestra base de datos");
    }

    public void activityUser(String id, String nombre, String Mail, String password){
        Intent intente = new Intent(this, RegistrarUsuario.class);
        intente.putExtra("id", id);
        intente.putExtra("Nombre", nombre);
        intente.putExtra("Mail", Mail);
        intente.putExtra("password", password);
        startActivity(intente);
    }

    public void activityParqueo(){
        Intent parqueo = new Intent(this, ParqueosDrawer.class);
        parqueo.putExtra("userId", this.userId);
        parqueo.putExtra("user", this.loggedUserName);
        parqueo.putExtra("userMail", this.loggedUserMail);
        startActivity(parqueo);
    }

    public void toast(String txt) {Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();}
}