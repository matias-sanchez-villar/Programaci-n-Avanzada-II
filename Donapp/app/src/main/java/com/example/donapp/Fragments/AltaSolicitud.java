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

import com.example.donapp.R;

public class AltaSolicitud extends Fragment {


    private EditText txtNombre, txtApellido, txtFecha, txtDireccion, txtCantDonante;
    private Spinner spnLocalidad, spnProvincia,spnTipoSangre;
    private Button btnCerrarSession, btnCerrar, btnGuardar;
    private View view;

    public AltaSolicitud() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_alta_solicitud, container, false);
        initialize();
        return this.view;
    }

    public void initialize(){
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

    }

}