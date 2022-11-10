package com.example.donapp.Activity.ui.MisDatos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;

import com.example.donapp.databinding.FragmentMisDatosPersonaJuridicaBinding;

public class MisDatosPersonaJuridicaFragment extends Fragment{

    private FragmentMisDatosPersonaJuridicaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MisDatosPersonaJuridicaViewModel misDatosPersonaJuridicaViewModel =
                new ViewModelProvider(this).get(MisDatosPersonaJuridicaViewModel.class);

        binding = FragmentMisDatosPersonaJuridicaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
