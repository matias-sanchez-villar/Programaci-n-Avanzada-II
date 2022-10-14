package com.example.unidad4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.unidad4.Controller.PageAdapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TabLayoutMediator.TabConfigurationStrategy {

    TabLayout tableLayout;
    ViewPager2 viewPager;
    ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide(); //ver
        tableLayout = findViewById(R.id.tablelayout);
        viewPager = findViewById(R.id.viewpager);

        titles = new ArrayList<String>();
        titles.add("ALTA");
        titles.add("MODIFICAR");
        titles.add("LISTAR");

        setViewPagerAdapter();
        new TabLayoutMediator(tableLayout, viewPager, this).attach();





    }

    public void setViewPagerAdapter() {
        PageAdapter viewPager2Adapter = new
                PageAdapter(this);
        ArrayList<Fragment> fragmentList = new ArrayList<>(); //createsan ArrayList of Fragments
        fragmentList.add(new AltaFragment());
        fragmentList.add(new ModificarFragment());
        fragmentList.add(new ListarFragment());
        viewPager2Adapter.setData(fragmentList);
        viewPager.setAdapter(viewPager2Adapter);

    }

    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        tab.setText(titles.get(position));
    }
}