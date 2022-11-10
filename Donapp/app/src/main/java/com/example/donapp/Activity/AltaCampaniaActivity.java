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
import com.example.donapp.Entity.Campana;

import com.example.donapp.Entity.Usuario;
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
    private Campana campana;


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

        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtFecha = (EditText)findViewById(R.id.txtFecha);
        txtDireccion = (EditText)findViewById(R.id.txtDireccion);
        txtCantS = (EditText)findViewById(R.id.textCantS);
        spnLocalidad = (Spinner)findViewById(R.id.spnLocalidad);
        spnProvincia = (Spinner)findViewById(R.id.spnProvincia);
        txtCantS = (EditText)findViewById((R.id.textCantS));
        txtCantDias = (EditText)findViewById((R.id.textCantDias));
        checkTerminos = (CheckBox)findViewById(R.id.checkTerminos);
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

    private void registrar() {
        String nombre = txtNombre.getText().toString();
        Date fecha = DateUtil.convertToSqlDate(txtFecha.getText().toString());
        String direccion = txtDireccion.getText().toString();
        int cantSolicitantes = Integer.parseInt(txtCantS.getText().toString());
        int cantDias = Integer.parseInt(txtCantDias.getText().toString());

        //Obtener el ID de Usuario sesion, creo que con el SharedPreferences
        Usuario UsuarioEmpresa = new Usuario();


        if(validarFechayZona(fecha, localidadSelected) && (checkTerminos.isChecked())){

            // Importate: Falta obtener el Usuario de SESSION!!!
            Campana newCampana = new Campana(
                    nombre,
                    fecha,
                    direccion,
                    localidadSelected,
                    provinciaSelected,
                    cantSolicitantes,
                    cantDias,
                    UsuarioEmpresa);
            campaniaRepository.create(newCampana);
            Toast.makeText(
                    getApplicationContext(),
                    "Registro exitoso",
                    Toast.LENGTH_LONG).show();
            backToLoginActivity();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Error al registrar Campaña",
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
