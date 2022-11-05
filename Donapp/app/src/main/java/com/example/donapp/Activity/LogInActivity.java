package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.donapp.R;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void login(View view){

    }

    public void registrar(View view){
        Intent tipoUsuarioActivity = new Intent(this, TipoUsuarioActivity.class);
        startActivity(tipoUsuarioActivity);
    }
}