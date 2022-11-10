package com.example.donapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.donapp.Data.Campania.CampaniaRepository;
import com.example.donapp.Data.Localidad.LocalidadRepository;
import com.example.donapp.Data.Provincia.ProvinciaRepository;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Campania;

import com.example.donapp.R;
import com.example.donapp.Util.DateUtil;

import java.util.Date;

public class AltaCampaniaActivity extends AppCompatActivity {

    private EditText txtNombre, txtFecha, txtDireccion, txtCantS, txtCantDias;
    private Spinner spnLocalidad, spnProvincia;
    private CheckBox checkTerminos;
    private Button btnGuardar;
    private ProvinciaRepository _provinciaRepository;
    private LocalidadRepository _localidadRepository;
    private Provincia provinciaSelected;
    private Localidad localidadSelected;
    private CampaniaRepository campaniaRepository;
    private Campania campania;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_campania);
        fillProperties();
        setListeners();
        getDBInfo();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    private void fillProperties() {
        campaniaRepository = new CampaniaRepository(this);
        _provinciaRepository = new ProvinciaRepository(this);
        _localidadRepository = new LocalidadRepository(this);

        txtNombre = (EditText)findViewById(R.id.txtNombreAltaCampania);
        txtFecha = (EditText)findViewById(R.id.txtFechaAltaCampania);
        txtDireccion = (EditText)findViewById(R.id.txtDireccionAltaCampania);
        txtCantS = (EditText)findViewById(R.id.txtCantDiasAltaCampania);
        spnLocalidad = (Spinner)findViewById(R.id.spnLocalidadAltaCampania);
        spnProvincia = (Spinner)findViewById(R.id.spnProvinciaAltaCampania);
        txtCantDias = (EditText)findViewById((R.id.txtCantDiasAltaCampania));
        checkTerminos = (CheckBox)findViewById(R.id.checkTerminosAltaCampania);
        btnGuardar = (Button)findViewById(R.id.btnGuardarAltaCampania);

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

    private void registrar() {
        String nombre = txtNombre.getText().toString();
        Date fecha = DateUtil.convertToSqlDate(txtFecha.getText().toString());
        String direccion = txtDireccion.getText().toString();
        int cantSolicitantes = Integer.parseInt(txtCantS.getText().toString());
        int cantDias = Integer.parseInt(txtCantDias.getText().toString());

        int response;

        if(validarFechayZona(fecha, localidadSelected) && (checkTerminos.isChecked())){

            // Solucionar Constructor
            /*Campana newCampana = new Campana(
                    nombre,
                    fecha,
                    direccion,
                    localidadSelected,
                    provinciaSelected,
                    cantSolicitantes,
                    cantDias);
            campaniaRepository.create(newCampana);*/
            Toast.makeText(
                    getApplicationContext(),
                    "Registro exitoso",
                    Toast.LENGTH_LONG).show();
            backToLoginActivity();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Error al registrar usuario",
                    Toast.LENGTH_LONG).show();
        }
    }

    private boolean validarFechayZona(Date fecha, Localidad localidadSelected) {
        return true; //hacer metodo validar
    }

    public void backToLoginActivity(){
        Intent loginActivity = new Intent(this, LogInActivity.class);
        startActivity(loginActivity);
    }

}
