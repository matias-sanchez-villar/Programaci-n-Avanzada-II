package com.example.donapp.Activity.ui.MisDatos;

import android.content.Intent;
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

import com.example.donapp.Activity.EditarMisDatosActivity;
import com.example.donapp.Activity.MisSolicitudesActivity;
import com.example.donapp.Data.Persona.PersonaJuridicaRepository;
import com.example.donapp.Data.Persona.PersonaRepository;
import com.example.donapp.Data.Persona.ReadPersonaFisicaAsync;
import com.example.donapp.Data.Persona.ReadPersonaJuridicaAsync;
import com.example.donapp.Data.Usuario.UsuarioRepository;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.PersonaJuridica;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.databinding.FragmentMisDatosPersonaJuridicaBinding;

public class MisDatosPersonaJuridicaFragment extends Fragment{

    private FragmentMisDatosPersonaJuridicaBinding binding;
    TextView txtNombre, txtTelefono, txtDireccion, txtProvincia, txtLocalidad, txtEmail,
            txtCUIL, txtHoraInicio, txtHoraFin, txtContasena;
    Button btnModifica;
    PersonaJuridica personaJuridica;
    PersonaJuridicaRepository _personaJuridicaRepository;
    UsuarioRepository _usuarioRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MisDatosPersonaJuridicaViewModel misDatosPersonaJuridicaViewModel =
                new ViewModelProvider(this).get(MisDatosPersonaJuridicaViewModel.class);

        _personaJuridicaRepository = new PersonaJuridicaRepository(getActivity());

        binding = FragmentMisDatosPersonaJuridicaBinding.inflate(inflater, container, false);
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
                //TODO: EditarMisDatos.class
                Intent editarMisDatos = new Intent(getActivity(), EditarMisDatosActivity.class);
                startActivity(editarMisDatos);
            }
        });
    }

    private void fillProperties() {
        txtNombre = binding.txtNombreResponseMDJ;
        txtCUIL = binding.txtCuilResponseMDJ;
        txtHoraInicio = binding.txtHoraInicioResponseMDJ;
        txtHoraFin = binding.txtHoraCierreResponseMDJ;
        txtTelefono = binding.txtTelefonoResponseMDJ;
        txtProvincia = binding.txtProvinciaResponseMDJ;
        txtLocalidad = binding.txtLocalidadResponseMDJ;
        txtDireccion = binding.txtDireccionResponseMDJ;
        txtEmail = binding.txtEmailResponseMDJ;
        txtContasena = binding.txtContrasenaResponseDPJ;
        btnModifica = binding.btnMoficiarDPJ;
    }

    private void setingsPropirties() {
        personaJuridica = _personaJuridicaRepository.selectEntityByUserId(
                GlobalPreferences.getLoggedUserId(getActivity())
        );

        txtNombre.setText(personaJuridica.getNombre());
        txtCUIL.setText(personaJuridica.getCuil());
        txtHoraInicio.setText(personaJuridica.getHorarioInicio());
        txtHoraFin.setText(personaJuridica.getHorarioFin());
        txtTelefono.setText(String.valueOf(personaJuridica.getTelefono()));
        txtProvincia.setText(personaJuridica.getProvincia().getNombre());
        txtLocalidad.setText(personaJuridica.getLocalidad().getNombre());
        txtDireccion.setText(personaJuridica.getDireccion());
        txtEmail.setText(GlobalPreferences.getLoggedUserMail(getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
