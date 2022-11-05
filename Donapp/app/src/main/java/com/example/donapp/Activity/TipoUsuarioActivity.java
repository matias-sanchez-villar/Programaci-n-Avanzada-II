package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.donapp.R;

public class TipoUsuarioActivity extends AppCompatActivity {

    private Button btnSolicitante;
    private Button btnDonante;
    private Button btnEmpresa;
    private Button btnInstitucion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_usuario);
        fillObjects();

        //Enlace entre botones y click. Otra forma se puede
        // configurar directamente el onClick en la vista
        btnSolicitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarPersonaFisica();
            }
        });

        btnDonante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarPersonaFisica();
            }
        });

        btnEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarPersonaJuridica();
            }
        });

        btnInstitucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarPersonaJuridica();
            }
        });

    }

    public void registrarPersonaFisica(){
        Intent personaFisicaFormulario = new Intent(this, PersonaFisicaActivity.class);
        startActivity(personaFisicaFormulario);
    }

    public void registrarPersonaJuridica(){
        Intent personaJuridicaFormulario = new Intent(this, PersonaJuridicaActivity.class);
        startActivity(personaJuridicaFormulario);
    }

    public void fillObjects(){
        btnSolicitante = (Button) findViewById(R.id.agregarSolicitante);
        btnDonante = (Button) findViewById(R.id.agregarDonante);
        btnEmpresa = (Button) findViewById(R.id.agregarEmpresa);
        btnInstitucion = (Button) findViewById(R.id.agregarInstitucion);
    }
}