package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.donapp.Data.Solicitud.SolicitudRepository;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.R;

public class MisSolicitudesActivity extends AppCompatActivity {

    Button newSolicitudBtn;
    ListView misSolicitudesLv;
    SolicitudRepository _solicitudRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_solicitudes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        _solicitudRepository = new SolicitudRepository(this);

        instantiateLayouts();
        setListeners();
        getDBInfo();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void instantiateLayouts(){
        misSolicitudesLv = (ListView) findViewById(R.id.misSolicitudesList);
        newSolicitudBtn = (Button) findViewById(R.id.nuevaSolicitudBtn);
    }

    public void setListeners(){

        //Instancio de button
        newSolicitudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToModificarSolicitud(new Solicitud());
            }
        });

        // Instancia de list view
        misSolicitudesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Solicitud solicitudSelected = (Solicitud) parent.getItemAtPosition(position);
                goToModificarSolicitud(solicitudSelected);
            }
        });
    }

    public void getDBInfo(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int usuarioId = preferences.getInt("idUsuario", 0);
        _solicitudRepository.selectAllForListViewByIntegerPropertie(
                misSolicitudesLv,
                "id_usuario",
                usuarioId
        );
    }

    public void goToModificarSolicitud(Solicitud solicitud){
        Intent modificarSolicitud = new Intent(this, AltaSolicitudActivity.class);
        modificarSolicitud.putExtra("solicitudUpdate", solicitud);
        startActivity(modificarSolicitud);
    }
}