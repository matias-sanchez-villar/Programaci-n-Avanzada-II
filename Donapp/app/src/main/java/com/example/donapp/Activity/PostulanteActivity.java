package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.donapp.Data.Campania.CampaniaRepository;
import com.example.donapp.Data.Postulacion.PostulacionRepository;
import com.example.donapp.Data.Postulantes.PostulantesRepository;
import com.example.donapp.Data.Solicitud.SolicitudRepository;
import com.example.donapp.Entity.BancoSangre;
import com.example.donapp.Entity.Campania;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Entity.Postulacion;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Enums.Categoria;
import com.example.donapp.Enums.EstadoPostulacion;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.R;
import com.example.donapp.Util.Toastable;

public class PostulanteActivity extends AppCompatActivity {

    PersonaFisica postulante;
    Solicitud solicitud;
    Postulacion postulacion;
    Campania campania;
    BancoSangre bancoDeSangre;
    Button btnConfirmarDonación;
    Button btnVolver;
    TextView nombreTxt;
    TextView apellidoTxt;
    TextView dniTxt;
    Bundle bundle;

    SolicitudRepository _solicitudRepository = new SolicitudRepository(this);
    PostulantesRepository _postulantesRepository = new PostulantesRepository(this);
    PostulacionRepository _postulacionRepository = new PostulacionRepository(this);
    CampaniaRepository _campaniaRepository = new CampaniaRepository(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postulante);
        bundle = getIntent().getExtras();

        postulante = (PersonaFisica) bundle.getSerializable("postulante");
        solicitud = (Solicitud) bundle.getSerializable("solicitud");
        campania = (Campania) bundle.getSerializable("campania");
        bancoDeSangre = (BancoSangre) bundle.getSerializable("bancoDeSangre");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        instanceLayouts();
        setListeners();
        fillProperties();
        getDBInfo();

        // SI LA POSTULACION TIENE ESTADO CONFIRMADO ENTONCES NO MOSTRAMOS BOTON, USAR POSTULACIONES REPOSITORY O VER OPTIMIZACIÓN
        // if(false){
          //  btnConfirmarDonación.setVisibility(View.GONE);
       // }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void instanceLayouts(){
        btnConfirmarDonación = (Button) findViewById(R.id.btnConfirmarDonacionDetallePostulante);
        btnVolver = (Button) findViewById(R.id.btnVolverDetallePostulante);
        nombreTxt = (TextView) findViewById(R.id.txtNombreResponseDetallePostulante);
        apellidoTxt = (TextView) findViewById(R.id.txtApellidoResponseDetallePostulante);
        dniTxt = (TextView) findViewById(R.id.txtDniResponseDetallePostulante);
    }

    public void setListeners(){
        btnConfirmarDonación.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarDonacion();
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

    public void confirmarDonacion(){

        StatusResponse responseRegistroPostulado;
        if(this.solicitud != null){
            this.solicitud.confirmarDonacion();
            responseRegistroPostulado = _solicitudRepository.update(this.solicitud);
        } else {
            this.campania.confirmarDonacion();
            responseRegistroPostulado = _campaniaRepository.update(this.campania);
        }

        // TODO: MEJORAR LOGICA

        StatusResponse responsePostulacion = _postulacionRepository.update(postulacion);


        if(responseRegistroPostulado == StatusResponse.SUCCESS){
            Toastable.toast(this, "Donación confirmada");
        } else{
            Toastable.toast(this, "Error al actualizar registro postulado");
        }

        if(responsePostulacion != StatusResponse.SUCCESS){
            Toastable.toast(this, "Error al actualizar postulación");
        }

        Intent postulantesActivity = new Intent(this, DetallePostulantesActivity.class);
        postulantesActivity.putExtra("categoria", getActualCategoria().ordinal());
        postulantesActivity.putExtra("id_registro", getRegistroInt());
        if(getActualCategoria() == Categoria.SOLICITUD){
            postulantesActivity.putExtra("solicitud", solicitud);
        }else {
            postulantesActivity.putExtra("campania", campania);
        }

        startActivity(postulantesActivity);
    }

    public Categoria getActualCategoria(){
        return solicitud != null
                ? Categoria.SOLICITUD
                : campania != null
                ? Categoria.CAMPANIA
                : Categoria.BANCO;
    }

    public int getRegistroInt(){
        return  solicitud != null
                ? solicitud.getId()
                : campania != null
                ? campania.getId()
                : 0;
    }

    public void getDBInfo(){
        if(getActualCategoria() == Categoria.SOLICITUD){
          postulacion = _postulacionRepository.selectEntityByRegistroAndCategoria(
                  new Postulacion(this.solicitud, Categoria.SOLICITUD), postulante.getId()
          );
        } else {
            postulacion = _postulacionRepository.selectEntityByRegistroAndCategoria(
                    new Postulacion(this.campania, Categoria.CAMPANIA), postulante.getId());
        }

        if(postulacion.getEstado() == EstadoPostulacion.CONFIRMADO){
            btnConfirmarDonación.setVisibility(View.GONE);
        }
    }
}