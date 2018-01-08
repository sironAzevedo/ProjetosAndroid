package com.example.sironazevedo.agendacontato.database;

import android.content.Context;
import android.database.sqlite.*;

/**
 * Created by Siron Azevedo on 01/06/2015.
 */

public class DataBase extends SQLiteOpenHelper{


    public DataBase(Context context) {
        super(context, "AGENDA", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.getCreateContato());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
