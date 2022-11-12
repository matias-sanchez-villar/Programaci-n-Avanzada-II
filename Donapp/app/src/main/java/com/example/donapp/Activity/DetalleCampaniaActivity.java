package com.example.donapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.donapp.Data.Campania.CampaniaRepository;
import com.example.donapp.Data.HistorialMedico.HistorialMedicoRepository;
import com.example.donapp.Data.Postulacion.PostulacionRepository;
import com.example.donapp.Data.Solicitud.SolicitudRepository;
import com.example.donapp.Entity.Campania;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.HistorialMedico;
import com.example.donapp.Entity.Postulacion;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.Categoria;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Enums.TipoUsuario;
import com.example.donapp.R;
import com.example.donapp.Util.DateUtil;
import com.example.donapp.Util.Toastable;

import java.util.Date;

public class DetalleCampaniaActivity extends AppCompatActivity {

    private TextView txtIdResponse, txtNombreResponse, txtFechaResponse, txtFechaFinResponse,
            txtLocalidadResponse, txtProvinciaResponse,
            txtDireccionResponse, txtCantDonantesResponse, txtCantDiasResponse,txtAlertaHistorialMedico;
    private Button btnVolver;
    private Button btnPostularse;
    private Button btnEliminarSolicitud;

    Campania campania;
    CampaniaRepository _campaniaRepository;
    PostulacionRepository _postulacionRepository;
    HistorialMedicoRepository _historialMedicoRepository = new HistorialMedicoRepository(this);
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_campania);
        _campaniaRepository = new CampaniaRepository(this);
        _postulacionRepository = new PostulacionRepository(this);

        bundle = getIntent().getExtras();

        int id = bundle.getInt("campania_id", 0);

        if(id != 0){
            this.campania = _campaniaRepository.selectEntity(new Campania(id));
        }
        fillProperties();
        setProperties();
        setListeners();
        getDBInfo();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }


    private void fillProperties() {
        txtIdResponse = (TextView)findViewById(R.id.txtIdResponse);
        txtNombreResponse = (TextView)findViewById(R.id.txtNombreResponse);
        txtFechaResponse = (TextView)findViewById(R.id.txtFechaResponse);
        txtFechaFinResponse = (TextView)findViewById(R.id.txtFechaFinResponse);
        txtLocalidadResponse = (TextView)findViewById(R.id.txtLocalidadResponse);
        txtProvinciaResponse = (TextView)findViewById(R.id.txtProvinciaResponse);
        txtDireccionResponse = (TextView)findViewById(R.id.txtDireccionResponse);
        txtCantDonantesResponse = (TextView)findViewById(R.id.txtCantDonantesResponse);
        txtCantDiasResponse = (TextView)findViewById(R.id.txtCantDiasResponse);
        txtAlertaHistorialMedico = (TextView) findViewById(R.id.txtAlertaHistorialMedicoDetalleCampania);
        btnVolver = (Button)findViewById(R.id.btnVolverDetalleCampania);
        btnPostularse = (Button) findViewById(R.id.btnPostularseDetalleCampania);
        btnEliminarSolicitud = (Button) findViewById(R.id.btnBorrarDetalleCampania);
    }

    private void setListeners() {
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnPostularse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postulate();
            }
        });

        btnEliminarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSolicitud();
            }
        });
    }

    private void setProperties() {
        txtNombreResponse.setText(campania.getNombreCampana());
        txtFechaResponse.setText(String.valueOf(campania.getFecha()));
        txtFechaFinResponse.setText(String.valueOf(campania.getFechaFin()));
        txtLocalidadResponse.setText(campania.getLocalidad().getNombre());
        txtProvinciaResponse.setText(campania.getProvincia().getNombre());
        txtDireccionResponse.setText(campania.getDireccion());
        txtCantDonantesResponse.setText(String.valueOf(campania.getCantDonantes()));
        txtCantDiasResponse.setText(String.valueOf(campania.getCantDias()));

        if(this.campania.getUsuario().getId() == GlobalPreferences.getLoggedUserId(this)){
            btnPostularse.setVisibility(View.GONE);
        } else {
            btnEliminarSolicitud.setVisibility(View.GONE);
            getDBInfo();
        }
    }

    public void postulate(){
        Date date = DateUtil.getActualDate();
        Usuario usuarioPostulado = new Usuario(GlobalPreferences.getLoggedUserId(this));
        Postulacion postulacion = new Postulacion(date, Categoria.CAMPANIA, usuarioPostulado, this.campania);

        if(_postulacionRepository.create(postulacion) != 0){
            Toastable.toast(this, "Postulación exitosa");
        }

        onBackPressed();
    }

    public void getDBInfo(){

        Postulacion existsPostulacion = new Postulacion(
                Categoria.CAMPANIA,
                new Usuario(GlobalPreferences.getLoggedUserId(this)),
                this.campania
        );

        if(_postulacionRepository.selectEntity(existsPostulacion) != null){
            btnPostularse.setEnabled(false);
            txtAlertaHistorialMedico.setText("Solo puede postularse a una donación cada 90 dias");
        }

        HistorialMedico historial = _historialMedicoRepository.selectEntity(
                new HistorialMedico(new Usuario(GlobalPreferences.getLoggedUserId(this)))
        );

        if(historial == null && GlobalPreferences.getLoggedTipoUsuario(this) != TipoUsuario.EMPRESA){
            btnPostularse.setEnabled(false);
            txtAlertaHistorialMedico.setText("DEBE COMPLETAR HISTORIAL MEDICO PARA POSTULARSE");
        }
    }

    public void deleteSolicitud(){


        if(_campaniaRepository.delete(this.campania.getId()) == StatusResponse.SUCCESS){
            Toastable.toast(this, "Solicitud Eliminada");
        } else{
            Toastable.toast(this, "Error");
        }

        Intent activity = new Intent(this, MisSolicitudesActivity.class);
        startActivity(activity);

    }

}
