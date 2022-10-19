package com.example.unidad4;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ModificarFragment extends Fragment {

    private EditText txtId, txtNombreProducto, txtStock;
    private Spinner spnCategorias;
    private Button btnBuscar, btnModificar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ModificarFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_modificar, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        txtId = (EditText)getView().findViewById(R.id.txtId);
        txtNombreProducto = (EditText)getView().findViewById(R.id.txtNombreProducto);
        txtStock = (EditText)getView().findViewById(R.id.txtStock);
        spnCategorias = (Spinner) getView().findViewById(R.id.spnCategorias);
        btnBuscar = (Button) getView().findViewById(R.id.btnBuscar);
        btnModificar = (Button) getView().findViewById(R.id.btnModificar);

        //No
        String [] nombres = {"pepe", "jose", "carolina", "luicio"};
        //Si
        ArrayAdapter <String> categorias = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                nombres
        );
        spnCategorias.setAdapter(categorias);
        spnCategorias.setPrompt("Categorias");
    }

    public void onClickBtnBuscar(View view){
        toast("Buscando");
        String id = txtId.getText().toString();

        //seteamos
        txtNombreProducto.setText("pepe");
        txtStock.setText("22");
    }

    public void onClickBtnModificar(View view){
        toast("Modificar");
        String Stock = txtStock.getText().toString();
        String nombreProducto = txtNombreProducto.getText().toString();
        String categoria = spnCategorias.getSelectedItem().toString();
        //Modificar
    }

    public void toast(String txt) {
        Toast.makeText(getActivity(), txt, Toast.LENGTH_SHORT).show();
    }

}