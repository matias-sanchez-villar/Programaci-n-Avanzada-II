package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donapp.Data.Criticidad.CriticidadRepository;
import com.example.donapp.Data.Localidad.LocalidadRepository;
import com.example.donapp.Data.Provincia.ProvinciaRepository;
import com.example.donapp.Data.Solicitud.SolicitudRepository;
import com.example.donapp.Entity.Criticidad;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.Estado;
import com.example.donapp.Enums.EstadoSolicitud;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.R;
import com.example.donapp.Util.DateUtil;
import com.example.donapp.Util.Validar;

import java.util.ArrayList;

public class AltaSolicitudActivity extends AppCompatActivity {

    private EditText txtNombre, txtApellido, txtFecha, txtDireccion, txtCantDonante;
    private Spinner spnLocalidad, spnProvincia, spnTipoSangre, spnCriticidad;
    private Button btnGuardar;
    private Button btnCancelarSolicitud;
    private ProvinciaRepository _provinciaRepository;
    private LocalidadRepository _localidadRepository;
    private Provincia provinciaSelected;
    private Localidad localidadSelected;
    private Criticidad criticidadSelected;
    private SolicitudRepository solicitudRepository;
    private CriticidadRepository _criticidadRepository;
    private Solicitud solicitudForUpdate;

    ArrayList<String> tiposDeSangre = new ArrayList<String>(){{add("A"); add("B"); add("AB"); add("O");}};

