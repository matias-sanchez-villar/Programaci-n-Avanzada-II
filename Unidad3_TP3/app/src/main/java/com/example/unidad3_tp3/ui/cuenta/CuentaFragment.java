package com.example.unidad3_tp3.ui.cuenta;

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

import com.example.unidad3_tp3.ParqueosDrawer;
import com.example.unidad3_tp3.R;
import com.example.unidad3_tp3.databinding.FragmentCuentaBinding;

import java.util.ArrayList;

public class CuentaFragment extends Fragment {

    private FragmentCuentaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CuentaViewModel galleryViewModel =
                new ViewModelProvider(this).get(CuentaViewModel.class);

        binding = FragmentCuentaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}