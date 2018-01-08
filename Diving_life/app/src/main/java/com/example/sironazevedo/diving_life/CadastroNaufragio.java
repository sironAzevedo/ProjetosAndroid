package com.example.sironazevedo.diving_life;

import android.app.AlertDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.sironazevedo.diving_life.database.DataBase;
import com.example.sironazevedo.diving_life.dominio.RepositorioNaufragio;
import com.example.sironazevedo.diving_life.dominio.entidades.NaufragioVO;

import java.util.Date;


public class CadastroNaufragio extends ActionBarActivity {

    private EditText edtNome;
    private EditText edtProfundidade;
    private EditText edtLocalizacao;
    private EditText edtHistoria;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioNaufragio repositorioNaufragio;
    private NaufragioVO naufragio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_naufragio_layout);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtProfundidade = (EditText) findViewById(R.id.edtProfundidade);
        edtLocalizacao = (EditText) findViewById(R.id.edtLocalizacao);
        edtHistoria = (EditText) findViewById(R.id.edtHistoria);

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            //INSTANCIANDO A CLASSE REPOSITORIOCONTATO
            repositorioNaufragio = new RepositorioNaufragio(conn);

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco!" + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro_naufragio, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.mni_acao1:

                if(naufragio == null){
                    inserir();
                }else{

                }

                finish();

                break;

            case R.id.mni_acao2:

                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private void inserir(){

        try {

            naufragio = new NaufragioVO();

            naufragio.setNome(edtNome.getText().toString());
            naufragio.setProfundidade(edtLocalizacao.getText().toString());
            naufragio.setLocalizacao(edtProfundidade.getText().toString());
            naufragio.setHistoria(edtHistoria.getText().toString());

            repositorioNaufragio.inserir(naufragio);

        }catch (Exception ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao inserir os dados!" + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

    }

}