    Bundle bundle;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_solicitud);
        bundle = getIntent().getExtras();
        solicitudForUpdate = (Solicitud) bundle.getSerializable("solicitudUpdate");

        fillProperties();
        setListeners();
        getDBInfo();

        // Si no es nueva, hacemos un update
        if(!solicitudForUpdate.isNew()){
            fillComponents(solicitudForUpdate);
            title.setText("Modificar solicitud");
        } else{
            btnCancelarSolicitud.setVisibility(View.GONE);
        }

    }

    public void onClickBtnGuardar(){
        // if (validateData()) {
            setSolicitud(EstadoSolicitud.ACTIVA);
            int response;
            if(solicitudForUpdate.isNew()){
                if(solicitudRepository.create(solicitudForUpdate) != 0){
                 toast("Solicitud creada");
                }else toast("Error");
            } else {
                if(solicitudRepository.update(solicitudForUpdate) != StatusResponse.FAIL){
                  toast("Solicitud modificada");
                } else toast("Error");
            }
            goToMisSolicitudes();
       // }
    }

    public void fillProperties(){
        solicitudRepository = new SolicitudRepository(this);
        _provinciaRepository = new ProvinciaRepository(this);
        _localidadRepository = new LocalidadRepository(this);
        _criticidadRepository = new CriticidadRepository(this);

        txtNombre = (EditText)findViewById(R.id.txtNombreAltaSolicitud);
        txtApellido = (EditText)findViewById(R.id.txtApellidoAltaSolicitud);
        txtFecha = (EditText)findViewById(R.id.txtFechaAltaSolicitud);
        txtDireccion = (EditText)findViewById(R.id.txtDireccionAltaSolicitud);
        txtCantDonante = (EditText)findViewById(R.id.txtCantDonanteAltaSolicitud);
        spnLocalidad = (Spinner)findViewById(R.id.spnLocalidadAltaSolicitud);
        spnProvincia = (Spinner)findViewById(R.id.spnProvinciaAltaSolicitud);
        spnTipoSangre = (Spinner)findViewById(R.id.spnTipoSangreAltaSolicitud);
        spnCriticidad = (Spinner) findViewById(R.id.spnTipoCriticidadAltaSolicitud);
        btnGuardar = (Button)findViewById(R.id.btnGuardarAltaSolicitud);
        btnCancelarSolicitud = (Button) findViewById(R.id.btnCancelarAltaSolicitud);
        title = (TextView) findViewById(R.id.altaSolicitudTitle);
    }

    public void setSolicitud(EstadoSolicitud estado){
        if(solicitudForUpdate.isNew()){
            this.solicitudForUpdate.setEstado(estado);
            this.solicitudForUpdate.setUsuario(new Usuario(GlobalPreferences.getLoggedUserId(this)));
            this.solicitudForUpdate.setAutomaticCodigo();
        }
        this.solicitudForUpdate.setNombre(txtNombre.getText().toString());
        this.solicitudForUpdate.setApellido(txtApellido.getText().toString());
        this.solicitudForUpdate.setFecha(DateUtil.convertToSqlDate(txtFecha.getText().toString()));
        this.solicitudForUpdate.setProvincia(new Provincia(provinciaSelected.getId()));
        this.solicitudForUpdate.setLocalidad(new Localidad(localidadSelected.getId()));
        this.solicitudForUpdate.setDireccion(txtDireccion.getText().toString());
        this.solicitudForUpdate.setCantidadDonantes(Integer.parseInt(txtCantDonante.getText().toString()));
        this.solicitudForUpdate.setCriticidad(new Criticidad(criticidadSelected.getId()));
    }

    public void setListeners() {
        spnProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provinciaSelected = (Provincia) parent.getSelectedItem();
                // Seteamos a 0 los items que pueda tener;
                spnLocalidad.setAdapter(null);
                getLocalidadByProvincia(provinciaSelected.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnLocalidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                localidadSelected = (Localidad) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnCriticidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                criticidadSelected = (Criticidad) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnTipoSangre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                solicitudForUpdate.setTipoDeSangre((String) parent.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnGuardar();
            }
        });

        btnCancelarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarSolicitud();
            }
        });
    }

    public void getDBInfo(){
        _provinciaRepository.selectAllForSpinner(spnProvincia);
        _criticidadRepository.selectAllForSpinner(spnCriticidad);

        //Fill spnTiposDeSangre
        ArrayAdapter<String> tiposDeSangreAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                tiposDeSangre);
        spnTipoSangre.setAdapter(tiposDeSangreAdapter);
    }

    public void getLocalidadByProvincia(int provinciaId){
        _localidadRepository.selectAllForSpinnerByProvincia(spnLocalidad, provinciaId);
    }

    public boolean validateData(){
        if (!Validar.texto(txtNombre.getText().toString())) {
            toast("Error en el campo Nombre, completelo nuevamente");
            return false;
        }
        if (!Validar.texto(txtApellido.getText().toString())) {
            toast("Error en el campo Apellido, completelo nuevamente");
            return false;
        }
        if (!Validar.texto(spnLocalidad.getSelectedItem().toString())) {
            toast("Error en el campo Localidad, completelo nuevamente");
            return false;
        }
        if (!Validar.texto(spnProvincia.getSelectedItem().toString())) {
            toast("Error en el campo Provincia, completelo nuevamente");
            return false;
        }
        if (!Validar.textoConEspaciosNumeros(txtDireccion.getText().toString())) {
            toast("Error en el campo Direccion, completelo nuevamente");
            return false;
        }
        if (!Validar.date(txtFecha.getText().toString())) {
            toast("Error en el campo Fecha, completelo nuevamente");
            return false;
        }
        if (!Validar.numeros(txtCantDonante.getText().toString())) {
            toast("Error en el campo Cantidad de donantes, completelo nuevamente");
            return false;
        }
        return  true;
    }

    public void fillComponents(Solicitud solicitud){
        txtNombre.setText(solicitud.getNombre());
        txtApellido.setText(solicitud.getApellido());
        txtFecha.setText(solicitud.getFecha().toString());
        txtDireccion.setText(solicitud.getDireccion());
        txtCantDonante.setText(String.valueOf(solicitud.getCantidadDonantes()));

    }

    public void goToMisSolicitudes(){
        Intent misSolicitudes = new Intent(this, MisSolicitudesActivity.class);
        startActivity(misSolicitudes);
    }

    public void cancelarSolicitud(){
        setSolicitud(EstadoSolicitud.CANCELADA);
        if(solicitudRepository.update(solicitudForUpdate) != StatusResponse.FAIL){
            toast("Solicitud cancelada");
        } else{
            toast("Error");
        }
    }

    public void toast(String txt) {Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();}
}