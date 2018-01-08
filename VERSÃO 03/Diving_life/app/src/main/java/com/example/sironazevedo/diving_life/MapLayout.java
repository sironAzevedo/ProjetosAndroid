package com.example.sironazevedo.diving_life;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.sironazevedo.diving_life.mapaFragments.MapaFragment;

/**
 * Created by Siron Azevedo on 07/06/2015.
 */
public class MapLayout extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mapa, new MapaFragment());
        transaction.commit();



    }
}
