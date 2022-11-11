package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.donapp.Data.Campania.CampaniaRepository;
import com.example.donapp.Data.HistorialMedico.HistorialMedicoRepository;
import com.example.donapp.Data.Localidad.LocalidadRepository;
import com.example.donapp.Data.Provincia.ProvinciaRepository;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.HistorialMedico;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.Estado;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Enums.TipoSangre;
import com.example.donapp.R;
import com.example.donapp.Util.DateUtil;

import java.math.BigDecimal;

public class AltaHistorialMedicoActivity extends AppCompatActivity {

    private EditText txtPeso, txtAltura, txtFechaUltimaDonacion;
    private Spinner spnTipoSangre;
    private Button btnGuardar;
    private TipoSangre tsSelected;
    private CheckBox
            tatuajesSi, tatuajesNo,
            inyeccionSi, inyeccionNo,
    examenSi, examenNo,
    revisionSi, revisionNo,
    dentalSi, dentalNo,
    endoscopiaSi, endoscopiaNo,
    embarazosi, embarazoNo,
    enfermedadCronicaSi, enfermedadCronicaNo,
    operacionSi, operacionNo,
    viajeSi, viajeNo,
    anemiaSi, anemiaNo,
    accidenteSi, accidenteNo,
    medicamentosSi, medicamentosNo,
    hepatitisSi, hepatitisNo;

    HistorialMedicoRepository _historialMedicoRepository;

    Bundle bundle;

