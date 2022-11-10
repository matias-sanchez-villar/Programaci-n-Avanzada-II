package com.example.donapp.Activity.ui.InstitucionesMedicas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InstitucionesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public InstitucionesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is instituciones fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
