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
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Enums.TipoUsuario;
import com.example.donapp.Util.Toastable;
import com.example.donapp.databinding.FragmentCampaniasBinding;

import java.util.ArrayList;
import java.util.Locale;

public class CampaniasFragment extends Fragment {

    private FragmentCampaniasBinding binding;
    SearchView searchView;
    Button misCampaniasButton;
    ListView listView;
    CampaniaRepository _campaniaRepository;
    ArrayList<String> searcheableProperties = new ArrayList<String>();
    String searcheablePropertie;
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
                String searcheable = newText;
                if(!searcheable.equals("") && !searcheablePropertie.equals("")){
                    StatusResponse response = _campaniaRepository.selectAllForListViewByStringPropertie(
                            listView, searcheablePropertie.toLowerCase(Locale.ROOT), searcheable);

                    if(response == StatusResponse.FAIL){
                        Toastable.toast(getActivity(), "Hubo un error al recuperar los datos");
                    }
                } else if(searcheable.equals("")){
                    getDBInfo();
                }

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

        spnSearcheableProperties.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searcheablePropertie = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        searcheableProperties.add("Provincia");
        searcheableProperties.add("Localidad");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                searcheableProperties);

        // Set Adapter
        spnSearcheableProperties.setAdapter(adapter);

        if(GlobalPreferences.getLoggedTipoUsuario(getActivity()) != TipoUsuario.EMPRESA){
            misCampaniasButton.setVisibility(View.GONE);
        }
    }

    public void goToMisCampanias() {
        Intent misCampaniasIntent = new Intent(getActivity(), MisCampaniasActivity.class);
        startActivity(misCampaniasIntent);
    }

}