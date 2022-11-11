package com.example.donapp.Activity.ui.HistorialMedico;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.donapp.Activity.AltaHistorialMedicoActivity;
import com.example.donapp.Data.HistorialMedico.HistorialMedicoRepository;
import com.example.donapp.Data.Postulacion.PostulacionRepository;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.HistorialMedico;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.databinding.FragmentHistorialMedicoBinding;

public class HistorialMedicoFragment extends Fragment{

    Button btnAltaHistorialMedico;
    ListView lvHistorialPostulaciones;
    TextView sinPostulacionesTxt;
    TextView fragmentTittle;
    ScrollView historialMainScrollView;
    HistorialMedicoRepository _historialMedicoRepository;
    PostulacionRepository _postulacionRepository;
    HistorialMedico historialMedico;


    private FragmentHistorialMedicoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistorialMedicoViewModel historialMedicoViewModel =
                new ViewModelProvider(this).get(HistorialMedicoViewModel.class);

        binding = FragmentHistorialMedicoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        _historialMedicoRepository = new HistorialMedicoRepository(getActivity());
        _postulacionRepository = new PostulacionRepository(getActivity());

        instanceLayouts();
        setListeners();
        getDbInfo();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void instanceLayouts(){
        btnAltaHistorialMedico = binding.btnAltaHistorialMedico;
        lvHistorialPostulaciones = binding.historialPostulaciones;
        sinPostulacionesTxt = binding.titlePostulacionesFragmentHistorialMedico;
        fragmentTittle = binding.titleFragmentHistorialMedico;
        historialMainScrollView = binding.scrollFragmentHistorialMedico;
    }

    public void setListeners(){
        btnAltaHistorialMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateHistorialMedico();
            }
        });
    }

    public void updateHistorialMedico(){
        Intent altaHistorialMedico = new Intent(getActivity(), AltaHistorialMedicoActivity.class);
        altaHistorialMedico.putExtra("historialMedicoUpdate", historialMedico != null
                ? historialMedico
                : new HistorialMedico()
        );
        startActivity(altaHistorialMedico);
    }

    public void getDbInfo(){

        historialMedico = _historialMedicoRepository.selectEntity(
                new HistorialMedico(
                        new Usuario(GlobalPreferences.getLoggedUserId(getActivity()))
                )
        );

        if(historialMedico != null){
            fragmentTittle.setText("Historial Médico");
            btnAltaHistorialMedico.setText("EDITAR HISTORIAL MÉDICO");
            sinPostulacionesTxt.setText("Historial Postulaciones");
        } else {
         historialMainScrollView.setVisibility(View.GONE);
        }

        _postulacionRepository.selectAllById(
                lvHistorialPostulaciones,
                GlobalPreferences.getLoggedUserId(getActivity())
        );
    }
}
