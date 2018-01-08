package com.example.sironazevedo.diving_life.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Siron Azevedo on 17/05/2015.
 */
public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MERGULHO";
    private static final int DATABASE_VERSION = 1;



    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.getCreateNaufragio());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
