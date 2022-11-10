package com.example.donapp.Activity.ui.HistorialMedico;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.donapp.databinding.FragmentHistorialMedicoBinding;

public class HistorialMedicoFragment extends Fragment{

    private FragmentHistorialMedicoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistorialMedicoViewModel historialMedicoViewModel =
                new ViewModelProvider(this).get(HistorialMedicoViewModel.class);

        binding = FragmentHistorialMedicoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
