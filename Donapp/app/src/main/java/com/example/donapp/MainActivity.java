package com.example.donapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.fragment_listar__campana_); //para probar
        setContentView(R.layout.fragment_alta__campana_);//para probar
        //setContentView(R.layout.fragment_detalle__campana_);
    }
}