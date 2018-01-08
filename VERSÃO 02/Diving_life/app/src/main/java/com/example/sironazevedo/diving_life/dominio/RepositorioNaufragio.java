package com.example.sironazevedo.diving_life.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.sironazevedo.diving_life.R;
import com.example.sironazevedo.diving_life.dominio.entidades.NaufragioVO;
import com.example.sironazevedo.diving_life.naufragioCustumizacao.NaufragioArrayAdapter;

/**
 * Created by ADM on 03/06/2015.
 */
public class RepositorioNaufragio {

    private SQLiteDatabase conn;
    private NaufragioVO naufragioVO;

    public RepositorioNaufragio(SQLiteDatabase conn){
        this.conn = conn;
    }

    private ContentValues preencheOuAteraNaufragio(NaufragioVO naufragio){
        ContentValues values = new ContentValues();
        values.put(NaufragioVO.NOME, naufragio.getNome());
        values.put(NaufragioVO.PROFUNDIDADE, naufragio.getProfundidade());
        values.put(NaufragioVO.LOCALIZACAO, naufragio.getLocalizacao());
        values.put(NaufragioVO.HISTORIA, naufragio.getHistoria());

        return values;
    }

    public void inserir(NaufragioVO naufragio){
        ContentValues values = preencheOuAteraNaufragio(naufragio);
        conn.insertOrThrow(NaufragioVO.TABELA, null, values);

    }

    public void alterar(NaufragioVO naufragio){
        ContentValues values = preencheOuAteraNaufragio(naufragio);
        conn.update(NaufragioVO.TABELA, values, "_id = ?", new String[]{String.valueOf(naufragio.getId())});

    }

    public void excluir(long id){
        conn.delete(NaufragioVO.TABELA, "_id = ?", new String[]{String.valueOf(id)});
    }


    public NaufragioArrayAdapter buscaNaufragios(Context context){

        NaufragioArrayAdapter adpNaufragios = new NaufragioArrayAdapter(context, R.layout.item_naufragio);

        Cursor cursor = conn.query("NAUFRAGIO", null, null, null, null, null, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do{
                naufragioVO = new NaufragioVO();
                naufragioVO.setId(cursor.getLong(cursor.getColumnIndex(NaufragioVO.ID)));
                naufragioVO.setNome(cursor.getString(cursor.getColumnIndex(NaufragioVO.NOME)));
                naufragioVO.setProfundidade(cursor.getString(cursor.getColumnIndex(NaufragioVO.PROFUNDIDADE)));
                naufragioVO.setLocalizacao(cursor.getString(cursor.getColumnIndex(NaufragioVO.LOCALIZACAO)));
                naufragioVO.setHistoria(cursor.getString(cursor.getColumnIndex(NaufragioVO.HISTORIA)));

                adpNaufragios.add(naufragioVO);

            }while (cursor.moveToNext());
        }

        return adpNaufragios;

    }

}