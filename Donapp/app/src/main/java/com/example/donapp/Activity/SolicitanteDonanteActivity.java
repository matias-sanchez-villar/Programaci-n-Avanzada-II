package com.example.donapp.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.donapp.databinding.ActivitySolicitanteDonanteBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.donapp.R;



public class SolicitanteDonanteActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivitySolicitanteDonanteBinding binding;
    TextView nombreUsuario;
    TextView mailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySolicitanteDonanteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarSolicitanteDonante.toolbar);
        binding.appBarSolicitanteDonante.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio_solicitante, R.id.nav_solicitudes_solicitante, R.id.nav_campanias_solicitante)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_solicitante_donante);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        instanceLayouts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.solicitante_donante, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_solicitante_donante);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void instanceLayouts(){
        View header = binding.navView.getHeaderView(0);
        nombreUsuario = (TextView) header.findViewById(R.id.navHeaderNombreUsuarioSolicitante);
        mailUsuario = (TextView) header.findViewById(R.id.navHeaderMailUsuarioSolicitante);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String nombre = preferences.getString("nombreUsuario", "");
        String mail = preferences.getString("mailUsuario", "");

        nombreUsuario.setText(nombre);
        mailUsuario.setText(mail);
    }
}