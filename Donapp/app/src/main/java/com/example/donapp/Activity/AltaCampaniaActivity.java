package com.example.donapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.donapp.Data.Campania.CampaniaRepository;
import com.example.donapp.Data.Localidad.LocalidadRepository;
import com.example.donapp.Data.Provincia.ProvinciaRepository;
import com.example.donapp.Entity.Criticidad;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.Localidad;
import com.example.donapp.Entity.Provincia;
import com.example.donapp.Entity.Campania;

import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.EstadoCampania;
import com.example.donapp.Enums.EstadoSolicitud;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.R;
import com.example.donapp.Util.DateUtil;

import java.util.Date;

public class AltaCampaniaActivity extends AppCompatActivity {

    private EditText txtNombre, txtFecha,txtFechaFin, txtDireccion, txtCantS, txtCantDias;
    private Spinner spnLocalidad, spnProvincia;
    private CheckBox checkTerminos;
    private Button btnGuardar, btnCancelarAltaCampania;
    private ProvinciaRepository _provinciaRepository;
    private LocalidadRepository _localidadRepository;
    private Provincia provinciaSelected;
    private Localidad localidadSelected;
    private CampaniaRepository campaniaRepository;
    private Campania campaniaForUpdate;
    Bundle bundle;
    TextView title;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_campania);
        bundle = getIntent().getExtras();

        campaniaForUpdate = (Campania) bundle.getSerializable("campaniaUpdate");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fillProperties();
        setListeners();
        getDBInfo();

        // Si no es nueva, hacemos un update
        if(!campaniaForUpdate.isNew()){
            fillComponents(campaniaForUpdate);
            title.setText("Modificar campania"); //ver titulo
        } else{
            btnCancelarAltaCampania.setVisibility(View.GONE);
        }
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void onClickBtnGuardar(){
        // if (validateData()) {
        setCampania(EstadoCampania.ACTIVA);
        int response;
        if(campaniaForUpdate.isNew()){
            if(campaniaRepository.create(campaniaForUpdate) != 0){
                toast("Campaña creada");
            }else toast("Error");
        } else {
            if(campaniaRepository.update(campaniaForUpdate) != StatusResponse.FAIL){
                toast("Campaña modificada");
            } else toast("Error");
        }
        goToMisCampanias();
    }

    private void fillProperties() {
        campaniaRepository = new CampaniaRepository(this);
        _provinciaRepository = new ProvinciaRepository(this);
        _localidadRepository = new LocalidadRepository(this);

        txtNombre = (EditText)findViewById(R.id.txtNombreAltaCampania);
        txtFecha = (EditText)findViewById(R.id.txtFechaAltaCampania);
        txtFechaFin = (EditText)findViewById(R.id.txtFechaFinAltaCampania);
        txtDireccion = (EditText)findViewById(R.id.txtDireccionAltaCampania);
        spnLocalidad = (Spinner)findViewById(R.id.spnLocalidadAltaCampania);
        spnProvincia = (Spinner)findViewById(R.id.spnProvinciaAltaCampania);
        txtCantS = (EditText)findViewById(R.id.txtCantDonantesAltaCampania);
        txtCantDias = (EditText)findViewById((R.id.txtCantDiasAltaCampania));
        checkTerminos = (CheckBox)findViewById(R.id.checkTerminosAltaCampania);
        btnGuardar = (Button)findViewById(R.id.btnGuardarAltaCampania);
        btnCancelarAltaCampania = (Button)findViewById(R.id.btnCancelarAltaCampania);
        title = (TextView) findViewById(R.id.titleAltaCampania); //Ver el titulo

    }

    public void setCampania(EstadoCampania estado) {
        if (campaniaForUpdate.isNew()) {
            this.campaniaForUpdate.setEstado(estado);
            this.campaniaForUpdate.setUsuario(new Usuario(GlobalPreferences.getLoggedUserId(this)));
            this.campaniaForUpdate.setAutomaticCodigo();
        }
        this.campaniaForUpdate.setNombreCampana(txtNombre.getText().toString());
        this.campaniaForUpdate.setFecha(DateUtil.convertToSqlDate(txtFecha.getText().toString()));
        this.campaniaForUpdate.setFechaFin(DateUtil.convertToSqlDate(txtFechaFin.getText().toString()));
        this.campaniaForUpdate.setProvincia(new Provincia(provinciaSelected.getId()));
        this.campaniaForUpdate.setLocalidad(new Localidad(localidadSelected.getId()));
        this.campaniaForUpdate.setDireccion(txtDireccion.getText().toString());
        this.campaniaForUpdate.setCantDias(Integer.parseInt(txtCantDias.getText().toString()));
        this.campaniaForUpdate.setCantDonantes(Integer.parseInt(txtCantS.getText().toString()));

    }

    public void setListeners() {
        btnGuardar.setEnabled(false);

        spnProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provinciaSelected = (Provincia) parent.getSelectedItem();
                // Seteamos a 0 los items que pueda tener;
                spnLocalidad.setAdapter(null);
                getLocalidadByProvincia(provinciaSelected.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnLocalidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                localidadSelected = (Localidad) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnGuardar();
            }
        });
        btnCancelarAltaCampania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarCampania();
            }
        });

        checkTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btnGuardar.setEnabled(true);
                }
            }
        });
    }


    public void getDBInfo(){
        _provinciaRepository.selectAllForSpinner(spnProvincia);
    }

    public void getLocalidadByProvincia(int provinciaId){
        _localidadRepository.selectAllForSpinnerByProvincia(spnLocalidad, provinciaId);
    }



    private boolean validarFechayZona(Date fecha, Localidad localidadSelected) {
        return true; //hacer metodo validar
    }

    private void fillComponents(Campania campania) {
        txtNombre.setText(campania.getNombreCampana());
        txtFecha.setText(String.valueOf(campania.getFecha()));
        txtFechaFin.setText(String.valueOf(campania.getFechaFin()));
        txtDireccion.setText(campania.getDireccion());
        txtCantS.setText(campania.getCantDonantes());
        txtCantDias.setText(campania.getCantDias());
    }

    public void goToMisCampanias(){
        Intent misCampanias = new Intent(this, MisCampaniasActivity.class);
        startActivity(misCampanias);
    }

    private void cancelarCampania() {
        setCampania(EstadoCampania.CANCELADA);
        if(campaniaRepository.update(campaniaForUpdate) != StatusResponse.FAIL){
            toast("Campaña cancelada");
        } else{
            toast("Error");
        }
    }

    public void toast(String txt) {Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();}
}
