package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.donapp.Data.Postulantes.PostulantesRepository;
import com.example.donapp.Data.Solicitud.SolicitudRepository;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Enums.Categoria;
import com.example.donapp.R;
import com.example.donapp.databinding.SolicitudesListItemBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DetallePostulantesActivity extends AppCompatActivity {

    Bundle bundle;
    ListView listView;
    PostulantesRepository _postulantesRepository;
    PersonaFisica personaSelected;
    Solicitud solicitud;
    int categoria;
    int idRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_postulantes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        _postulantesRepository = new PostulantesRepository(this);

        bundle = getIntent().getExtras();

        categoria = bundle.getInt("categoria");
        idRegistro = bundle.getInt("id_registro");
        solicitud = (Solicitud) bundle.getSerializable("solicitud");

        instanceLayouts();
        setListeners();
        getDBInfo();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void instanceLayouts(){
        listView = (ListView) findViewById(R.id.postulantesSolicitudesList);
    }

    public void setListeners(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSelected = (PersonaFisica) parent.getItemAtPosition(position);
                goToPostulanteActivity(personaSelected);
            }
        });
    }

    public void getDBInfo(){
        _postulantesRepository.selectAllByIdRegistro(listView, idRegistro, Categoria.getCategoria(categoria));
    }

    public void goToPostulanteActivity(PersonaFisica persona){
        Intent postulanteActivity = new Intent(this, PostulanteActivity.class);
        postulanteActivity.putExtra("postulante", personaSelected);
        postulanteActivity.putExtra("id_registro", idRegistro);
        postulanteActivity.putExtra("categoria", categoria);
        postulanteActivity.putExtra("solicitud", solicitud);

        startActivity(postulanteActivity);
    }
}