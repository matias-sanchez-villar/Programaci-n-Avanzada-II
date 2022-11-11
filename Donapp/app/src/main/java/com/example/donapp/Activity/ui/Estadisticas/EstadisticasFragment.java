package com.example.donapp.Activity.ui.Estadisticas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.donapp.databinding.FragmentEstadisticasBinding;


public class EstadisticasFragment extends Fragment{

    private FragmentEstadisticasBinding binding;
    TextView txCantCampania, txtPromAsistencia, txtPromInasistencia, txtPeriodoFecha;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EstadisticasViewModel estadisticasViewModel =
                new ViewModelProvider(this).get(EstadisticasViewModel.class);

        binding = FragmentEstadisticasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fillProperties();
        instanceLayouts();

        return root;
    }

    public void fillProperties(){
        txCantCampania = binding.txtEstCantCampaniasResponse;
        txtPromAsistencia = binding.txtEstPromAsistenciaResponse;
        txtPromInasistencia = binding.txtEstPromInasistenciaResponse;
        txtPeriodoFecha = binding.txtEstPeriodoFechaResponse;
    }

    public void instanceLayouts(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
