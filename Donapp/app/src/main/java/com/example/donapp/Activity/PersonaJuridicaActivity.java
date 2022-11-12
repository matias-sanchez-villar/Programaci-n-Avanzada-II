package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.donapp.Data.Localidad.LocalidadRepository;
import com.example.donapp.Data.Provincia.ProvinciaRepository;
import com.example.donapp.Data.Usuario.UsuarioRepository;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Persona;
import com.example.donapp.Entity.PersonaJuridica;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.R;


public class PersonaJuridicaActivity extends AppCompatActivity {

    Button btnSiguiente;
    EditText nombreText;
    EditText cuilText;
    EditText telefonoText;
    EditText direccionJuridica;
    EditText horarioInicioText;
    EditText horarioFinText;
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
        setContentView(R.layout.activity_persona_juridica);

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
        btnSiguiente = (Button) findViewById(R.id.btnSiguienFromJuridica);
        nombreText = (EditText) findViewById(R.id.nombrePersonaJuridica);
        cuilText = (EditText) findViewById(R.id.cuilPersonaJuridica);
        telefonoText = (EditText) findViewById(R.id.telefonoPersonaJuridica);
        direccionJuridica = (EditText) findViewById(R.id.direccionJuridica);
        horarioInicioText = (EditText) findViewById(R.id.horarioInicioPersona);
        horarioFinText = (EditText) findViewById(R.id.horarioFinPersona);
        provinciaSpn = (Spinner) findViewById(R.id.spnProvinciaPersonaJuridica);
        localidadSpn = (Spinner) findViewById(R.id.spnLocalidadPersonaJuridica);
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

        if(validarCamposVacios() == true) {
            String nombre = nombreText.getText().toString();
            String cuil = cuilText.getText().toString();
            String direccion = direccionJuridica.getText().toString();
            int telefono = Integer.parseInt(telefonoText.getText().toString());
            String horarioInicio = horarioInicioText.getText().toString();
            String horarioFin = horarioFinText.getText().toString();

            persona = new PersonaJuridica(
                    nombre,
                    telefono,
                    direccion,
                    provinciaSelected,
                    localidadSelected,
                    cuil,
                    horarioInicio,
                    horarioFin);

            Intent usuarioActivity = new Intent(this, UsuarioActivity.class);
            usuarioActivity.putExtra("persona", this.persona);
            usuarioActivity.putExtra("isJuridica", this.persona.getJuridica());
            usuarioActivity.putExtra("tipoUsuario", this.tipoUsuario);
            startActivity(usuarioActivity);
        }
        else{
            Toast.makeText(
                    getApplicationContext(),
                    "Error!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public boolean validarCamposVacios() {
        boolean bandera= true;
        if (nombreText.getText().length() == 0){
            nombreText.setError("Campo obligatorio.");
            bandera=false;
        }
        if (cuilText.getText().length() == 0){
            cuilText.setError("Campo obligatorio.");
            bandera=false;
        }
        if (direccionJuridica.getText().length() == 0){
            direccionJuridica.setError("Campo obligatorio.");
            bandera=false;
        }
        if (telefonoText.getText().length() == 0){
            telefonoText.setError("Campo obligatorio.");
            bandera=false;
        }
        if (horarioInicioText.getText().length() == 0){
            horarioInicioText.setError("Campo obligatorio.");
            bandera=false;
        }
        if (horarioFinText.getText().length() == 0){
            horarioFinText.setError("Campo obligatorio.");
            bandera=false;
        }
        return bandera;
    }

    public void getDBInfo(){
        _provinciaRepository.selectAllForSpinner(provinciaSpn);
    }

    public void getLocalidadByProvincia(int provinciaId){
        _localidadRepository.selectAllForSpinnerByProvincia(localidadSpn, provinciaId);
    }
}