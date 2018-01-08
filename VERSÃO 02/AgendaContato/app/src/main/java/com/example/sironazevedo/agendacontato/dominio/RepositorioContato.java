package com.example.sironazevedo.agendacontato.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sironazevedo.agendacontato.ContatoCustomizacao.ContatoArrayAdapter;
import com.example.sironazevedo.agendacontato.R;
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

    //METODO PARA INSERIR DADOS NO BANCO MANUALMENTE PELO SCRIPT
//    public void testeInserirContato(){
//
//        for (int i = 0; i < 10; i++){
//            ContentValues values = new ContentValues();
//            values.put("NOME", "SIRON AZEVEDO");
//            conn.insertOrThrow("CONTATO", null, values);
//        }
//    }

    private ContentValues preencheOuAteraContato(Contato contato){


        ContentValues values = new ContentValues();
       /* values.put("NOME", contato.getNome());
        values.put("TELEFONE", contato.getTelefone());
        values.put("TIPOTELEFONE", contato.getTipoTelefone());
        values.put("EMAIL", contato.getEmail());
        values.put("TIPOEMAIL", contato.getTipoEmail());
        values.put("ENDERECO", contato.getEndereco());
        values.put("TIPOENDERECO", contato.getTipoEndereco());
        values.put("DATASESPECIAIS", contato.getDatasEspeciais().getTime());
        values.put("TIPODATASESPECIAIS", contato.getTipoDatasEspeciais());
        values.put("GRUPOS", contato.getGrupos());*/

        //CODIGO REFATORADO
        values.put(Contato.NOME, contato.getNome());
        values.put(Contato.TELEFONE, contato.getTelefone());
        values.put(Contato.TIPOTELEFONE, contato.getTipoTelefone());
        values.put(Contato.EMAIL, contato.getEmail());
        values.put(Contato.TIPOEMAIL, contato.getTipoEmail());
        values.put(Contato.ENDERECO, contato.getEndereco());
        values.put(Contato.TIPOENDERECO, contato.getTipoEndereco());
        values.put(Contato.DATASESPECIAIS, contato.getDatasEspeciais().getTime());
        values.put(Contato.TIPODATASESPECIAIS, contato.getTipoDatasEspeciais());
        values.put(Contato.GRUPOS, contato.getGrupos());

        return values;

    }

    public void inserir(Contato contato){

        ContentValues values = preencheOuAteraContato(contato);
        conn.insertOrThrow(Contato.TABELA, null, values);

    }

    public void alterar(Contato contato){
        ContentValues values = preencheOuAteraContato(contato);
        conn.update(Contato.TABELA, values, "_id = ?", new String[]{String.valueOf(contato.getId())});

    }

    public void excluir(long id){
        conn.delete(Contato.TABELA, "_id = ?", new String[]{String.valueOf(id)});
    }


    //METODO PARA BUSCAR OS CONTATOS DO BANCO DE DADOS
    public ContatoArrayAdapter buscaContatos(Context context){
//        public ArrayAdapter<Contato> buscaContatos(Context context){

        //ESTEs CODIGOS FOI REFATORADO NA CLASSE CONTATOARRAYADAPTER PARA CUSTOMIZAR O LISTVIEW
//        ArrayAdapter<Contato> adpContatos = new ArrayAdapter<Contato>(context, android.R.layout.simple_list_item_1);
        ContatoArrayAdapter adpContatos = new ContatoArrayAdapter(context, R.layout.item_contato);

        Cursor cursor = conn.query("CONTATO", null, null, null, null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                  //ADICIONA MANUALMENTE OS DADOS CADASTRADOS NO BANCO MANUALMANTE NA LISTA
//                String nome = cursor.getString(1);
//                adpContatos.add(nome);

                contato = new Contato();
               // contato.setId(cursor.getLong(cursor.getColumnIndex("_id"))); SERVE QUANDO NÃO SABE O INDICE DO CAMPO NO BANCO DE DADOS
                contato.setId(cursor.getLong(cursor.getColumnIndex(Contato.ID)));
                contato.setNome(cursor.getString(cursor.getColumnIndex(Contato.NOME)));
                contato.setTelefone(cursor.getString(cursor.getColumnIndex(Contato.TELEFONE)));
                contato.setTipoTelefone(cursor.getString(cursor.getColumnIndex(Contato.TIPOTELEFONE)));
                contato.setEmail(cursor.getString(cursor.getColumnIndex(Contato.EMAIL)));
                contato.setTipoEmail(cursor.getString(cursor.getColumnIndex(Contato.TIPOEMAIL)));
                contato.setEndereco(cursor.getString(cursor.getColumnIndex(Contato.ENDERECO)));
                contato.setTipoEndereco(cursor.getString(cursor.getColumnIndex(Contato.TIPOENDERECO)));
                contato.setDatasEspeciais(new Date(cursor.getLong(cursor.getColumnIndex(Contato.DATASESPECIAIS))));
                contato.setTipoDatasEspeciais(cursor.getString(cursor.getColumnIndex(Contato.TIPODATASESPECIAIS)));
                contato.setGrupos(cursor.getString(cursor.getColumnIndex(Contato.GRUPOS)));

                adpContatos.add(contato);

            }while (cursor.moveToNext());
        }

        return adpContatos;

    }
}
