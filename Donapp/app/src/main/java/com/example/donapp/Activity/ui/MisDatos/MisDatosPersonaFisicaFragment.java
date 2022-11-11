package com.example.donapp.Activity.ui.MisDatos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;

import com.example.donapp.Data.Campania.CampaniaRepository;
import com.example.donapp.Data.Localidad.LocalidadRepository;
import com.example.donapp.Data.Provincia.ProvinciaRepository;
import com.example.donapp.R;
import com.example.donapp.databinding.FragmentMisDatosPersonaFisicaBinding;

public class MisDatosPersonaFisicaFragment extends Fragment{

    FragmentMisDatosPersonaFisicaBinding binding;
    TextView txtNombre, txtTelefono, txtDireccion, txtProvincia, txtEmail,
            txtLocalidad, txtApellido, txtDNI, txtFechaNacimiento, txtContasena;
    Button btnModifica;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MisDatosPersonaFisicaViewModel misDatosViewModel =
                new ViewModelProvider(this).get(MisDatosPersonaFisicaViewModel.class);

        binding = FragmentMisDatosPersonaFisicaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
