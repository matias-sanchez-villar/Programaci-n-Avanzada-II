package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.donapp.Data.Usuario.UsuarioRepository;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.TipoUsuario;
import com.example.donapp.R;

public class LogInActivity extends AppCompatActivity {

    UsuarioRepository _usuarioRepository;
    EditText nombreText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        _usuarioRepository = new UsuarioRepository(this);
        fillProperties();
    }

    public void login(View view){
        Usuario usuarioSearched = searchUsuario();
        if(usuarioSearched != null){

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("mailUsuario", usuarioSearched.getEmail());
            edit.putString("nombreUsuario", usuarioSearched.getNombreUsuario());
            edit.putInt("idUsuario", usuarioSearched.getId());
            edit.apply();

            initializeMenu(usuarioSearched.getTipoUsuario());
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Usuario o contraseña incorrectos",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void registrar(View view){
        Intent tipoUsuarioActivity = new Intent(this, TipoUsuarioActivity.class);
        startActivity(tipoUsuarioActivity);
    }

    public Usuario searchUsuario(){
        Usuario usuarioToSearch =
                new Usuario(
                nombreText.getText().toString(),
                passwordText.getText().toString());
        return _usuarioRepository.selectEntity(usuarioToSearch);
    }

    public void fillProperties(){
        this.nombreText = (EditText) findViewById(R.id.loginNombreUsuario);
        this.passwordText = (EditText) findViewById(R.id.loginPassword);
    }

    public void initializeMenu(TipoUsuario tipoUsuario){
        if(tipoUsuario == TipoUsuario.SOLICITANTE || tipoUsuario == TipoUsuario.DONANTE){
            Intent menu = new Intent(this, SolicitanteDonanteActivity.class);
            startActivity(menu);
        }
    }
}