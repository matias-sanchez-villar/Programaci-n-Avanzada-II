package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.donapp.Data.Persona.PersonaRepository;
import com.example.donapp.Data.Usuario.UsuarioRepository;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Entity.PersonaJuridica;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Enums.TipoUsuario;
import com.example.donapp.R;

public class UsuarioActivity extends AppCompatActivity {

    EditText nombreUsuarioText;
    EditText mailUsuarioText;
    EditText contraseniaText;
    EditText confContraseniaText;
    Button btnRegistrar;
    UsuarioRepository _usuarioRepository;
    PersonaRepository _personaRepository;

    Bundle bundle;
    PersonaFisica personaFisica;
    PersonaJuridica personaJuridica;
    Usuario usuario;
    int tipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bundle = getIntent().getExtras();
        if(bundle.getBoolean("isJuridica")){
            personaJuridica = (PersonaJuridica) bundle.getSerializable("persona");
        } else {
            personaFisica = (PersonaFisica) bundle.getSerializable("persona");
        }
        tipoUsuario = bundle.getInt("tipoUsuario");
        fillProperties();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void fillProperties(){
        nombreUsuarioText = (EditText) findViewById(R.id.nombreUsuario);
        mailUsuarioText = (EditText) findViewById(R.id.emailUsuario);
        contraseniaText = (EditText) findViewById(R.id.contraseniaUsuario);
        confContraseniaText = (EditText) findViewById(R.id.confirmarContraseniaUsuario);
        btnRegistrar = (Button) findViewById(R.id.registrarUsuario);
        _usuarioRepository = new UsuarioRepository(this);
        _personaRepository = new PersonaRepository(this);
    }

    public void registrar(){
        String nombreUsuario = nombreUsuarioText.getText().toString();
        String mail = mailUsuarioText.getText().toString();
        String contrasenia = contraseniaText.getText().toString();
        String confContrasenia = confContraseniaText.getText().toString();
        int response;

        if(validarCamposVacios() == true){
            Usuario newUsuario = new Usuario(
                    nombreUsuario,
                    mail,
                    contrasenia,
                    TipoUsuario.getTipoUsuario(tipoUsuario));

            if(personaJuridica != null){
                response = _personaRepository.createPersonaJuridica(personaJuridica);
                personaJuridica.setId(response);
                newUsuario.setPersona(personaJuridica);
            } else {
                response = _personaRepository.createPersonaFisica(personaFisica);
                personaFisica.setId(response);
                newUsuario.setPersona(personaFisica);
            }

            if(response != StatusResponse.FAIL.ordinal()){
                _usuarioRepository.create(newUsuario);

                Toast.makeText(
                        getApplicationContext(),
                        "Registro exitoso",
                        Toast.LENGTH_LONG).show();
                backToLoginActivity();
            }
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Error al registrar usuario",
                    Toast.LENGTH_LONG).show();
        }
    }

    /*public boolean validarUsuario(
            String nombreUsuario,
            String contrasenia,
            String confContrasenia){
        return true;
    }*/
    public boolean validarCamposVacios() {
        boolean bandera= true;
        if (nombreUsuarioText.getText().length() == 0){
            nombreUsuarioText.setError("Campo obligatorio.");
            bandera=false;
        }
        if (mailUsuarioText.getText().length() == 0){
            mailUsuarioText.setError("Campo obligatorio.");
            bandera=false;
        }
        if (contraseniaText.getText().length() == 0){
            contraseniaText.setError("Campo obligatorio.");
            bandera=false;
        }
        if (confContraseniaText.getText().length() == 0){
            confContraseniaText.setError("Campo obligatorio.");
            bandera=false;
        }
        return bandera;
    }

    public void backToLoginActivity(){
        Intent loginActivity = new Intent(this, LogInActivity.class);
        startActivity(loginActivity);
    }
}