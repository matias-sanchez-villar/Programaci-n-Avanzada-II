package com.example.donapp.Activity.ui.Donantes;

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

import com.example.donapp.Data.Donantes.DonanteRepository;
import com.example.donapp.Data.InstitucionesMedicas.InstitucionMedicaRepository;
import com.example.donapp.databinding.FragmentDonantesBinding;

public class DonantesFragment extends Fragment{

    private FragmentDonantesBinding binding;
    ListView listView;
    DonanteRepository _donanteRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DonantesViewModel donantesViewModel =
                new ViewModelProvider(this).get(DonantesViewModel.class);

        binding = FragmentDonantesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        _donanteRepository = new DonanteRepository(getActivity());

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
        listView = (ListView) binding.listDonantesFragment;
    }

    public void setListeners(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public void getDBInfo(){
        _donanteRepository.selectAllForListView(listView);
    }
}
