package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.donapp.Data.Localidad.LocalidadRepository;
import com.example.donapp.Data.Provincia.ProvinciaRepository;
import com.example.donapp.Data.Solicitud.SolicitudRepository;
import com.example.donapp.R;

public class DetalleSolicitud extends AppCompatActivity {

    private TextView txtIdResponse, txtNombreResponse, txtApellidoResponse,
            txtFechaFinResponse, txtLocalidadResponse, txtProvinciaResponse,
            txtDireccionResponse, txtCantDonantesResponse, txtCantTipoSangre;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud);
        fillProperties();
        setingsPropirties();
    }

    public void fillProperties(){
        txtIdResponse = (TextView)findViewById(R.id.txtIdResponse);
        txtNombreResponse = (TextView)findViewById(R.id.txtNombreResponse);
        txtApellidoResponse = (TextView)findViewById(R.id.txtApellidoResponse);
        txtFechaFinResponse = (TextView)findViewById(R.id.txtFechaFinResponse);
        txtLocalidadResponse = (TextView)findViewById(R.id.txtLocalidadResponse);
        txtProvinciaResponse = (TextView)findViewById(R.id.txtProvinciaResponse);
        txtDireccionResponse = (TextView)findViewById(R.id.txtDireccionResponse);
        txtCantDonantesResponse = (TextView)findViewById(R.id.txtCantDonantesResponse);
        txtCantTipoSangre = (TextView)findViewById(R.id.txtCantTipoSangre);
        btnVolver = (Button)findViewById(R.id.btnVolver);
    }

    public void setingsPropirties(){
        ///seteamos las propiedades
    }

    public void onClickBtnVolver(View view){
        //Volvemos al activity anterior
    }

}