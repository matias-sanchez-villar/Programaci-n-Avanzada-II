package com.example.donapp.Activity.ui.HistorialMedico;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.donapp.Activity.AltaHistorialMedicoActivity;
import com.example.donapp.Data.HistorialMedico.HistorialMedicoRepository;
import com.example.donapp.Data.Postulacion.PostulacionRepository;
import com.example.donapp.Entity.GlobalPreferences;
import com.example.donapp.Entity.HistorialMedico;
import com.example.donapp.Entity.Usuario;
import com.example.donapp.Enums.TipoSangre;
import com.example.donapp.databinding.FragmentHistorialMedicoBinding;

public class HistorialMedicoFragment extends Fragment{

    Button btnAltaHistorialMedico;
    ListView lvHistorialPostulaciones;
    TextView sinPostulacionesTxt;
    TextView fragmentTittle;
    ScrollView historialMainScrollView;
    HistorialMedicoRepository _historialMedicoRepository;
    PostulacionRepository _postulacionRepository;
    HistorialMedico historialMedico;
    TextView tipoSangre, peso, altura, fechaDonacion, tatuajes, vacunas,
            examenSangre, revisionMedica, tratamientoDental, endoscopia,
            embaraso, enfermedad, operacion, viaje, anemia, accidentes,
            usoMedicamentes, hepatitis;


    private FragmentHistorialMedicoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistorialMedicoViewModel historialMedicoViewModel =
                new ViewModelProvider(this).get(HistorialMedicoViewModel.class);

        binding = FragmentHistorialMedicoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        _historialMedicoRepository = new HistorialMedicoRepository(getActivity());
        _postulacionRepository = new PostulacionRepository(getActivity());

        instanceLayouts();
        setListeners();
        getDbInfo();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void instanceLayouts(){
        btnAltaHistorialMedico = binding.btnAltaHistorialMedico;
        lvHistorialPostulaciones = binding.historialPostulaciones;
        sinPostulacionesTxt = binding.titlePostulacionesFragmentHistorialMedico;
        fragmentTittle = binding.titleFragmentHistorialMedico;
        historialMainScrollView = binding.scrollFragmentHistorialMedico;

        tipoSangre = binding.tipoSangreDHM;
        peso = binding.pesoDHM;
        altura = binding.alturaDHM;
        fechaDonacion = binding.ultimaDonacionDHM;
        tatuajes = binding.tatuajeDHM;
        vacunas = binding.vacunaDHM;
        examenSangre = binding.examenDHM;
        revisionMedica = binding.revisionDHM;
        tratamientoDental = binding.tratamientoDHM;
        endoscopia = binding.endoscopiaDHM;
        embaraso = binding.embarazoDHM;
        enfermedad = binding.enfermedadDHM;
        operacion = binding.operacionDHM;
        viaje = binding.viajeDHM;
        anemia = binding.anemiaDHM;
        accidentes = binding.accidenteDHM;
        usoMedicamentes = binding.medicamentosDHM;
        hepatitis = binding.hepatitisDHM;
    }

    public void setListeners(){
        btnAltaHistorialMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateHistorialMedico();
            }
        });
    }

    public void updateHistorialMedico(){
        Intent altaHistorialMedico = new Intent(getActivity(), AltaHistorialMedicoActivity.class);
        altaHistorialMedico.putExtra("historialMedicoUpdate", historialMedico != null
                ? historialMedico
                : new HistorialMedico()
        );
        startActivity(altaHistorialMedico);
    }

    public void getDbInfo(){

        historialMedico = _historialMedicoRepository.selectEntity(
                new HistorialMedico(
                        new Usuario(GlobalPreferences.getLoggedUserId(getActivity()))
                )
        );

        if(historialMedico != null){
            fragmentTittle.setText("Historial Médico");
            btnAltaHistorialMedico.setText("EDITAR HISTORIAL MÉDICO");
            sinPostulacionesTxt.setText("Historial Postulaciones");
            setProperties(historialMedico);
        }
        else historialMainScrollView.setVisibility(View.GONE);


        _postulacionRepository.selectAllById(
                lvHistorialPostulaciones,
                GlobalPreferences.getLoggedUserId(getActivity())
        );
    }

    public void setProperties(HistorialMedico historialMedico){
        tipoSangre.setText(TipoSangre.getStringByPosition(historialMedico.getTipoSangre().ordinal()));
        peso.setText(String.valueOf(historialMedico.getPeso()));
        altura.setText(historialMedico.getAltura().toString());
        fechaDonacion.setText(historialMedico.getUltimaDonacion().toString());
        tatuajes.setText(historialMedico.isTatuajes() == true ? "si" : "no");
        vacunas.setText(historialMedico.isVacunaAlergia() == true ? "si" : "no");
        examenSangre.setText(historialMedico.isExamenSangre() == true ? "si" : "no");
        revisionMedica.setText(historialMedico.isRevisionMedica() == true ? "si" : "no");
        tratamientoDental.setText(historialMedico.isTratamientoDental() == true ? "si" : "no");
        endoscopia.setText(historialMedico.isEndoscopia() == true ? "si" : "no");
        embaraso.setText(historialMedico.isEmbarazo() == true ? "si" : "no");
        enfermedad.setText(historialMedico.isEnfermedaCronica() == true ? "si" : "no");
        operacion.setText(historialMedico.isOperacion() == true ? "si" : "no");
        viaje.setText(historialMedico.isViaje() == true ? "si" : "no");
        anemia.setText(historialMedico.isAnemia() == true ? "si" : "no");
        accidentes.setText(historialMedico.isAccidenteVascular() == true ? "si" : "no");
        usoMedicamentes.setText(historialMedico.isUsaMedicamentos() == true ? "si" : "no");
        hepatitis.setText(historialMedico.isHepatitis() == true ? "si" : "no");
    }

}
