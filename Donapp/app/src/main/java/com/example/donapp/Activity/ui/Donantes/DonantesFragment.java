package com.example.donapp.Activity.ui.Donantes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.donapp.databinding.FragmentDonantesBinding;

public class DonantesFragment extends Fragment{

    private FragmentDonantesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DonantesViewModel donantesViewModel =
                new ViewModelProvider(this).get(DonantesViewModel.class);

        binding = FragmentDonantesBinding.inflate(inflater, container, false);
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
