package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.donapp.Data.Localidad.LocalidadRepository;
import com.example.donapp.Data.Provincia.ProvinciaRepository;
import com.example.donapp.Data.Solicitud.SolicitudRepository;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.R;
import com.example.donapp.Util.Validar;

public class AltaSolicitud extends AppCompatActivity {

    private EditText txtNombre, txtApellido, txtFecha, txtDireccion, txtCantDonante;
    private Spinner spnLocalidad, spnProvincia, spnTipoSangre;
    private Button btnGuardar;
    private ProvinciaRepository _provinciaRepository;
    private LocalidadRepository _localidadRepository;
    private Provincia provinciaSelected;
    private Localidad localidadSelected;
    private SolicitudRepository solicitudRepository;
    private Solicitud solicitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_solicitud);
        fillProperties();
        setListeners();
        getDBInfo();
    }

    public void onClickBtnGuardar(View view){
        if (validateData())
            return;

    }

    public void fillProperties(){
        solicitudRepository = new SolicitudRepository(this);
        _provinciaRepository = new ProvinciaRepository(this);
        _localidadRepository = new LocalidadRepository(this);

        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        txtFecha = (EditText)findViewById(R.id.txtFecha);
        txtDireccion = (EditText)findViewById(R.id.txtDireccion);
        txtCantDonante = (EditText)findViewById(R.id.txtCantDonante);
        spnLocalidad = (Spinner)findViewById(R.id.spnLocalidad);
        spnProvincia = (Spinner)findViewById(R.id.spnProvincia);
        spnTipoSangre = (Spinner)findViewById(R.id.spnTipoSangre);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
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
    }

    public void getDBInfo(){
        _provinciaRepository.selectAllForSpinner(spnProvincia);
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

    public void toast(String txt) {Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();}
}