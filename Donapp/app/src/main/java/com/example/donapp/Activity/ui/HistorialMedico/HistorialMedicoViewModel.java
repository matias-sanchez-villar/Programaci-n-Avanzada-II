package com.example.donapp.Activity.ui.HistorialMedico;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistorialMedicoViewModel extends ViewModel{
    private final MutableLiveData<String> mText;

    public HistorialMedicoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is historial medico fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
