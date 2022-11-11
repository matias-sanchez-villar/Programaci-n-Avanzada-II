package com.example.donapp.Activity.ui.MisDatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;

import com.example.donapp.Activity.EditarMisDatosActivity;
import com.example.donapp.Data.Persona.PersonaFisicaRepository;
import com.example.donapp.Data.Persona.PersonaRepository;
import com.example.donapp.Data.Usuario.UsuarioRepository;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.PersonaFisica;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.databinding.FragmentMisDatosPersonaFisicaBinding;

public class MisDatosPersonaFisicaFragment extends Fragment{

    FragmentMisDatosPersonaFisicaBinding binding;
    TextView txtNombre, txtTelefono, txtDireccion, txtProvincia, txtEmail,
            txtLocalidad, txtApellido, txtDNI, txtFechaNacimiento, txtContasena;
    Button btnModifica;
    PersonaFisica personaFisica;
    PersonaFisicaRepository _personaFisicaRepository;
    UsuarioRepository _usuarioRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MisDatosPersonaFisicaViewModel misDatosViewModel =
                new ViewModelProvider(this).get(MisDatosPersonaFisicaViewModel.class);

        _personaFisicaRepository = new PersonaFisicaRepository(getActivity());
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
                Intent misSolicitudesIntent = new Intent(getActivity(), EditarMisDatosActivity.class);
                startActivity(misSolicitudesIntent);
            }
        });
    }

    private void fillProperties() {
        txtNombre = binding.txtNombreResponseMDF;
        txtApellido = binding.txtApellidoResponseMDF;
        txtFechaNacimiento = binding.txtFechaNacimientoResponseMDF;
        txtDNI = binding.txtDNIResponseMDF;
        txtTelefono = binding.txtTelefonoResponseMDF;
        txtProvincia = binding.txtProvinciaResponseMDF;
        txtLocalidad = binding.txtLocalidadResponseMDF;
        txtDireccion = binding.txtDireccionResponseMDF;
        txtEmail = binding.txtEmailResponseMDF;
        txtContasena = binding.txtContrasenaResponseDPF;
        btnModifica = binding.btnMoficiarDPF;
    }

    private void setingsPropirties() {
        Usuario usuario = searchUsuario();
        personaFisica = _personaFisicaRepository.selectEntityByUserId(
                GlobalPreferences.getLoggedUserId(getActivity())
        );

        txtNombre.setText(personaFisica.getNombre());
        txtApellido.setText(personaFisica.getApellido());
        txtFechaNacimiento.setText(personaFisica.getFechaNacimiento().toString());
        txtDNI.setText(String.valueOf(personaFisica.getDni()));
        txtTelefono.setText(String.valueOf(personaFisica.getTelefono()));
        txtProvincia.setText(personaFisica.getProvincia().getNombre());
        txtLocalidad.setText(personaFisica.getLocalidad().getNombre());
        txtDireccion.setText(personaFisica.getDireccion());
        txtEmail.setText(usuario.getEmail());
        txtContasena.setText(usuario.getPassword());
    }

    public Usuario searchUsuario(){
        Usuario usuario = GlobalPreferences.getLoggedUserNamePass(getActivity());
        _usuarioRepository = new UsuarioRepository(getActivity());

        Usuario usuarioToSearch =
                new Usuario(
                        usuario.getNombreUsuario(),
                        usuario.getPassword());

        return _usuarioRepository.selectEntity(usuarioToSearch);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
