package com.example.donapp.Activity.ui.BancosDeSangre;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BancosDeSangreViewModel extends ViewModel{
    private final MutableLiveData<String> mText;

    public BancosDeSangreViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is bancos de sangre fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
