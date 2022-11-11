package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.donapp.Data.Postulantes.PostulantesRepository;
import com.example.donapp.Data.Solicitud.SolicitudRepository;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.R;

public class PostulanteActivity extends AppCompatActivity {

    PersonaFisica postulante;
    Button btnConfirmarDonación;
    Button btnVolver;
    TextView nombreTxt;
    TextView apellidoTxt;
    TextView dniTxt;
    Bundle bundle;

    SolicitudRepository _solicitudRepository;
    PostulantesRepository _postulantesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postulante);
        bundle = getIntent().getExtras();

        postulante = (PersonaFisica) bundle.getSerializable("postulante");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        instanceLayouts();
        setListeners();
        fillProperties();

        // SI LA POSTULACION TIENE ESTADO CONFIRMADO ENTONCES NO MOSTRAMOS BOTON, USAR POSTULACIONES REPOSITORY O VER OPTIMIZACIÓN
        if(false){
            btnConfirmarDonación.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void instanceLayouts(){
        btnConfirmarDonación = (Button) findViewById(R.id.btnConfirmarDonacionDetallePostulante);
        btnVolver = (Button) findViewById(R.id.btnVolverDetallePostulante);
        nombreTxt = (TextView) findViewById(R.id.txtNombreDetallePostulante);
        apellidoTxt = (TextView) findViewById(R.id.txtApellidoDetallePostulante);
        dniTxt = (TextView) findViewById(R.id.txtDniDetallePostulante);
    }

    public void setListeners(){
        btnConfirmarDonación.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void fillProperties(){
        nombreTxt.setText(postulante.getNombre());
        apellidoTxt.setText(postulante.getApellido());
        dniTxt.setText(String.valueOf(postulante.getDni()));
    }
}