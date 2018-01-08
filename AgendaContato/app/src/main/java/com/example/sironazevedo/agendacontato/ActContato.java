package com.example.sironazevedo.agendacontato;

import android.app.AlertDialog;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import com.example.sironazevedo.agendacontato.database.DataBase;
import com.example.sironazevedo.agendacontato.dominio.RepositorioContato;
import com.example.sironazevedo.agendacontato.dominio.entidades.Contato;


public class ActContato extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ImageButton btnAdicionar;
    private EditText edtPesquisa;
    private ListView lstContatos;
    private ArrayAdapter<Contato> adpContatos;
//    private ArrayAdapter<String> adpContatos;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioContato repositorioContato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contato);

        btnAdicionar = (ImageButton)findViewById(R.id.btnAdicionar);
        edtPesquisa = (EditText)findViewById(R.id.edtPesquisa);
        lstContatos = (ListView) findViewById(R.id.lstContatos);
        btnAdicionar.setOnClickListener(this);

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            //INSTANCIANDO A CLASSE REPOSITORIOCONTATO
            repositorioContato = new RepositorioContato(conn);

            //ADICIONANDO OS CONTADOS CADASTRADO NO BANCO NO ARRAY ADAPTER
            adpContatos = repositorioContato.buscaContatos(this);

            //ADICIONANDO A LISTA DE CONTATOS NO LISTVIEW
            lstContatos.setAdapter(adpContatos);


            //MENSAGEM DE ALERTA PARA A CONEXÃO DO BANCO DE DADOS
//            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
//            dlg.setMessage("Conexão criada com sucesso!");
//            dlg.setNeutralButton("OK", null);
//            dlg.show();

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco!" + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ActCadContatos.class);
        startActivityForResult(intent, 0);
    }

    //METODO PARA ATUALIZAR A LISTVIEW OU QUALQUER OUTRAR TELA QUE TENHA DADOS PARA SER ATUALIZADO QUANDO FOR CHAMADA NOVAMENTE
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        adpContatos = repositorioContato.buscaContatos(this);
        lstContatos.setAdapter(adpContatos);
    }

    //ATENÇÃO: ESTE METODO REALIZA A PASSAGEM DE PARAMENTRO PARA OUTRA TELA.
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Contato contato = adpContatos.getItem(position);
        Intent intent = new Intent(this, ActCadContatos.class);
        intent.putExtra("CONTATO", contato);

        startActivityForResult(intent, 0);

    }
}















