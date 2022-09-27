package com.example.unidad3_tp3.ui.parqueo;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.unidad3_tp3.AdminSQLite;
import com.example.unidad3_tp3.MainActivity;
import com.example.unidad3_tp3.ParqueosDrawer;
import com.example.unidad3_tp3.R;
import com.example.unidad3_tp3.databinding.FragmentParqueosBinding;

import java.util.ArrayList;

public class ParqueoFragment extends Fragment {

    private FragmentParqueosBinding binding;
    private GridView Lista;
    private int userId;
    AdminSQLite helper;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ParqueoViewModel homeViewModel =
                new ViewModelProvider(this).get(ParqueoViewModel.class);

        binding = FragmentParqueosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View view = inflater.inflate(R.layout.fragment_parqueos, container, false);

        Lista = (GridView) view.findViewById(R.id.GridViewParqueos);

        // Instanciamos Helper
        helper = new AdminSQLite(getContext(), "DB_TP3",null,1);

        Bundle bundle = getArguments();
        if(bundle != null){
            userId = bundle.getInt("userId");
            LlenarGridView();
        }

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void LlenarGridView(){
        helper.abrirDB();

        Cursor c = helper.LeerParqueos(userId);
        String Matricula = "", Tiempo = "";
        ArrayList<String> item = new ArrayList<String>();
        if(c.moveToFirst()){
            Matricula = c.getString(0);
            Tiempo = c.getString(1);
            item.add(Matricula+" "+Tiempo);
        }while (c.moveToNext());

        helper.cerarDB();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.fragment_parqueos,item);

        Lista.setAdapter(adapter);
    }

}