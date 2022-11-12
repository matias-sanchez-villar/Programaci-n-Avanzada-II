package com.example.donapp.Activity.ui.Estadisticas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.donapp.Data.Campania.CampaniaRepository;
import com.example.donapp.Entity.Campania;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.databinding.FragmentEstadisticasBinding;

import java.util.ArrayList;
import java.util.Iterator;


public class EstadisticasFragment extends Fragment{

    private FragmentEstadisticasBinding binding;
    TextView txtCantCampania, txtPromAsistencia, txtCantMax, txtCantMin;
    CampaniaRepository campaniaRepository;
    ArrayList<Campania> listCampania;

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
        campaniaRepository = new CampaniaRepository(getActivity());
        txtCantCampania = binding.txtEstCantCampaniasResponse;
        txtPromAsistencia = binding.txtEstPromAsistenciaResponse;
        txtCantMax = binding.txtEstCantMaxDonantesResponse;
        txtCantMin = binding.txtEstCantMinDonantesResponse;
    }

    public void instanceLayouts(){
        listCampania = campaniaRepository.selectEntityList(GlobalPreferences.getLoggedUserId(getActivity()));
        txtCantCampania.setText(listCampania.size());
        txtPromAsistencia.setText(promDonantes(listCampania));
        txtCantMin.setText(listCampania.get(0).getCantDonantes());
        txtCantMax.setText(listCampania.get(listCampania.size()-1).getCantDonantes());
    }

    public int promDonantes(ArrayList<Campania> list){
        int cant = 0;
        for (int x=0; x < list.size(); x++){
            Campania campania = list.get(x);
            cant += campania.getCantDonantes();
        }
        return cant / list.size();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
