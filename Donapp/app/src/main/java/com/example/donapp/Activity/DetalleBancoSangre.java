package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donapp.Data.BancoSangre.BancoSangreRepository;
import com.example.donapp.Data.BancoSangre.DataBancoSangreAsync;
import com.example.donapp.Data.BancoSangre.ReadBancoSangreAsync;
import com.example.donapp.Entity.BancoSangre;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.R;

public class DetalleBancoSangre extends AppCompatActivity {

    TextView txtHospital, txtProvincia, txtLocalidad, txtDireccion;
    Button btnPostularse;
    BancoSangreRepository BancoSangreRepository;
    BancoSangre bancoSangre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_banco_sangre);

        fillProperties();
        setProperties();
    }

    public void onClickBtnPostularse(View view){
        int idUsuario = GlobalPreferences.getLoggedUserId(this);
        int idBancoSangre = idBancoSangre();



        toast("postulación realizada");
    }

    private void fillProperties() {
        txtHospital = (TextView)findViewById(R.id.txtHospitalResponseBNC);
        txtProvincia = (TextView)findViewById(R.id.txtProvinciaResponseBNC);
        txtLocalidad = (TextView)findViewById(R.id.txtLocalidadResponseBNC);
        txtDireccion = (TextView)findViewById(R.id.txtDireccionResponseBNC);
        btnPostularse = (Button)findViewById(R.id.btnPostularseBNC);
    }

    public void setProperties(){
        int id = idBancoSangre();
        BancoSangreRepository = new BancoSangreRepository(this);
        bancoSangre = BancoSangreRepository.selectEntity(new BancoSangre(id));
        txtHospital.setText(bancoSangre.getHospital());
        txtProvincia.setText(bancoSangre.getProvincia().getNombre());
        txtLocalidad.setText(bancoSangre.getLocalidad().getNombre());
        txtDireccion.setText(bancoSangre.getDireccion());
    }

    public int idBancoSangre(){
        Bundle bundle = getIntent().getExtras();
        return bundle.getInt("bancoSangre_id", 0);
    }

    public void toast(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();}

}