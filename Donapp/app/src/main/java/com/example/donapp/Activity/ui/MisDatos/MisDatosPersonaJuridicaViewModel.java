package com.example.donapp.Activity.ui.MisDatos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MisDatosPersonaJuridicaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MisDatosPersonaJuridicaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is historial medico fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
