package com.example.donapp.Activity.ui.InstitucionesMedicas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.donapp.Data.InstitucionesMedicas.InstitucionMedicaRepository;
import com.example.donapp.databinding.FragmentInstitucionesMedicasBinding;

public class InstitucionesMedicasFragment extends Fragment{

    private FragmentInstitucionesMedicasBinding binding;
    ListView listView;
    InstitucionMedicaRepository _institucionMedicaRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InstitucionesViewModel institucionesViewModel =
                new ViewModelProvider(this).get(InstitucionesViewModel.class);

        binding = FragmentInstitucionesMedicasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        _institucionMedicaRepository = new InstitucionMedicaRepository(getActivity());

        instanceLayouts();
        setListeners();
        getDBInfo();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void instanceLayouts(){
        listView = (ListView) binding.listInstitucionMedicaFragment;
    }

    public void setListeners(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public void getDBInfo(){
        _institucionMedicaRepository.selectAllForListView(listView);
    }
}
