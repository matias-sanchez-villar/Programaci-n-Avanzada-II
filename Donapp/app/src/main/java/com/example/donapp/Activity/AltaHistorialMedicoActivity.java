package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.donapp.Data.Campania.CampaniaRepository;
import com.example.donapp.Data.Localidad.LocalidadRepository;
import com.example.donapp.Data.Provincia.ProvinciaRepository;
import com.example.donapp.Entity.HistorialMedico;
import com.example.donapp.R;

public class AltaHistorialMedicoActivity extends AppCompatActivity {

    private EditText txtPeso, txtAltura, txtFechaUltimaDonacion;
    private Spinner spnTipoSangre;
    private Button btnGuardar;
    private CheckBox tatuajesT, tatujesF,
                     enferTranSexualT, enferTranSexualF,
                     usoDrograsT, usoDrograsF,
                     usoMedicamentosT, usoMedicamentosF,
                     hepatitisT, hepatitisF,
                     resMinimasT, resMinimasF,
                     resMaximasT, resMaximasF;
    private HistorialMedico historialMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_historial_medico);
        fillProperties();
    }

    public void onClickBtnGuardar(View view){
        if (validateData())
            return;
    }

    private void fillProperties() {
        txtPeso = (EditText)findViewById(R.id.txtPeso);
        txtAltura = (EditText)findViewById(R.id.txtAltura);
        txtFechaUltimaDonacion = (EditText)findViewById(R.id.txtFechaUltimaDonacion);
        spnTipoSangre = (Spinner)findViewById(R.id.spnTipoSangres);

        tatuajesT = (CheckBox)findViewById(R.id.cbxTatuajesTrue);
        tatujesF = (CheckBox)findViewById((R.id.cbxTatuajesFalse));

        enferTranSexualT = (CheckBox)findViewById((R.id.cbxTranSexualTrue));
        enferTranSexualF = (CheckBox)findViewById(R.id.cbxTranSexualFalse);

        usoDrograsT = (CheckBox)findViewById(R.id.cbxDrogasTrue);
        usoDrograsF = (CheckBox)findViewById((R.id.cbxDrogasFalse));

        usoMedicamentosT = (CheckBox)findViewById((R.id.cbxMedicamentosTrue));
        usoMedicamentosF = (CheckBox)findViewById(R.id.cbxMedicamentosFalse);

        hepatitisT = (CheckBox)findViewById(R.id.cbxHepatitisTrue);
        hepatitisF = (CheckBox)findViewById((R.id.cbxHepatitisFalse));

        resMinimasT = (CheckBox)findViewById(R.id.cbxMedidasMinimasTrue);
        resMinimasF = (CheckBox)findViewById((R.id.cbxMedidasMinimasFalse));

        resMaximasT = (CheckBox)findViewById((R.id.cbxDefinitivosTrue));
        resMaximasF = (CheckBox)findViewById(R.id.cbxDefinitivosFalse);

        btnGuardar = (Button)findViewById(R.id.btnGuardarHistMedico);
    }

    public boolean validateData(){
        return true;
    }

    public void toast(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();}
}