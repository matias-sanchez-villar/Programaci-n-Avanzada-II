package com.example.unidad3_tp3;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.AndroidViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unidad3_tp3.databinding.ActivityParqueosDrawerBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ParqueosDrawer extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityParqueosDrawerBinding binding;

    EditText txtMatricula, txtTiempo;
    Button btnRegistrar, btnCancelar;
    FloatingActionButton btnOpenDialog;
    AdminSQLite helper=new AdminSQLite(this, "DB_TP3",null,1);
    TextView userName, userMail;
    GridView Lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParqueosDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Botón para abrir dialogo
        setSupportActionBar(binding.appBarParqueosDrawer.toolbar);
        binding.appBarParqueosDrawer.btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_parqueo, R.id.nav_cuenta)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_parqueos_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        instanceLayouts();

        LlenarGridView();

        Lista=(GridView) findViewById(R.id.GridViewParqueos);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parqueos_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.nuevoParqueo){
            openDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void instanceLayouts(){
        View header = binding.navView.getHeaderView(0);
        userName = (TextView)header.findViewById(R.id.user_name);
        userMail = (TextView)header.findViewById(R.id.user_mail);

        userName.setText(getIntent().getStringExtra("user"));
        userMail.setText(getIntent().getStringExtra("userMail"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_parqueos_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void openDialog(){
        Dialog registrarDialog = new Dialog(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_registrar_parqueo, null);

        txtMatricula = dialogView.findViewById(R.id.txtMatricula);
        txtTiempo = dialogView.findViewById(R.id.txtTiempo);

        btnRegistrar = dialogView.findViewById(R.id.btnRegistrarParqueo);
        btnCancelar = dialogView.findViewById(R.id.btnCancelar);

        instantiateDialogButtonsListeners(registrarDialog);

        registrarDialog.setContentView(dialogView);

        registrarDialog.show();
    }

    public void instantiateDialogButtonsListeners(Dialog dialog){
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int matricula = Utilities.stringToNumber(txtMatricula.getText().toString());
                int tiempo = Utilities.stringToNumber(txtTiempo.getText().toString());
                int userId = getIntent().getIntExtra("userId", -1);

                if(!validarCamposNumericos(matricula,tiempo, userId)){
                    Toast.makeText(getApplicationContext(), "Solo se admiten numeros", Toast.LENGTH_LONG);
                }else {
                    helper.abrirDB();

                    helper.insertarParqueo(matricula, tiempo, userId);

                    helper.cerarDB();

                    Toast.makeText(getApplicationContext(),"Registro almacenado con exito",Toast.LENGTH_LONG).show();

                    dialog.dismiss();

                    LlenarGridView();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public boolean validarCamposNumericos(int matricula, int tiempo, int usuario){
        return matricula != -1 && tiempo != -1 && usuario != -1;
    }

    public void LlenarGridView(){
        int userId = getIntent().getIntExtra("userId", -1);

        helper.abrirDB();

        Cursor c = helper.LeerParqueos(userId);
        String Matricula = "", Tiempo = "";
        ArrayList<String> item = new ArrayList<String>();
        if(c.moveToFirst()){
            Matricula = c.getString(0);
            Tiempo = c.getString(1);
            item.add(Matricula+" "+Tiempo);
        }while (c.moveToNext());

        helper.cerarDB();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.fragment_parqueos,item);
        Lista.setAdapter(adapter);
    }


}