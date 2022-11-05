package com.example.donapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.donapp.Activity.LogInActivity;
import com.example.donapp.Data.Usuario.UsuarioRepository;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.fragment_listar__campana_); //para probar
        //setContentView(R.layout.fragment_alta__campana_);//para probar
        //setContentView(R.layout.fragment_detalle__campana_);

        // Instanciamos LogInActivity
        Intent login = new Intent(this, LogInActivity.class);
        startActivity(login);
    }
}