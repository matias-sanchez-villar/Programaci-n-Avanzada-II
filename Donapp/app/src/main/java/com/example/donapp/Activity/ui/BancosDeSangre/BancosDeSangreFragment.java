package com.example.donapp.Activity.ui.BancosDeSangre;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.donapp.Activity.ui.HistorialMedico.HistorialMedicoViewModel;
import com.example.donapp.databinding.FragmentBancosDeSangreBinding;

public class BancosDeSangreFragment extends Fragment{

    private FragmentBancosDeSangreBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BancosDeSangreViewModel bancosDeSangreViewModel =
                new ViewModelProvider(this).get(BancosDeSangreViewModel.class);

        binding = FragmentBancosDeSangreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
