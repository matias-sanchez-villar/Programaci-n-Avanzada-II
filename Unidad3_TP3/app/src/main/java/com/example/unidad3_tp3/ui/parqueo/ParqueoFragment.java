package com.example.unidad3_tp3.ui.parqueo;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.unidad3_tp3.AdminSQLite;
import com.example.unidad3_tp3.R;
import com.example.unidad3_tp3.databinding.FragmentParqueosBinding;

import java.util.ArrayList;

public class ParqueoFragment extends Fragment {

    private FragmentParqueosBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ParqueoViewModel homeViewModel =
                new ViewModelProvider(this).get(ParqueoViewModel.class);

        binding = FragmentParqueosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}