package com.example.unidad3_tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrarUsuario extends AppCompatActivity {

    Button btnGrabar;
    EditText txtNombre, txtCorreo, txtpassword, txtRepPassword;

    AdminSQLite helper=new AdminSQLite(this, "DB_TP3",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        btnGrabar=(Button) findViewById(R.id.btnRegistrarUsu);
        txtNombre=(EditText) findViewById(R.id.txtnomUsu);
        txtCorreo=(EditText) findViewById(R.id.txtmailUsu);
        txtpassword=(EditText) findViewById(R.id.txtPassUsu);
        txtRepPassword=(EditText) findViewById(R.id.txtRepPassUsu);


        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validarUsuario();

                //validaciones
                if(!validaNombre(txtNombre.getText().toString())) toast("Nombre incorrecto");

                /*if(!validaMail(txtCorreo.getText().toString())) toast("Mail incorrecto");*/


                if(!validaMailexistente(txtCorreo.getText().toString())){ //es TRUE existe


                helper.abrirDB();
                helper.insertarUsuario(String.valueOf(txtNombre.getText()),
                        String.valueOf(txtCorreo.getText()),
                        String.valueOf(txtpassword.getText()));
                helper.cerarDB();
                toast("Registro almacenado con exito");
                    Intent i= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                }
                else toast("No se ha podido registrar el usuario");

                txtNombre.setText("");
                txtCorreo.setText("");
                txtpassword.setText("");
                txtRepPassword.setText("");
            }
        });

    }

    public boolean validaMail(String mail) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(mail);
        return mather.matches();
    }

    public boolean validaMailexistente(String mail) {
        boolean existe=false;
        Cursor cursor= helper.ConsultaMail(mail);
        if(cursor.getCount()>0){
            existe= true;
            Toast.makeText(getApplicationContext(),"Mail registrado, ingrese un nuevo correo",Toast.LENGTH_LONG).show();
        }else{
            existe = false;
        }
        return existe;
    }

    public boolean validaNombre(String nombre) {
        Pattern pattern = Pattern.compile("^[A-Za-z]{1,20}$");
        Matcher mather = pattern.matcher(nombre);
        return mather.matches();
    }

    public void validarUsuario (){
        if(!ValidarUsuario.nombre(txtNombre.getText().toString())) {
            toast("Nombre de usuario incorrecto, intente nuevamente");
            return;
        }

        if(!ValidarUsuario.email(txtCorreo.getText().toString())) {
            toast("Correo incorrecto, intente nuevamente");
            return;
        }

        if(!ValidarUsuario.password(txtpassword.getText().toString())) {
            toast("Contraseña incorrecto, intente nuevamente");
            return;
        }

        if (txtRepPassword.getText().toString() != txtpassword.getText().toString()){
            toast("Contraseña incorrecto, intente nuevamente");
            return;
        }
    }

    /*
    public boolean validarPassword(String pass, String repeatPass) {
        boolean bandera=false;
        if(pass.equals(repeatPass)){
            bandera= true;
        }else{
            Toast.makeText(getApplicationContext(),"Password incorrecto",Toast.LENGTH_LONG).show();
        }
        return bandera;
    }*/

    public void toast(String txt) {Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();}
}