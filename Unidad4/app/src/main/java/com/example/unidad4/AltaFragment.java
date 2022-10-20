package com.example.unidad4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class AltaFragment extends Fragment {

    private EditText txtId, txtNombreProducto, txtStock;
    private Spinner spnCategorias;
    private Button btnAgregar;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public AltaFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alta, container, false);
        fillProperties();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBtnAgregar();
            }
        });
        return view;
    }

    public void fillProperties(){
        txtId = (EditText)view.findViewById(R.id.txtId);
        txtNombreProducto = (EditText)view.findViewById(R.id.txtNombreProducto);
        txtStock = (EditText)view.findViewById(R.id.txtStock);
        spnCategorias = (Spinner) view.findViewById(R.id.spnCategorias);
        btnAgregar = (Button) view.findViewById(R.id.btnAgregar);

        //No
        String [] nombres = {"pepe", "jose", "carolina", "luicio"};
        //Si
        ArrayAdapter<String> categorias = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                nombres
        );
        spnCategorias.setAdapter(categorias);
        spnCategorias.setPrompt("Categorias");
    }

    public void onClickBtnAgregar(){
        String id = txtId.getText().toString();

        //seteamos
        txtNombreProducto.setText("pepe");
        txtStock.setText("22");

    }
}