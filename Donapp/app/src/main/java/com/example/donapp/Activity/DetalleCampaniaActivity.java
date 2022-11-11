package com.example.donapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.donapp.Data.Campania.CampaniaRepository;
import com.example.donapp.Data.Solicitud.SolicitudRepository;
import com.example.donapp.Entity.Campania;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.R;

public class DetalleCampaniaActivity extends AppCompatActivity {

    private TextView txtIdResponse, txtNombreResponse, txtFechaResponse,
            txtLocalidadResponse, txtProvinciaResponse,
            txtDireccionResponse, txtCantSolicitantesResponse, txtCantDiasResponse;
    private Button btnVolver;
    Campania campania;
    CampaniaRepository _campaniaRepository;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_campania);
        _campaniaRepository = new CampaniaRepository(this);

        bundle = getIntent().getExtras();

        int id = bundle.getInt("solicitud_id", 0);

        if(id != 0){
            this.campania = _campaniaRepository.selectEntity(new Campania(id));
        }
        fillProperties();
        setProperties();
        setListeners();
    }

    private void setListeners() {
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void fillProperties() {
        txtIdResponse = (TextView)findViewById(R.id.txtIdResponse);
        txtNombreResponse = (TextView)findViewById(R.id.txtNombreResponse);
        txtFechaResponse = (TextView)findViewById(R.id.txtFechaResponse);
        txtLocalidadResponse = (TextView)findViewById(R.id.txtLocalidadResponse);
        txtProvinciaResponse = (TextView)findViewById(R.id.txtProvinciaResponse);
        txtDireccionResponse = (TextView)findViewById(R.id.txtDireccionResponse);
        txtCantSolicitantesResponse = (TextView)findViewById(R.id.txtCantSolicitantesResponse);
        txtCantDiasResponse = (TextView)findViewById(R.id.txtCantDiasResponse);
        btnVolver = (Button)findViewById(R.id.btnVolver);
    }

    private void setProperties() {
        txtIdResponse.setText(campania.getId());
        txtNombreResponse.setText(campania.getNombreCampana());
        txtFechaResponse.setText(String.valueOf(campania.getFecha()));
        txtLocalidadResponse.setText(campania.getLocalidad().getNombre());
        txtProvinciaResponse.setText(campania.getProvincia().getNombre());
        txtDireccionResponse.setText(campania.getDireccion());
        txtCantSolicitantesResponse.setText(String.valueOf(campania.getCantSolicitante()));
        txtCantDiasResponse.setText(String.valueOf(campania.getCantDias()));
    }

}
