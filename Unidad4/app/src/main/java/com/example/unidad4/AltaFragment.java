package com.example.unidad4;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.unidad4.Data.ArticuloRepository;
import com.example.unidad4.Data.DataCategoria;
import com.example.unidad4.Entity.Articulo;
import com.example.unidad4.Entity.Categoria;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AltaFragment extends Fragment {

    private EditText txtId, txtNombreProducto, txtStock;
    private Spinner spnCategorias;
    private Button btnAgregar;
    private Categoria categoriaSelected;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public AltaFragment() {}


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        spnCategorias = getView().findViewById(R.id.spnCategorias);
        getDBInfo();
        spnCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriaSelected = (Categoria) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

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
    }

    public void onClickBtnAgregar() {

        /**
         * TODO: AGREGAR VALIDACIONES ACA Y DESCOMENTAR

        **/

        int id = Integer.parseInt(txtId.getText().toString());
        String nombre = txtNombreProducto.getText().toString();
        int stock = Integer.parseInt(txtStock.getText().toString());

        ArticuloRepository thread = new ArticuloRepository(
                new Articulo(
                        id,
                        nombre,
                        stock,
                        categoriaSelected.getId()),
                true,
                getActivity());
        try {
            String result = thread.execute().get();

            if(result == ArticuloRepository.OPERACION_EXITOSA){
                txtId.setText("");
                txtStock.setText("");
                txtNombreProducto.setText("");

                Toast.makeText(
                        getActivity().getApplicationContext(),
                        "Producto agregado",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(
                        getActivity().getApplicationContext(),
                        "Hubo un error al agregar",
                        Toast.LENGTH_LONG).show();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getDBInfo(){
        DataCategoria threadActivity = new DataCategoria(spnCategorias, getActivity());
        threadActivity.execute();
    }
}