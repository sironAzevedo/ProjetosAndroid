package com.example.sironazevedo.agendacontato;

import android.app.DatePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sironazevedo.agendacontato.app.MessageBox;
import com.example.sironazevedo.agendacontato.app.ViewHelper;
import com.example.sironazevedo.agendacontato.database.DataBase;
import com.example.sironazevedo.agendacontato.dominio.RepositorioContato;
import com.example.sironazevedo.agendacontato.dominio.entidades.Contato;
import com.example.sironazevedo.agendacontato.utilitario.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ActCadContatos extends ActionBarActivity {

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtTelefone;
    private EditText edtEndereco;
    private EditText edtDatasEspeciais;
    private EditText edtGrupo;

    private Spinner spnTipoEmail;
    private Spinner spnTipoTelefone;
    private Spinner spnTipoEndereco;
    private Spinner spnTipoDatasEspeciais;

    private ArrayAdapter<String> adpTipoEmail;
    private ArrayAdapter<String> adpTipoTelefone;
    private ArrayAdapter<String> adpTipoEndereco;
    private ArrayAdapter<String> adpTipoDatasEspeciais;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioContato repositorioContato;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_contatos);


        edtNome = (EditText) findViewById(R.id.edtNome);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtEndereco = (EditText) findViewById(R.id.edtEnderece);
        edtDatasEspeciais = (EditText) findViewById(R.id.edtDatasEspeciais);
        edtGrupo = (EditText) findViewById(R.id.edtGrupos);

        spnTipoEmail = (Spinner) findViewById(R.id.spnTipoEmail);
        spnTipoTelefone = (Spinner) findViewById(R.id.spnTipoTelefone);
        spnTipoEndereco = (Spinner) findViewById(R.id.spnTipoEndereco);
        spnTipoDatasEspeciais = (Spinner) findViewById(R.id.spnDatasEspeciais);

        adpTipoEmail = ViewHelper.createArrayAdapter(this, spnTipoEmail);
        adpTipoTelefone = ViewHelper.createArrayAdapter(this, spnTipoTelefone);
        adpTipoEndereco = ViewHelper.createArrayAdapter(this, spnTipoEndereco);
        adpTipoDatasEspeciais = ViewHelper.createArrayAdapter(this, spnTipoDatasEspeciais);



        /*adpTipoEmail = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpTipoEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoTelefone = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpTipoTelefone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoEndereco = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpTipoEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        adpTipoDatasEspeciais = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        adpTipoDatasEspeciais.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnTipoEmail.setAdapter(adpTipoEmail);
        spnTipoTelefone.setAdapter(adpTipoTelefone);
        spnTipoEndereco.setAdapter(adpTipoEndereco);
        spnTipoDatasEspeciais.setAdapter(adpTipoDatasEspeciais);*/

        adpTipoEmail.add("Casa");
        adpTipoEmail.add("Trabalho");
        adpTipoEmail.add("Outros");

        adpTipoTelefone.add("Celular");
        adpTipoTelefone.add("Trabalho");
        adpTipoTelefone.add("Casa");
        adpTipoTelefone.add("Principal");
        adpTipoTelefone.add("Fax Trabalho");
        adpTipoTelefone.add("pager");
        adpTipoTelefone.add("Outros");

        adpTipoEndereco.add("Casa");
        adpTipoEndereco.add("Trabalho");
        adpTipoEndereco.add("Outros");

        adpTipoDatasEspeciais.add("Aniversario");
        adpTipoDatasEspeciais.add("Data Comemorativa");
        adpTipoDatasEspeciais.add("Outros");


        ExibeDataListener dataListener = new ExibeDataListener();
        edtDatasEspeciais.setOnClickListener(dataListener);
        edtDatasEspeciais.setOnFocusChangeListener(dataListener);

        //RECUPERA O VALOR CLICADO NA LISTVIEW QUE ESTAR NA TELA ACTCONTATOS - SERVER PARA COLOCAR NO TRABALHO TOPICOS II
        Bundle bundle = getIntent().getExtras();
        if((bundle != null) && (bundle.containsKey(ActContato.PAR_CONTATO))){
            contato = (Contato) bundle.getSerializable(ActContato.PAR_CONTATO);
            preencheDados();
        }else{
            contato = new Contato();
        }


        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            //ESTE TRY É USANDO TAMBÉM NA CLASSE ACTCONTATO, OS CODIGOS QUE ESTÃO COMENTADO
            //SÃO USADOS NA CLASSE ACTCONTATOS PARA BUSCAR OS DADOS DO BANCO E ADICIONAR NA LISTA

            //INSTANCIANDO A CLASSE REPOSITORIOCONTATO
            repositorioContato = new RepositorioContato(conn);

            //CHAMADA DO METODO PARA INSERIR DADOS NO BANCO MANUALMENTE
            //repositorioContato.testeInserirContato();

            //ADICIONANDO OS CONTADOS CADASTRADO NO BANCO NO ARRAY ADAPTER
            //adpContatos = repositorioContato.buscaContatos(this);

            //ADICIONANDO A LISTA DE CONTATOS NO LISTVIEW
            //lstContatos.setAdapter(adpContatos);


            //MENSAGEM DE ALERTA PARA A CONEXÃO DO BANCO DE DADOS
