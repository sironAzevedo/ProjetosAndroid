package com.example.sironazevedo.agendacontato.utilitario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Siron Azevedo on 04/06/2015.
 */
public class DateUtils {

    //METODO PARA CONVERTER UMA DATA, MES E ANO
    public static String dateToString(int year, int monthOfYear, int dayOfMonth){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        Date date = calendar.getTime();

        SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");

        //DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        String dt = format.format(date);

        return dt;
    }

    public static Date getDate(int year, int monthOfYear, int dayOfMonth){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        Date date = calendar.getTime();

        return date;
    }


    //METODO PARA CONVERTER UMA DATA ESPECIFICA
    public static String dateToString(Date date){
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        String dt = format.format(date);

        return dt;
    }
}
