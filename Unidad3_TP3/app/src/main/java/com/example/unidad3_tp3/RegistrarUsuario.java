package com.example.unidad3_tp3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
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

                if(validarCamposVacios(String.valueOf(txtNombre.getText()), String.valueOf(txtCorreo.getText()),String.valueOf(txtpassword.getText()),String.valueOf(txtRepPassword.getText())))  {

                    if (validarPassword(String.valueOf(txtpassword.getText()), String.valueOf(txtRepPassword.getText()))) {
                        if((validaMailexistente(String.valueOf(txtCorreo.getText()))) && (validarFormatMail(String.valueOf(txtCorreo.getText())))) { //es TRUE existe

                            helper.abrirDB();
                            helper.insertarUsuario(String.valueOf(txtNombre.getText()),
                                    String.valueOf(txtCorreo.getText()),
                                    String.valueOf(txtpassword.getText()));
                            helper.cerarDB();
                            Toast.makeText(getApplicationContext(), "Registro almacenado con exito", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Mail incorrecto", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Password incorrecto",Toast.LENGTH_LONG).show();
                    }
                }
                txtNombre.setText("");
                txtCorreo.setText("");
                txtpassword.setText("");
                txtRepPassword.setText("");
            }
        });

    }

    public boolean validarFormatMail(String mail) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(mail);
        return mather.matches();
    }


    public boolean validaMailexistente(String mail) {
        boolean bandera=true;
        helper.abrirDB();
        Cursor cursor= helper.ConsultaMail(mail);
        try {
            if(cursor.getCount()>0){
                bandera= false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        helper.cerarDB();
        return bandera;
    }

    public boolean validarPassword(String pass, String repeatPass) {
        if(pass.equals(repeatPass)){
            return true;
        }
        return false;
    }

    public boolean validarCamposVacios(String nom, String mail, String pass, String repeatPass) {
        boolean bandera= true;
        if(nom.trim().isEmpty()){
            txtNombre.setError("Campo Nombre obligatorio");
            bandera=false;
        }
        if(mail.trim().isEmpty()){
            txtCorreo.setError("Campo Correo obligatorio");
            bandera=false;
        }
        if(pass.trim().isEmpty()){
            txtpassword.setError("Campo Password obligatorio");
            bandera=false;
        }
        if(repeatPass.trim().isEmpty()){
            txtRepPassword.setError("Campo Repetir password obligatorio");
            bandera=false;
        }
        return bandera;
    }

}