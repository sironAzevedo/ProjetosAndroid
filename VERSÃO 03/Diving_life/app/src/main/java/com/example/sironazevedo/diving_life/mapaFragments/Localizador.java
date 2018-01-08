package com.example.sironazevedo.diving_life.mapaFragments;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by Siron Azevedo on 08/06/2015.
 */
public class Localizador {
    private Context context;
    public Localizador(Context context) {
        this.context = context;
    }

    public LatLng getCoordenada(String localizacao) {
        Geocoder geocoder = new Geocoder(context);
        try {
            List<Address> localizacoes = geocoder.getFromLocationName(localizacao, 1);
            if(!localizacoes.isEmpty()){
                Address localizacoesLocalizadas = localizacoes.get(0);
                double latitude = localizacoesLocalizadas.getLatitude();
                double longitude = localizacoesLocalizadas.getLongitude();

                return new LatLng(latitude, longitude);
            }else{
                return null;
            }

        } catch (IOException e) {
            return null;
        }
    }
}
