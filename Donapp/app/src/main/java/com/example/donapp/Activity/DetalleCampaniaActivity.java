package com.example.donapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.donapp.R;

public class DetalleCampaniaActivity extends AppCompatActivity {

    private TextView txtIdResponse, txtNombreResponse, txtFechaResponse,
            txtLocalidadResponse, txtProvinciaResponse,
            txtDireccionResponse, txtCantSolicitantesResponse, txtCantDiasResponse;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_campania);
        fillProperties();
        setingsPropirties();
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

    private void setingsPropirties() {
    }

    public void onClickBtnVolver(View view){
        //Volvemos al activity anterior
    }
}
