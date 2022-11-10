package com.example.donapp.Activity.ui.Estadisticas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.donapp.databinding.FragmentEstadisticasBinding;


public class EstadisticasFragment extends Fragment{

    private FragmentEstadisticasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EstadisticasViewModel estadisticasViewModel =
                new ViewModelProvider(this).get(EstadisticasViewModel.class);

        binding = FragmentEstadisticasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // final TextView textView = binding.textSlideshow;
        // slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
