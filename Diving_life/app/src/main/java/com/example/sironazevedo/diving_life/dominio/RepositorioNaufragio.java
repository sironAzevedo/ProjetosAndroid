package com.example.sironazevedo.diving_life.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.widget.*;

import com.example.sironazevedo.diving_life.dominio.entidades.NaufragioVO;

import java.util.Date;

/**
 * Created by ADM on 03/06/2015.
 */
public class RepositorioNaufragio {

    private SQLiteDatabase conn;
    private NaufragioVO naufragioVO;

    public RepositorioNaufragio(SQLiteDatabase conn){
        this.conn = conn;
    }

    public void inserir(NaufragioVO naufragioVO){
        ContentValues values = new ContentValues();
        values.put("NOME", naufragioVO.getNome());
        values.put("PROFUNDIDADE", naufragioVO.getProfundidade());
        values.put("LOCALIZACAO", naufragioVO.getLocalizacao());
        values.put("HISTORIA", naufragioVO.getHistoria());

        conn.insertOrThrow("NAUFRAGIO", null, values);

    }

    public ArrayAdapter<NaufragioVO> buscaNaufragios(Context context){

        ArrayAdapter<NaufragioVO> adpNaufragios = new ArrayAdapter<NaufragioVO>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conn.query("NAUFRAGIO", null, null, null, null, null, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do{
                naufragioVO = new NaufragioVO();
                naufragioVO.setId(cursor.getLong(0));
                naufragioVO.setNome(cursor.getString(1));
                naufragioVO.setProfundidade(cursor.getString(2));
                naufragioVO.setLocalizacao(cursor.getString(3));
                naufragioVO.setHistoria(cursor.getString(4));

                adpNaufragios.add(naufragioVO);

            }while (cursor.moveToNext());
        }

        return adpNaufragios;

    }

}