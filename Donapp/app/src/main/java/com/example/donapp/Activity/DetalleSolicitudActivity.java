package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.donapp.Data.Localidad.LocalidadRepository;
import com.example.donapp.Data.Postulacion.PostulacionRepository;
import com.example.donapp.Data.Provincia.ProvinciaRepository;
import com.example.donapp.Data.Solicitud.SolicitudRepository;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.Postulacion;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.Categoria;
import com.example.donapp.R;
import com.example.donapp.Util.DateUtil;
import com.example.donapp.Util.Toastable;

import java.util.Calendar;
import java.util.Date;

public class DetalleSolicitudActivity extends AppCompatActivity {

    private TextView txtIdResponse, txtNombreResponse, txtApellidoResponse,
            txtFechaFinResponse, txtLocalidadResponse, txtProvinciaResponse,
            txtDireccionResponse, txtCantDonantesResponse, txtCantTipoSangre, txtCriticidad;
    private Button btnVolver;
    private Button btnPostularse;
    Solicitud solicitud;
    SolicitudRepository _solicitudRepository;
    PostulacionRepository _postulacionRepository;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        _solicitudRepository = new SolicitudRepository(this);
        _postulacionRepository = new PostulacionRepository(this);

        bundle = getIntent().getExtras();

        int id = bundle.getInt("solicitud_id", 0);

        if(id != 0){
            this.solicitud = _solicitudRepository.selectEntity(new Solicitud(id));
        }

        fillProperties();
        setProperties();
        setListeners();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void fillProperties(){
        txtNombreResponse = (TextView)findViewById(R.id.txtNombreResponseDetalleSolicitud);
        txtApellidoResponse = (TextView)findViewById(R.id.txtApellidoResponseDetalleSolicitud);
        txtFechaFinResponse = (TextView)findViewById(R.id.txtFechaFinResponseDetalleSolicitud);
        txtLocalidadResponse = (TextView)findViewById(R.id.txtLocalidadResponseDetalleSolicitud);
        txtProvinciaResponse = (TextView)findViewById(R.id.txtProvinciaResponseDetalleSolicitud);
        txtDireccionResponse = (TextView)findViewById(R.id.txtDireccionResponseDetalleSolicitud);
        txtCantDonantesResponse = (TextView)findViewById(R.id.txtCantDonantesResponseDetalleSolicitud);
        txtCantTipoSangre = (TextView)findViewById(R.id.txtCantTipoSangreDetalleSolicitud);
        txtCriticidad = (TextView) findViewById(R.id.txtCriticidadResponseDetalleSolicitud);
        btnVolver = (Button)findViewById(R.id.btnVolverDetalleSolicitud);
        btnPostularse = (Button) findViewById(R.id.btnPostularseDetalleSolicitud);
    }

    public void setListeners(){
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
    }

    public void setProperties(){
        ///seteamos las propiedades
        txtNombreResponse.setText(solicitud.getNombre());
        txtApellidoResponse.setText(solicitud.getApellido());
        txtFechaFinResponse.setText(solicitud.getAndroidFecha());
        txtLocalidadResponse.setText(solicitud.getLocalidad().getNombre());
        txtProvinciaResponse.setText(solicitud.getProvincia().getNombre());
        txtDireccionResponse.setText(solicitud.getDireccion());
        txtCantDonantesResponse.setText(String.valueOf(solicitud.getCantidadDonantes()));
        txtCantTipoSangre.setText(solicitud.getTipoDeSangre());
        txtCriticidad.setText(solicitud.getCriticidad().getDescripcion());

        if(this.solicitud.getUsuario().getId() == GlobalPreferences.getLoggedUserId(this)){
            btnPostularse.setVisibility(View.GONE);
        } else {
            getDBInfo();
        }
    }

    public void postulate(){
        Date date = DateUtil.getActualDate();
        Usuario usuarioPostulado = new Usuario(GlobalPreferences.getLoggedUserId(this));
        Postulacion postulacion = new Postulacion(date, Categoria.SOLICITUD, usuarioPostulado, this.solicitud);

        if(_postulacionRepository.create(postulacion) != 0){
            Toastable.toast(this, "Postulación exitosa");
        }

        onBackPressed();
    }

    public void getDBInfo(){

        Postulacion existsPostulacion = new Postulacion(
                Categoria.SOLICITUD,
                new Usuario(GlobalPreferences.getLoggedUserId(this)),
                this.solicitud
        );

        if(_postulacionRepository.selectEntity(existsPostulacion) != null){
            btnPostularse.setEnabled(false);
        }
    }

}