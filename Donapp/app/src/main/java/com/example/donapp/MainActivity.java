package com.example.donapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

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

        // Instanciamos LogInActivity
        Intent login = new Intent(this, LogInActivity.class);
        startActivity(login);

    }

    public void irVerMasInformacion (View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.argentina.gob.ar/salud/donarsangre/faq"));
        startActivity(i);
    }

}