package com.example.sironazevedo.agendacontato.app;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Siron Azevedo on 04/06/2015.
 */
public class ViewHelper {

    public static ArrayAdapter<String> createArrayAdapter(Context context, Spinner spinner){

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        return arrayAdapter;
    }

}
