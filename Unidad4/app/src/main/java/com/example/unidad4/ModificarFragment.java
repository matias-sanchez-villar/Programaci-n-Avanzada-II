package com.example.unidad4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.unidad4.Entity.Articulo;
import com.example.unidad4.Entity.Categoria;

public class ModificarFragment extends Fragment {

    private Articulo articulo;
    private Categoria categoria;
    private EditText txtId, txtNombreProducto, txtStock;
    private Spinner spnCategorias;
    private Button btnBuscar, btnModificar;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ModificarFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_modificar, container, false);
        fillProperties();
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBtnBuscar();
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBtnModificar();
            }
        });
        return view;
    }

    public void fillProperties(){
        txtId = (EditText)view.findViewById(R.id.txtId);
        txtNombreProducto = (EditText)view.findViewById(R.id.txtNombreProducto);
        txtStock = (EditText)view.findViewById(R.id.txtStock);
        spnCategorias = (Spinner) view.findViewById(R.id.spnCategorias);
        btnBuscar = (Button) view.findViewById(R.id.btnBuscar);
        btnModificar = (Button) view.findViewById(R.id.btnModificar);

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

    public void onClickBtnBuscar(){
        String id = txtId.getText().toString();

        //seteamos
        txtNombreProducto.setText("pepe");
        txtStock.setText("22");
        
    }

    public void onClickBtnModificar(){
        String Stock = txtStock.getText().toString();
        String nombreProducto = txtNombreProducto.getText().toString();
        String categoria = spnCategorias.getSelectedItem().toString();
        toast(categoria);
    }

    public void toast(String txt) {
        Toast.makeText(getActivity(), txt, Toast.LENGTH_SHORT).show();
    }

}