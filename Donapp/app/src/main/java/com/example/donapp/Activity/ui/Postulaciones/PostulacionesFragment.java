package com.example.donapp.Activity.ui.Postulaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.donapp.Activity.ui.BancosDeSangre.BancosDeSangreViewModel;
import com.example.donapp.Data.Postulacion.PostulacionRepository;
import com.example.donapp.databinding.FragmentBancosDeSangreBinding;
import com.example.donapp.databinding.FragmentPostulacionesBinding;

public class PostulacionesFragment extends Fragment{

    private FragmentPostulacionesBinding binding;
    ListView listView;
    PostulacionRepository _postulacionRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PostulacionesViewModel postulacionesViewModel =
                new ViewModelProvider(this).get(PostulacionesViewModel.class);

        binding = FragmentPostulacionesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        _postulacionRepository = new PostulacionRepository(getActivity());

        instanceLayouts();
        getDBInfo();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void instanceLayouts(){
        listView = binding.listPostulaciones;
    }

    public void getDBInfo(){
        _postulacionRepository.selectAllForListView(listView);
    }
}
