package com.example.sironazevedo.agendacontato.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.widget.*;

import com.example.sironazevedo.agendacontato.dominio.entidades.Contato;

import java.util.Date;

/**
 * Created by Siron Azevedo on 02/06/2015.
 */
public class RepositorioContato {

    private SQLiteDatabase conn;
    private Contato contato;

    public RepositorioContato(SQLiteDatabase conn){
        this.conn = conn;

    }

    public void inserir(Contato contato){
        ContentValues values = new ContentValues();
        values.put("NOME", contato.getNome());
        values.put("TELEFONE", contato.getTelefone());
        values.put("TIPOTELEFONE", contato.getTipoTelefone());
        values.put("EMAIL", contato.getEmail());
        values.put("TIPOEMAIL", contato.getTipoEmail());
        values.put("ENDERECO", contato.getEndereco());
        values.put("TIPOENDERECO", contato.getTipoEndereco());
        values.put("DATASESPECIAIS", contato.getDatasEspeciais().getTime());
        values.put("TIPODATASESPECIAIS", contato.getTipoDatasEspeciais());
        values.put("GRUPOS", contato.getGrupos());

        conn.insertOrThrow("CONTATO", null, values);

    }

    //METODO PARA INSERIR DADOS NO BANCO MANUALMENTE PELO SCRIPT
//    public void testeInserirContato(){
//
//        for (int i = 0; i < 10; i++){
//            ContentValues values = new ContentValues();
//            values.put("NOME", "SIRON AZEVEDO");
//            conn.insertOrThrow("CONTATO", null, values);
//        }
//    }

    public ArrayAdapter<Contato> buscaContatos(Context context){

        ArrayAdapter<Contato> adpContatos = new ArrayAdapter<Contato>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conn.query("CONTATO", null, null, null, null, null, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do{
                  //ADICIONA MANUALMENTE OS DADOS CADASTRADOS NO BANCO MANUALMANTE NA LISTA
//                String nome = cursor.getString(1);
//                adpContatos.add(nome);

                contato = new Contato();
                contato.setId(cursor.getLong(0));
                contato.setNome(cursor.getString(1));
                contato.setTelefone(cursor.getString(2));
                contato.setTipoTelefone(cursor.getString(3));
                contato.setEmail(cursor.getString(4));
                contato.setTipoEmail(cursor.getString(5));
                contato.setEndereco(cursor.getString(6));
                contato.setTipoEndereco(cursor.getString(7));
                contato.setDatasEspeciais(new Date(cursor.getLong(8)));
                contato.setTipoDatasEspeciais(cursor.getString(9));
                contato.setGrupos(cursor.getString(10));

                adpContatos.add(contato);

                //CONTINAR N AULA 16 AOS 18: 49



            }while (cursor.moveToNext());
        }

        return adpContatos;

    }
}
