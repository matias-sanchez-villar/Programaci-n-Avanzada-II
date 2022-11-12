package com.example.donapp.Activity.ui.Solicitudes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.donapp.Activity.DetalleSolicitudActivity;
import com.example.donapp.Activity.MisSolicitudesActivity;
import com.example.donapp.Data.Solicitud.SolicitudRepository;
import com.example.donapp.Entity.Solicitud;
import com.example.donapp.Enums.StatusResponse;
import com.example.donapp.Util.Toastable;
import com.example.donapp.databinding.FragmentSolicitudesBinding;

import java.util.ArrayList;
import java.util.Locale;


public class SolicitudesFragment extends Fragment {

    private FragmentSolicitudesBinding binding;
    SearchView searchView;
    Button misSolicitudesButton;
    ListView listView;
    SolicitudRepository _solicitudRepository;
    ArrayList<String> searcheableProperties = new ArrayList<String>();
    String searcheablePropertie;
    Spinner spnSearcheableProperties;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SolicitudesViewModel solicitudesViewModel =
                new ViewModelProvider(this).get(SolicitudesViewModel.class);

        binding = FragmentSolicitudesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        _solicitudRepository = new SolicitudRepository(getActivity());

        searchView = (SearchView) binding.searchBuscarSolicitudes;

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
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String searcheable = newText;
                if(!searcheable.equals("") && !searcheablePropertie.equals("")){
                  StatusResponse response = _solicitudRepository.selectAllForListViewByStringPropertie(
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

                Solicitud solicitudSelected = (Solicitud) parent.getItemAtPosition(position);
                Intent detalleIntent = new Intent(getActivity(), DetalleSolicitudActivity.class);
                detalleIntent.putExtra("solicitud_id", solicitudSelected.getId());

                startActivity(detalleIntent);
            }
        });

        misSolicitudesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMisSolicitudes();
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
        _solicitudRepository.selectAllForListView(listView);
    }

    public void instanceLayouts(){
        listView = (ListView) binding.listSolicitudesFragment;
        misSolicitudesButton = (Button) binding.MisSolicitudesBtn;
    }

    public void fillProperties(){
        spnSearcheableProperties = (Spinner) binding.spnSearchSolicitud;

        // Propiedades buscables
        searcheableProperties.add("");
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

    public void goToMisSolicitudes(){
        Intent misSolicitudesIntent = new Intent(getActivity(), MisSolicitudesActivity.class);
        startActivity(misSolicitudesIntent);
    }
}