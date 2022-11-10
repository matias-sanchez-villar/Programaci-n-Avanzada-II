package com.example.donapp.Activity.ui.MisDatos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;

import com.example.donapp.databinding.FragmentMisDatosPersonaFisicaBinding;

public class MisDatosPersonaFisicaFragment extends Fragment{

    private FragmentMisDatosPersonaFisicaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MisDatosPersonaFisicaViewModel misDatosViewModel =
                new ViewModelProvider(this).get(MisDatosPersonaFisicaViewModel.class);

        binding = FragmentMisDatosPersonaFisicaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
