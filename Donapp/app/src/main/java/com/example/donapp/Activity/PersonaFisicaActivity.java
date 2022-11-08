package com.example.donapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.donapp.Data.Localidad.LocalidadRepository;
import com.example.donapp.Data.Provincia.ProvinciaRepository;
import com.example.donapp.Data.Usuario.UsuarioRepository;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Persona;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.R;
import com.example.donapp.Util.DateUtil;

import java.util.Date;

public class PersonaFisicaActivity extends AppCompatActivity {

    Button btnSiguiente;
    EditText nombreText;
    EditText apellidoText;
    EditText telefonoText;
    EditText fechaNacText;
    EditText direccionPersona;
    EditText dniPersonaText;
    Spinner provinciaSpn;
    Spinner localidadSpn;
    Provincia provinciaSelected;
    Localidad localidadSelected;
    UsuarioRepository _usuarioRepository;
    ProvinciaRepository _provinciaRepository;
    LocalidadRepository _localidadRepository;
    Persona persona;
    Bundle bundle;
    int tipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_fisica);
        _usuarioRepository = new UsuarioRepository(this);
        _provinciaRepository = new ProvinciaRepository(this);
        _localidadRepository = new LocalidadRepository(this);
        fillProperties();
        setListeners();
        getDBInfo();

        bundle = getIntent().getExtras();
        tipoUsuario = bundle.getInt("tipoUsuario");
    }

    public void fillProperties(){
        btnSiguiente = (Button) findViewById(R.id.btnUsuarioActivity);
        nombreText = (EditText) findViewById(R.id.nombrePersonaFisica);
        apellidoText = (EditText) findViewById(R.id.apellidoPersonaFisica);
        telefonoText = (EditText) findViewById(R.id.telefonoPersona);
        fechaNacText = (EditText) findViewById(R.id.fechaNacPersona);
        direccionPersona = (EditText) findViewById(R.id.direccionPersona);
        provinciaSpn = (Spinner) findViewById(R.id.spnProvinciaPersonaFisica);
        localidadSpn = (Spinner) findViewById(R.id.spnLocalidadPersonaFisica);
        dniPersonaText = (EditText) findViewById(R.id.dniPersona);
    }

    public void setListeners() {
        provinciaSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provinciaSelected = (Provincia) parent.getSelectedItem();
                // Seteamos a 0 los items que pueda tener;
                localidadSpn.setAdapter(null);
                getLocalidadByProvincia(provinciaSelected.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        localidadSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                localidadSelected = (Localidad) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUsuarioActivity();
            }
        });
    }

    public void goToUsuarioActivity(){

        String nombre = nombreText.getText().toString();
        String apellido = apellidoText.getText().toString();
        String direccion = direccionPersona.getText().toString();
        int telefono = Integer.parseInt(telefonoText.getText().toString());
        int dni = Integer.parseInt(dniPersonaText.getText().toString());
        Date fechaNac = DateUtil.convertToSqlDate(fechaNacText.getText().toString());

        persona = new PersonaFisica(
                nombre,
                telefono,
                direccion,
                provinciaSelected,
                localidadSelected,
                apellido,
                fechaNac,
                dni);

        Intent usuarioActivity = new Intent(this, UsuarioActivity.class);
        usuarioActivity.putExtra("persona", this.persona);
        usuarioActivity.putExtra("isJuridica", this.persona.getJuridica());
        usuarioActivity.putExtra("tipoUsuario", this.tipoUsuario);
        startActivity(usuarioActivity);
    }

    public void getDBInfo(){
        _provinciaRepository.selectAllForSpinner(provinciaSpn);
    }

    public void getLocalidadByProvincia(int provinciaId){
        _localidadRepository.selectAllForSpinnerByProvincia(localidadSpn, provinciaId);
    }
}