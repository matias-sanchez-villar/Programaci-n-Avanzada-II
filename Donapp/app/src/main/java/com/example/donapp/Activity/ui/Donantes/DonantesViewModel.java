package com.example.donapp.Activity.ui.Donantes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class DonantesViewModel extends ViewModel{

    private final MutableLiveData<String> mText;

    public DonantesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is donantes fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
