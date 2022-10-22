package com.example.unidad4;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import java.util.concurrent.ExecutionException;

public class ModificarFragment extends Fragment {

    private Articulo articulo;
    private Categoria categoriaSelected;
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        spnCategorias = getView().findViewById(R.id.spnCategorias);
        spnCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriaSelected = (Categoria) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getDBInfo();
    }

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
    }

    public void onClickBtnBuscar(){

        /**
         * TODO: AGREGAR VALIDACIONES ACA Y DESCOMENTAR

         **/

        int id = Integer.parseInt(txtId.getText().toString());

        ArticuloRepository thread = new ArticuloRepository(id);

        try {
           String result = thread.execute().get();

           if(result == ArticuloRepository.OPERACION_EXITOSA){

               articulo = thread.getArticulo();
              txtNombreProducto.setText(articulo.getNombre());

              txtStock.setText(String.valueOf(articulo.getStock()));

              spnCategorias.setSelection(articulo.getIdCategoria() - 1);
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

    public void onClickBtnModificar(){

        /**
         * TODO: AGREGAR VALIDACIONES ACA Y DESCOMENTAR

         **/

        int Stock = Integer.parseInt(txtStock.getText().toString());
        String nombreProducto = txtNombreProducto.getText().toString();

        if(articulo.getIdCategoria() != categoriaSelected.getId()){
            articulo.setIdCategoria(categoriaSelected.getId());
        }

        ArticuloRepository thread = new ArticuloRepository(articulo, false, getActivity());

        try {
            String result = thread.execute().get();

            if(result == ArticuloRepository.OPERACION_EXITOSA){
                txtId.setText("");
                txtStock.setText("");
                txtNombreProducto.setText("");

                Toast.makeText(
                        getActivity().getApplicationContext(),
                        "Producto modificado",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(
                        getActivity().getApplicationContext(),
                        "Hubo un error al modificar",
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