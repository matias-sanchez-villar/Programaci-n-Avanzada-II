package com.example.donapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.donapp.Entity.GlobalPreferences;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.donapp.R;

import com.example.donapp.databinding.ActivityEmpresaBinding;

public class EmpresaActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityEmpresaBinding binding;

    TextView nombreUsuario;
    TextView mailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEmpresaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarEmpresa.toolbar);
        /**
         * Botón de icono de mail(default) o de +.
         * No lo usamos ya que se muestran en todos los fragments y no nos sirve
         * Si se quiere volver a agregar, hay que agregar en app_bar_activity.xml
         binding.appBarSolicitanteDonante.fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //       .setAction("Action", null).show();
        }
        });

         **/
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio_empresa,
                R.id.nav_campanias_empresa,
                R.id.nav_donantes_empresa,
                R.id.nav_instituciones_empresa,
                R.id.nav_estadisticas_empresa,
                R.id.nav_mis_datos_empresa)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_empresa);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        instanceLayouts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.empresa, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_empresa);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cerrar_sesion_empresa:
                backToLogin();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void instanceLayouts(){
        View header = binding.navView.getHeaderView(0);
        nombreUsuario = (TextView) header.findViewById(R.id.navHeaderNombreUsuarioEmpresa);
        mailUsuario = (TextView) header.findViewById(R.id.navHeaderMailEmpresa);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String nombre = preferences.getString("nombreUsuario", "");
        String mail = preferences.getString("mailUsuario", "");

        nombreUsuario.setText(nombre);
        mailUsuario.setText(mail);
    }

    public void backToLogin(){
        GlobalPreferences.restartPreferences(this);
        Intent loginActivity = new Intent(this, LogInActivity.class);
        startActivity(loginActivity);
    }
}