package com.example.donapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.donapp.Entity.Solicitud;
import com.example.donapp.R;

public class ListBancoSangre extends AppCompatActivity {

    ListView vtBancoSangre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_banco_sangre);

        vtBancoSangre = (ListView) findViewById(R.id.lvBancoSangre);
        setListeners();
    }

    public void setListeners(){
        // Instancia de list view
        vtBancoSangre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Solicitud solicitudSelected = (Solicitud) parent.getItemAtPosition(position);

            }
        });
    }

}