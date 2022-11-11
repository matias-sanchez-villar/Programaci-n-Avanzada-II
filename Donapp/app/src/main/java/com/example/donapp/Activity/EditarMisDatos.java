package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.donapp.R;

public class EditarMisDatos extends AppCompatActivity {

    EditText txtNombre, txtEmail, txtPassword;
    Button btnModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_mis_datos);

        fillProperties();
    }

    public void onClickBtnModificar(View view){

    }

    public void fillProperties(){
        txtNombre = (EditText)findViewById(R.id.txtNombreUsuarioEMD);
        txtEmail = (EditText)findViewById(R.id.txtEmailEMD);
        txtPassword = (EditText)findViewById(R.id.txtPasswordEMD);
        btnModificar = (Button) findViewById(R.id.btnModificarEMD);
    }

}