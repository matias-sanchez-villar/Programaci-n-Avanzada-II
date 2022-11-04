package com.example.donapp.Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.donapp.Entity.Campana;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.MainActivity;
import com.example.donapp.R;

import java.util.ArrayList;
import java.util.Calendar;


public class Alta_Campana_Fragment extends Fragment {

    private int IdEmpresa;
    private EditText textNombre, textFecha, textCantS, textCantDias;
    private Spinner spLocalidad, spProvincia;
    private CheckBox checkTerminos;
    private Button btnCerrarSession, btnCerrar, btnGuardar;
    //private ImageButton iBtbCalendario;
    private View view;
    private int dia,mes,anio;

    public Alta_Campana_Fragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_alta__campana_, container, false);

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

        /*iBtbCalendario.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {onClickiBtbCalendario();}
        });*/

        return this.view;

    }



    public void fillProperties(){
        //TODO queda traer los datos para los SPINNER
        IdEmpresa=1;
        textNombre = (EditText)view.findViewById(R.id.textNombre);
        textFecha = (EditText)view.findViewById(R.id.textFecha);
        spLocalidad = (Spinner)view.findViewById(R.id.spLocalidad);
        spProvincia = (Spinner)view.findViewById(R.id.spProvincia);
        textCantS = (EditText)view.findViewById(R.id.textCantS);
        textCantDias = (EditText)view.findViewById(R.id.textCantDias);
        checkTerminos = (CheckBox)view.findViewById(R.id.checkTerminos);
        btnCerrarSession = (Button)view.findViewById(R.id.btnCerrarSession);
        //iBtbCalendario = (ImageButton) view.findViewById(R.id.iBtbCalendario);
        btnCerrar = (Button)view.findViewById(R.id.btnCerrar);
        btnGuardar = (Button)view.findViewById(R.id.btnGuardar);
    }

    private void onClickBtnGuardar() {
        ArrayList<Localidad> localidad = new ArrayList<Localidad>();
        localidad.add(new Localidad(spLocalidad.getSelectedItem().toString()));
        Campana campana = new Campana(
                //Falta el ID empresa de session
                textNombre.getText().toString(),
                textFecha.getText().toString(),
                new Provincia(
                        spProvincia.getSelectedItem().toString(),
                        localidad
                ),
                Integer.parseInt((textCantS.getText().toString())),
                Integer.parseInt((textCantDias.getText().toString()))
        );

        if(checkTerminos.isChecked()){
            // Guardar

        }

        //TODO: Solo queda guardar en la BD

    }

    private void onClickBtnCerrar() {
    }

    private void onClickBtnCerrarSession() {
    }

    /*private void onClickiBtbCalendario() {
        final Calendar c= Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        anio = c.get(Calendar.YEAR);

        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textFecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        },dia,mes,anio);
        datePicker.show();
    }*/


}