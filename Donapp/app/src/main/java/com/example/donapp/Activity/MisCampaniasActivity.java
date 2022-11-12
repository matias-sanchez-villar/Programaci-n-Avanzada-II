package com.example.donapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.donapp.Data.Campania.CampaniaRepository;
import com.example.donapp.Data.Solicitud.SolicitudRepository;
import com.example.donapp.Entity.Campania;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.R;


public class MisCampaniasActivity extends AppCompatActivity {

    Button newCampaniaBtn;
    ListView misCampaniasesLv;
    CampaniaRepository _campaniasRepository;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_campanias);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        _campaniasRepository = new CampaniaRepository(this);

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
        misCampaniasesLv = (ListView) findViewById(R.id.misCampaniasList);
        newCampaniaBtn = (Button) findViewById(R.id.nuevaCampaniaBtn);
    }

    public void setListeners(){

        //Instancio de button
        newCampaniaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToModificarCampania(new Campania());
            }
        });

        // Instancia de list view
        misCampaniasesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Campania campaniaSelected = (Campania) parent.getItemAtPosition(position);
                goToModificarCampania(campaniaSelected);
            }
        });
    }

    public void getDBInfo(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int usuarioId = preferences.getInt("idUsuario", 0);
        _campaniasRepository.selectAllForListViewByIntegerPropertie(
                misCampaniasesLv,
                "id_usuario",
                usuarioId
        );
    }

    public void goToModificarCampania(Campania campania){
        Intent modificarCampania = new Intent(this, AltaCampaniaActivity.class);
        modificarCampania.putExtra("campaniaUpdate", campania);
        startActivity(modificarCampania);
    }






}
