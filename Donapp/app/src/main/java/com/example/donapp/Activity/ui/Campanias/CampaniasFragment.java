package com.example.donapp.Activity.ui.Campanias;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.donapp.Activity.DetalleCampaniaActivity;
import com.example.donapp.Activity.MisCampaniasActivity;
import com.example.donapp.Data.Campania.CampaniaRepository;
import com.example.donapp.Entity.Campania;
import com.example.donapp.databinding.FragmentCampaniasBinding;

import java.util.ArrayList;

public class CampaniasFragment extends Fragment {

    private FragmentCampaniasBinding binding;
    SearchView searchView;
    Button misCampaniasButton;
    ListView listView;
    CampaniaRepository _campaniaRepository;
    ArrayList<String> searcheableProperties = new ArrayList<String>();
    Spinner spnSearcheableProperties;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CampaniasViewModel campaniasViewModel =
                new ViewModelProvider(this).get(CampaniasViewModel.class);

        binding = FragmentCampaniasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        _campaniaRepository = new CampaniaRepository(getActivity());

        searchView = (SearchView) binding.searchBuscarCampanias;

        instanceLayouts();
        fillProperties();
        setListeners();
        getDBInfo();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void setListeners(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Campania campaniaSelected = (Campania) parent.getItemAtPosition(position);
                Intent detalleIntent = new Intent(getActivity(), DetalleCampaniaActivity.class);
                detalleIntent.putExtra("campania_id", campaniaSelected.getId());

                startActivity(detalleIntent);
            }
        });

        misCampaniasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMisCampanias();
            }
        });
    }

    public void getDBInfo(){
        _campaniaRepository.selectAllForListView(listView);
    }

    public void instanceLayouts(){
        listView = (ListView) binding.listCampaniasFragment;
        misCampaniasButton = (Button) binding.MisCampaniasBtn;
    }

    public void fillProperties(){
        spnSearcheableProperties = (Spinner) binding.spnSearchCampanias;

        // Propiedades buscables
        searcheableProperties.add("Codigo");
        searcheableProperties.add("Nombre");
        searcheableProperties.add("Criticidad");
        searcheableProperties.add("Provincia");
        searcheableProperties.add("Localidad");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                searcheableProperties);

        // Set Adapter
        spnSearcheableProperties.setAdapter(adapter);

        //
    }

    public void goToMisCampanias() {
        Intent misCampaniasIntent = new Intent(getActivity(), MisCampaniasActivity.class);
        startActivity(misCampaniasIntent);
    }

}