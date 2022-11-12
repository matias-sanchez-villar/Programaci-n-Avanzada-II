package com.example.donapp.Activity.ui.BancosDeSangre;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.donapp.Activity.DetalleBancoSangreActivity;
import com.example.donapp.Data.BancoSangre.BancoSangreRepository;
import com.example.donapp.Entity.BancoSangre;
import com.example.donapp.databinding.FragmentBancosDeSangreBinding;

public class BancosDeSangreFragment extends Fragment{

    private FragmentBancosDeSangreBinding binding;
    ListView vtBancoSangre;
    BancoSangreRepository bancoSangreRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BancosDeSangreViewModel bancosDeSangreViewModel =
                new ViewModelProvider(this).get(BancosDeSangreViewModel.class);

        binding = FragmentBancosDeSangreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fillProperties();
        setListeners();

        return root;
    }

    public void fillProperties(){
        vtBancoSangre = binding.lvBancoSangre;
        bancoSangreRepository = new BancoSangreRepository(getActivity());
        bancoSangreRepository.selectAllForListView(vtBancoSangre);
    }

    public void setListeners(){
        vtBancoSangre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BancoSangre bancoSangre = (BancoSangre) parent.getItemAtPosition(position);
                Intent detalleIntent = new Intent(getActivity(), DetalleBancoSangreActivity.class);
                detalleIntent.putExtra("bancoSangre_id", bancoSangre.getId());
                startActivity(detalleIntent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
