package com.example.donapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.R;
import com.example.donapp.Util.DateUtil;

import java.util.ArrayList;

public class AltaSolicitud extends Fragment {

    private EditText txtNombre, txtApellido, txtFecha, txtDireccion, txtCantDonante;
    private Spinner spnLocalidad, spnProvincia,spnTipoSangre;
    private Button btnCerrarSession, btnCerrar, btnGuardar;
    private View view;

    public AltaSolicitud() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_alta_solicitud, container, false);

        //Inicializamos variables
        fillProperties();

        //Evento de los BTN On Click

        btnCerrarSession.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {onClickBtnCerrarSession();}
        });


        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {onClickBtnCerrar();}
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {onClickBtnGuardar();}
        });

        return this.view;
    }

    public void fillProperties(){
        //TODO queda traer los datos para los SPINNER

        txtNombre = (EditText)view.findViewById(R.id.txtNombre);
        txtApellido = (EditText)view.findViewById(R.id.txtApellido);
        txtFecha = (EditText)view.findViewById(R.id.txtFecha);
        txtDireccion = (EditText)view.findViewById(R.id.txtDireccion);
        txtCantDonante = (EditText)view.findViewById(R.id.txtCantDonante);
        spnLocalidad = (Spinner)view.findViewById(R.id.spnLocalidad);
        spnProvincia = (Spinner)view.findViewById(R.id.spnProvincia);
        spnTipoSangre = (Spinner)view.findViewById(R.id.spnTipoSangre);
        btnCerrarSession = (Button)view.findViewById(R.id.btnCerrarSession);
        btnCerrar = (Button)view.findViewById(R.id.btnCerrar);
        btnGuardar = (Button)view.findViewById(R.id.btnGuardar);
    }

    public void onClickBtnCerrarSession(){

    }

    public void onClickBtnCerrar(){

    }

    public void onClickBtnGuardar(){
        ArrayList<Localidad> localidad = new ArrayList<Localidad>();
        localidad.add(new Localidad(spnLocalidad.getSelectedItem().toString()));
        Solicitud solicitud = new Solicitud(
                txtNombre.getText().toString(),
                txtApellido.getText().toString(),
                DateUtil.convertToSqlDate(txtFecha.getText().toString()),
                new Provincia(
                        spnProvincia.getSelectedItem().toString(),
                        localidad
                ),
                txtDireccion.getText().toString(),
                Integer.parseInt((txtCantDonante.getText().toString()))
        );

        //TODO: Solo queda guardar en la BD

    }

    public void toast(String txt) {Toast.makeText(getContext(), txt, Toast.LENGTH_SHORT).show();}

}