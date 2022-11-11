package com.example.donapp.Activity.ui.MisDatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;

import com.example.donapp.Activity.DetalleSolicitudActivity;
import com.example.donapp.Activity.MisSolicitudesActivity;
import com.example.donapp.Data.Campania.CampaniaRepository;
import com.example.donapp.Data.Localidad.LocalidadRepository;
import com.example.donapp.Data.Provincia.ProvinciaRepository;
import com.example.donapp.Data.Usuario.ReadUsuarioAsync;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.R;
import com.example.donapp.databinding.FragmentMisDatosPersonaFisicaBinding;

public class MisDatosPersonaFisicaFragment extends Fragment{

    FragmentMisDatosPersonaFisicaBinding binding;
    TextView txtNombre, txtTelefono, txtDireccion, txtProvincia, txtEmail,
            txtLocalidad, txtApellido, txtDNI, txtFechaNacimiento, txtContasena;
    Button btnModifica;
    PersonaFisica personaFisica;
    ReadUsuarioAsync readUsuarioAsync;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MisDatosPersonaFisicaViewModel misDatosViewModel =
                new ViewModelProvider(this).get(MisDatosPersonaFisicaViewModel.class);

        binding = FragmentMisDatosPersonaFisicaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fillProperties();
        setingsPropirties();
        onClick();

        return root;
    }

    private void onClick(){
        btnModifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: ModificarMisDatos.class
                Intent misSolicitudesIntent = new Intent(getActivity(), MisSolicitudesActivity.class);
                startActivity(misSolicitudesIntent);
            }
        });
    }

    private void fillProperties() {
        txtNombre = (EditText)binding.txtNombreResponseMDF;
        txtApellido = (EditText)binding.txtApellidoResponseMDF;
        txtFechaNacimiento = (EditText)binding.txtFechaNacimientoResponseMDF;
        txtDNI = (EditText)binding.txtDNIResponseMDF;
        txtTelefono = (EditText)binding.txtTelefonoResponseMDF;
        txtProvincia = (EditText)binding.txtProvinciaResponseMDF;
        txtLocalidad = (EditText)binding.txtLocalidadResponseMDF;
        txtDireccion = (EditText)binding.txtDireccionResponseMDF;
        txtEmail = (EditText)binding.txtEmailResponseMDF;
        txtContasena = (EditText)binding.txtContrasenaResponseDPF;
        btnModifica = (Button)binding.btnMoficiarDPF;
    }

    private void setingsPropirties() {
        int idUsuario = GlobalPreferences.getLoggedUserId(getActivity());

        txtNombre.setText("pepe");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
