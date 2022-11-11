package com.example.donapp.Activity.ui.MisDatos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;

import com.example.donapp.databinding.FragmentMisDatosPersonaJuridicaBinding;

public class MisDatosPersonaJuridicaFragment extends Fragment{

    private FragmentMisDatosPersonaJuridicaBinding binding;
    TextView txtNombre, txtTelefono, txtDireccion, txtProvincia, txtLocalidad, txtEmail,
            txtCUIL, txtHoraInicio, txtHoraFin, txtContasena;
    Button btnModifica;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MisDatosPersonaJuridicaViewModel misDatosPersonaJuridicaViewModel =
                new ViewModelProvider(this).get(MisDatosPersonaJuridicaViewModel.class);

        binding = FragmentMisDatosPersonaJuridicaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    private void fillProperties() {
        txtNombre = (EditText)binding.txtNombreResponseMDJ;
        txtCUIL = (EditText)binding.txtCuilResponseMDJ;
        txtHoraInicio = (EditText)binding.txtHoraInicioResponseMDJ;
        txtHoraFin = (EditText)binding.txtHoraCierreResponseMDJ;
        txtTelefono = (EditText)binding.txtTelefonoResponseMDJ;
        txtProvincia = (EditText)binding.txtProvinciaResponseMDJ;
        txtLocalidad = (EditText)binding.txtLocalidadResponseMDJ;
        txtDireccion = (EditText)binding.txtDireccionResponseMDJ;
        txtEmail = (EditText)binding.txtEmailResponseMDJ;
        txtContasena = (EditText)binding.txtContrasenaResponseDPJ;
        btnModifica = (Button)binding.btnMoficiarDPJ;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
