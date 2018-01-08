package com.example.sironazevedo.diving_life;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.sironazevedo.diving_life.app.MessageBox;
import com.example.sironazevedo.diving_life.database.DataBase;
import com.example.sironazevedo.diving_life.dominio.RepositorioNaufragio;
import com.example.sironazevedo.diving_life.dominio.entidades.NaufragioVO;

import java.lang.reflect.Method;


public class CadastroNaufragio extends ActionBarActivity {

    private EditText edtNome;
    private EditText edtProfundidade;
    private EditText edtLocalizacao;
    private EditText edtHistoria;
    private ImageView naufragioFoto;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioNaufragio repositorioNaufragio;
    private NaufragioVO naufragio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_naufragio_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.mergulhar_actionbar);


        edtNome = (EditText) findViewById(R.id.edtNome);
        edtProfundidade = (EditText) findViewById(R.id.edtProfundidade);
        edtLocalizacao = (EditText) findViewById(R.id.edtLocalizacao);
        edtHistoria = (EditText) findViewById(R.id.edtHistoria);
        naufragioFoto = (ImageView) findViewById(R.id.naufragioFoto);

        //RECUPERA O VALOR CLICADO NA LISTVIEW QUE ESTAR NA TELA ACTCONTATOS - SERVER PARA COLOCAR NO TRABALHO TOPICOS II
        Bundle bundle = getIntent().getExtras();
        if((bundle != null) && (bundle.containsKey(MergulhoNaufragio.PAR_CONTATO))){
            naufragio = (NaufragioVO) bundle.getSerializable(MergulhoNaufragio.PAR_CONTATO);
            preencheDados();
        }else{
            naufragio = new NaufragioVO();
        }

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            //INSTANCIANDO A CLASSE REPOSITORIOCONTATO
            repositorioNaufragio = new RepositorioNaufragio(conn);

        }catch (SQLException ex){
            MessageBox.show(this, "Erro", "Erro ao criar o banco: " + ex.getMessage());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_cadastro_naufragio, menu);

        onMenuOpened(Window.FEATURE_ACTION_BAR, menu);

        if(naufragio.getId() != 0 ){
            menu.getItem(0).setTitle("Alterar");
            menu.getItem(1).setVisible(true);
            menu.getItem(2).setVisible(true);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if(featureId == Window.FEATURE_ACTION_BAR && menu != null){
            if(menu.getClass().getSimpleName().equals("MenuBuilder")){
                try{
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch(NoSuchMethodException e){
                } catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        }

        return super.onMenuOpened(featureId, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.mni_acao1:
                salvar();
                finish();
                break;

            case R.id.mni_acao2:
                excluir();
                finish();
                break;
            case R.id.mni_acao3:
                Intent intent = new Intent(this, MapLayout.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvar(){

        try {
            //naufragio = new NaufragioVO();

            naufragio.setNome(edtNome.getText().toString());
            naufragio.setProfundidade(edtProfundidade.getText().toString());
            naufragio.setLocalizacao(edtLocalizacao.getText().toString());
            naufragio.setHistoria(edtHistoria.getText().toString());

            if(naufragio.getId() == 0){
                repositorioNaufragio.inserir(naufragio);
            }else{
                repositorioNaufragio.alterar(naufragio);
            }

        }catch (Exception ex){
            MessageBox.show(this, "Erro", "Erro ao salvar os dados: " + ex.getMessage());
        }
    }

    private void excluir(){
        try {
            repositorioNaufragio.excluir(naufragio.getId());

        }catch (Exception ex){
            MessageBox.show(this, "Erro", "Erro ao excluir os dados: " + ex.getMessage());
        }

    }

    //PREENCHE OS CAMPOS COM OS DADOS DO ITEM CLICADO NA LISTVIEW
    private void preencheDados(){

        edtNome.setText(naufragio.getNome());
        edtProfundidade.setText(naufragio.getProfundidade());
        edtLocalizacao.setText(naufragio.getLocalizacao());
        edtHistoria.setText(naufragio.getHistoria());

    }

}
