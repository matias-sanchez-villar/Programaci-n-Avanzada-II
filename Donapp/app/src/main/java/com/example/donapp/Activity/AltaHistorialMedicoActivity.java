package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.donapp.Data.Campania.CampaniaRepository;
import com.example.donapp.Data.Localidad.LocalidadRepository;
import com.example.donapp.Data.Provincia.ProvinciaRepository;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.HistorialMedico;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.Estado;
import com.example.donapp.Enums.TipoSangre;
import com.example.donapp.R;
import com.example.donapp.Util.DateUtil;

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

    private HistorialMedico historialMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_historial_medico);
        fillProperties();
        setListeners();
    }

    public void onClickBtnGuardar(){
        historialMedico = new HistorialMedico();
        historialMedico.setTipoSangre(tsSelected);
        historialMedico.setPeso(Integer.valueOf(txtPeso.getText().toString()));
        historialMedico.setAltura(Double.parseDouble(txtAltura.getText().toString()));
        historialMedico.setUltimaDonacion(DateUtil.convertToSqlDate(txtFechaUltimaDonacion.getText().toString()));

        historialMedico.setTatuajes(tatuajesSi.isSelected());
        historialMedico.setVacunaAlergia(inyeccionSi.isSelected());
        historialMedico.setExamenSangre(examenSi.isSelected());
        historialMedico.setRevisionMedica(revisionSi.isSelected());
        historialMedico.setTratamientoDental(dentalSi.isSelected());
        historialMedico.setEndoscopia(endoscopiaSi.isSelected());
        historialMedico.setEmbarazo(embarazosi.isSelected());
        historialMedico.setEnfermedaCronica(enfermedadCronicaSi.isSelected());
        historialMedico.setOperacion(operacionSi.isSelected());
        historialMedico.setViaje(viajeSi.isSelected());
        historialMedico.setAnemia(anemiaSi.isSelected());
        historialMedico.setAccidenteVascular(accidenteSi.isSelected());
        historialMedico.setUsaMedicamentos(medicamentosSi.isSelected());
        historialMedico.setEstado(Estado.ACTIVO);
        historialMedico.setUsuario(new Usuario(GlobalPreferences.getLoggedUserId(this)));
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

    public void toast(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();}
}