    HistorialMedico historialMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_historial_medico);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        _historialMedicoRepository = new HistorialMedicoRepository(this);

        bundle = getIntent().getExtras();

        historialMedico = (HistorialMedico) bundle.getSerializable("historialMedicoUpdate");

        if (!historialMedico.isNew()){
            setPropertiesForUpdate(historialMedico);
        }
        fillProperties();
        setListeners();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void onClickBtnGuardar(){
        historialMedico.setTipoSangre(tsSelected);
        historialMedico.setPeso(Integer.valueOf(txtPeso.getText().toString()));
        historialMedico.setAltura(new BigDecimal(txtAltura.getText().toString()));
        historialMedico.setUltimaDonacion(DateUtil.convertToSqlDate(txtFechaUltimaDonacion.getText().toString()));

        historialMedico.setTatuajes(tatuajesSi.isChecked());
        historialMedico.setVacunaAlergia(inyeccionSi.isChecked());
        historialMedico.setExamenSangre(examenSi.isChecked());
        historialMedico.setRevisionMedica(revisionSi.isChecked());
        historialMedico.setTratamientoDental(dentalSi.isChecked());
        historialMedico.setEndoscopia(endoscopiaSi.isChecked());
        historialMedico.setEmbarazo(embarazosi.isChecked());
        historialMedico.setEnfermedaCronica(enfermedadCronicaSi.isChecked());
        historialMedico.setOperacion(operacionSi.isChecked());
        historialMedico.setViaje(viajeSi.isChecked());
        historialMedico.setAnemia(anemiaSi.isChecked());
        historialMedico.setAccidenteVascular(accidenteSi.isChecked());
        historialMedico.setUsaMedicamentos(medicamentosSi.isChecked());
        historialMedico.setEstado(Estado.ACTIVO);
        historialMedico.setUsuario(new Usuario(GlobalPreferences.getLoggedUserId(this)));

        updateHistorialMedico(historialMedico);
    }

    private void fillProperties() {
        txtPeso = (EditText)findViewById(R.id.txtPesoAltaHistorialMedico);
        txtAltura = (EditText)findViewById(R.id.txtAlturaAltaHistorialMedico);
        txtFechaUltimaDonacion = (EditText)findViewById(R.id.txtFechaUltimaDonacionAltaHistorialMedico);
        spnTipoSangre = (Spinner)findViewById(R.id.spnTipoSangreAltaHistorialMedico);

        tatuajesSi = findViewById(R.id.cbxTatuajesTrueAltaHistorialMedico);
        tatuajesNo = findViewById(R.id.cbxTatuajesFalseAltaHistorialMedico);
        inyeccionSi = findViewById(R.id.cbxVacunaAlergiaTrueAltaHistorialMedico);
        inyeccionNo = findViewById(R.id.cbxVacunaAlergiaFalseAltaHistorialMedico);
        examenSi  = findViewById(R.id.cbxExamenSangreTrueAltaHistorialMedico);
        examenNo = findViewById(R.id.cbxExamenSangreTrueAltaHistorialMedico);
        revisionSi = findViewById(R.id.cbxRevisionMedicaTrueAltaHistorialMedico);
        revisionNo = findViewById(R.id.cbxRevisionMedicaFalseAltaHistorialMedico);
        dentalSi = findViewById(R.id.cbxTratamientoDentalTrueAltaHistorialMedico);
        dentalNo = findViewById(R.id.cbxTratamientoDentalFalseAltaHistorialMedico);
        endoscopiaSi = findViewById(R.id.cbxEndoscopiaTrueAltaHistorialMedico);
        endoscopiaNo = findViewById(R.id.cbxEndoscopiaFalseAltaHistorialMedico);
        embarazosi = findViewById(R.id.cbxEmbarazoTrueAltaHistorialMedico);
        embarazoNo = findViewById(R.id.cbxEmbarazoFalseAltaHistorialMedico);
        enfermedadCronicaSi = findViewById(R.id.cbxEnfermedadesTrueAltaHistorialMedico);
        enfermedadCronicaNo = findViewById(R.id.cbxEnfermedadesFalseAltaHistorialMedico);
        operacionSi = findViewById(R.id.cbxOperacionesTrueAltaHistorialMedico);
        operacionNo = findViewById(R.id.cbxOperacionesFalseAltaHistorialMedico);
        viajeSi = findViewById(R.id.cbxViajesTrueAltaHistorialMedico);
        viajeNo = findViewById(R.id.cbxViajesFalseAltaHistorialMedico);
        anemiaSi = findViewById(R.id.cbxAnemiaTrueAltaHistorialMedico);
        anemiaNo = findViewById(R.id.cbxAnemiaFalseAltaHistorialMedico);
        accidenteSi = findViewById(R.id.cbxAccidenteVascularTrueAltaHistorialMedico);
        accidenteNo = findViewById(R.id.cbxAccidenteVascularFalseAltaHistorialMedico);
        medicamentosSi = findViewById(R.id.cbxMedicamentosTrueAltaHistorialMedico);
        medicamentosNo = findViewById(R.id.cbxMedicamentosFalseAltaHistorialMedico);
        hepatitisSi = findViewById(R.id.cbxHepatitisTrue);
        hepatitisNo = findViewById(R.id.cbxHepatitisFalse);

        spnTipoSangre.setAdapter(TipoSangre.getSpinnerAdapter(this));
        btnGuardar = (Button)findViewById(R.id.btnGuardarHistMedico);
    }

    public boolean validateData(){
        return true;
    }

    public void setListeners(){
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnGuardar();
            }
        });

        spnTipoSangre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tsSelected = TipoSangre.getTipoSangreByPosition(parent.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setPropertiesForUpdate(HistorialMedico historialMedico){

    }

    public void updateHistorialMedico(HistorialMedico historialMedico){

        if(historialMedico.isNew()){
            if(_historialMedicoRepository.create(historialMedico) != 0){
                toast("Historial generado");
            } else toast("ERROR");
        } else {
            if(_historialMedicoRepository.update(historialMedico) != StatusResponse.FAIL){
                toast("Historial modificado");
            } else toast("ERROR");
        }

        Intent solicitanteActivity = new Intent(this, SolicitanteDonanteActivity.class);
        startActivity(solicitanteActivity);
    }

    public void toast(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }
}