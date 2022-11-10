package com.example.donapp.Activity.ui.Postulaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.donapp.Activity.ui.BancosDeSangre.BancosDeSangreViewModel;
import com.example.donapp.databinding.FragmentBancosDeSangreBinding;
import com.example.donapp.databinding.FragmentPostulacionesBinding;

public class PostulacionesFragment extends Fragment{

    private FragmentPostulacionesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PostulacionesViewModel postulacionesViewModel =
                new ViewModelProvider(this).get(PostulacionesViewModel.class);

        binding = FragmentPostulacionesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