//            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
//            dlg.setMessage("Conexão criada com sucesso!");
//            dlg.setNeutralButton("OK", null);
//            dlg.show();

        }catch (SQLException ex){

            MessageBox.show(this, "Erro", "Erro ao criar o banco: " + ex.getMessage());

//            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
//            dlg.setMessage("Erro ao criar o banco!" + ex.getMessage());
//            dlg.setNeutralButton("OK", null);
//            dlg.show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_contatos, menu);

        if(contato.getId() != 0 ){
            menu.getItem(0).setTitle("Alterar");
            menu.getItem(1).setVisible(true);
        }

        return super.onCreateOptionsMenu(menu);
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
        }


        return super.onOptionsItemSelected(item);
    }

     private void salvar(){

        try {
           //contato = new Contato();

            contato.setNome(edtNome.getText().toString());
            contato.setTelefone(edtTelefone.getText().toString());
            contato.setEmail(edtEmail.getText().toString());
            contato.setEndereco(edtEndereco.getText().toString());

            /*Date date = new Date();
            contato.setDatasEspeciais(date);*/

            contato.setGrupos(edtGrupo.getText().toString());

            contato.setTipoTelefone(String.valueOf(spnTipoTelefone.getSelectedItemPosition()));
            contato.setTipoEmail(String.valueOf(spnTipoEmail.getSelectedItemPosition()));
            contato.setTipoEndereco(String.valueOf(spnTipoEndereco.getSelectedItemPosition()));
            contato.setTipoDatasEspeciais(String.valueOf(spnTipoDatasEspeciais.getSelectedItemPosition()));

            if(contato.getId() == 0){
                repositorioContato.inserir(contato);
            }else{
                repositorioContato.alterar(contato);
            }

        }catch (Exception ex){
            MessageBox.show(this, "Erro", "Erro ao inserir os dados: " + ex.getMessage());
//            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
//            dlg.setMessage("Erro ao inserir os dados!" + ex.getMessage());
//            dlg.setNeutralButton("OK", null);
//            dlg.show();
        }

    }

    private void excluir(){
        try {
            repositorioContato.excluir(contato.getId());

        }catch (Exception ex){
            MessageBox.show(this, "Erro", "Erro ao excluir os dados: " + ex.getMessage());
//            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
//            dlg.setMessage("Erro ao excluir os dados!" + ex.getMessage());
//            dlg.setNeutralButton("OK", null);
//            dlg.show();
        }

    }

    //PREENCHE OS CAMPOS COM OS DADOS DO ITEM CLICADO NA LISTVIEW
    private void preencheDados(){

        edtNome.setText(contato.getNome());
        edtTelefone.setText(contato.getTelefone());
        spnTipoTelefone.setSelection(Integer.parseInt(contato.getTipoTelefone()));
        edtEmail.setText(contato.getEmail());
        spnTipoEmail.setSelection(Integer.parseInt(contato.getTipoEmail()));
        edtEndereco.setText(contato.getEndereco());
        spnTipoEndereco.setSelection(Integer.parseInt(contato.getTipoEndereco()));

        SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
        //DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        String dt = format.format(contato.getDatasEspeciais());

        edtDatasEspeciais.setText(dt);
        spnTipoDatasEspeciais.setSelection(Integer.parseInt(contato.getTipoDatasEspeciais()));
        edtGrupo.setText(contato.getGrupos());

    }

    private void exibeData(){

        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dlg = new DatePickerDialog(this, new SelecionaDataListener(), ano, mes, dia);
        dlg.show();
    }

    private class ExibeDataListener implements View.OnClickListener, View.OnFocusChangeListener{

        @Override
        public void onClick(View v) {
            exibeData();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus){
                exibeData();
            }
        }
    }

    private class SelecionaDataListener implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            /*CODIGO REFATORADO E COLOCADO EM UMA CLASSE PARA FICAR MAIS ORGANIZADO*/
            String dt = DateUtils.dateToString(year, monthOfYear, dayOfMonth);
            Date date = DateUtils.getDate(year, monthOfYear, dayOfMonth);


           /* Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);
            Date date = calendar.getTime();

            DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
            String dt = format.format(date);*/

            edtDatasEspeciais.setText(dt);
            contato.setDatasEspeciais(date);
        }
    }
}





















