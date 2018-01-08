package com.example.sironazevedo.diving_life.mapaFragments;

import android.support.v4.app.FragmentActivity;

import com.example.sironazevedo.diving_life.database.DataBase;
import com.example.sironazevedo.diving_life.dominio.RepositorioNaufragio;
import com.example.sironazevedo.diving_life.dominio.entidades.NaufragioVO;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by Siron Azevedo on 07/06/2015.
 */
public class MapaFragment extends SupportMapFragment {
    private RepositorioNaufragio repositorioNaufragio;
    private NaufragioVO naufragio;

    @Override
    public void onResume() {
        super.onResume();
        //new LatLng(-12.888835, -38.480765);

        FragmentActivity context = getActivity();
        LatLng local = new Localizador(context).getCoordenada("Rua São Felix 41e Itacaranha");
        centralizarNoMapa(local);

        DataBase dao = new DataBase(context);
        List<NaufragioVO> naufragios = (List<NaufragioVO>) repositorioNaufragio.buscaNaufragios(context);

        for(NaufragioVO naufragio: naufragios){
            GoogleMap map = getMap();
            LatLng localNaufragio = new Localizador(context).getCoordenada(naufragio.getLocalizacao());
            MarkerOptions opcoes = new MarkerOptions().title(naufragio.getNome()).position(localNaufragio);
            map.addMarker(opcoes);
        }


    }

    private void centralizarNoMapa(LatLng local){
        GoogleMap map = getMap();
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(local, 15);
        map.animateCamera(update);
    }
}
