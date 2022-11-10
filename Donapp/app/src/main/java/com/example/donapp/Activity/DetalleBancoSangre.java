package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.donapp.Data.BancoSangre.BancoSangreRepository;
import com.example.donapp.Entity.BancoSangre;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.R;

public class DetalleBancoSangre extends AppCompatActivity {

    TextView txtHospital, txtProvincia, txtLocalidad, txtDireccion;
    Button btnPostularse;
    BancoSangreRepository bancoSangreRepository;
    BancoSangre bancoSangre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_banco_sangre);

        fillProperties();
        setProperties();
    }

    public void onClickBtnPostularse(View view){
        //a donde le pegamos?
    }

    private void fillProperties() {
        txtHospital = (TextView)findViewById(R.id.txtHospitalResponseBNC);
        txtProvincia = (TextView)findViewById(R.id.txtProvinciaResponseBNC);
        txtLocalidad = (TextView)findViewById(R.id.txtLocalidadResponseBNC);
        txtDireccion = (TextView)findViewById(R.id.txtDireccionResponseBNC);
        btnPostularse = (Button)findViewById(R.id.btnPostularseBNC);
        bancoSangreRepository = new BancoSangreRepository(this);
    }

    public void setProperties(){
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("bancoSangre_id", 0);
        //bancoSangre = bancoSangreRepository.selectEntity(new Solicitud(id));
        txtHospital.setText(bancoSangre.getHospital());
        txtProvincia.setText(bancoSangre.getProvincia().getNombre());
        txtLocalidad.setText(bancoSangre.getLocalidad().getNombre());
        txtDireccion.setText(bancoSangre.getDireccion());
    }
}