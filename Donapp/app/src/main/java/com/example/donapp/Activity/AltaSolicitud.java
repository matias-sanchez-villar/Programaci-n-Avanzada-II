package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.R;
import com.example.donapp.Util.DateUtil;
import com.example.donapp.Util.Validar;

import java.util.ArrayList;

public class AltaSolicitud extends AppCompatActivity {

    private EditText txtNombre, txtApellido, txtFecha, txtDireccion, txtCantDonante;
    private Spinner spnLocalidad, spnProvincia,spnTipoSangre;
    private Button btnCerrarSession, btnCerrar, btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_solicitud);
    }

    public void onClickBtnGuardar(){
        if (validateData())
            return;

        //TODO: Solo queda guardar en la BD

    }

    public void fillProperties(){
        //TODO queda traer los datos para los SPINNER

        txtNombre = (EditText)this.findViewById(R.id.txtNombre);
        txtApellido = (EditText)this.findViewById(R.id.txtApellido);
        txtFecha = (EditText)this.findViewById(R.id.txtFecha);
        txtDireccion = (EditText)this.findViewById(R.id.txtDireccion);
        txtCantDonante = (EditText)this.findViewById(R.id.txtCantDonante);
        spnLocalidad = (Spinner)this.findViewById(R.id.spnLocalidad);
        spnProvincia = (Spinner)this.findViewById(R.id.spnProvincia);
        spnTipoSangre = (Spinner)this.findViewById(R.id.spnTipoSangre);
        btnCerrarSession = (Button)this.findViewById(R.id.btnCerrarSession);
        btnCerrar = (Button)this.findViewById(R.id.btnCerrar);
        btnGuardar = (Button)this.findViewById(R.id.btnGuardar);
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

    public void toast(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();}
}