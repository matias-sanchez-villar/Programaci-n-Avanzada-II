package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.donapp.Data.Usuario.UsuarioRepository;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.R;

public class EditarMisDatos extends AppCompatActivity {

    EditText txtNombre, txtEmail, txtPassword;
    Button btnModificar;
    UsuarioRepository usuarioRepository;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_mis_datos);

        fillProperties();
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onClickBtnModificar();
            }
        });
    }

    public void onClickBtnModificar(){
        usuario.setId(GlobalPreferences.getLoggedUserId(this));
        usuario.setNombreUsuario(txtNombre.getText().toString());
        usuario.setEmail(txtEmail.getText().toString());
        usuario.setPassword(txtPassword.getText().toString());

        usuarioRepository = new UsuarioRepository(this);
        StatusResponse status = usuarioRepository.update(usuario);

        if (status == StatusResponse.SUCCESS) toast("Datos modificados");
        else toast("Error datos no modificados");
    }

    public void fillProperties(){
        txtNombre = (EditText)findViewById(R.id.txtNombreUsuarioEMD);
        txtEmail = (EditText)findViewById(R.id.txtEmailEMD);
        txtPassword = (EditText)findViewById(R.id.txtPasswordEMD);
        btnModificar = (Button) findViewById(R.id.btnModificarEMD);
    }

    public void toast(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }

}