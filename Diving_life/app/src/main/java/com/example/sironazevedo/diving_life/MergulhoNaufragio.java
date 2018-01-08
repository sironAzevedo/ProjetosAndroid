package com.example.sironazevedo.diving_life;


import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.sironazevedo.diving_life.database.DataBase;
import com.example.sironazevedo.diving_life.dominio.RepositorioNaufragio;
import com.example.sironazevedo.diving_life.dominio.entidades.NaufragioVO;


/**
 * Created by Siron Azevedo on 11/04/2015.
 */
public class MergulhoNaufragio extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener, BaseSliderView.OnSliderClickListener, BaseSliderView.ImageLoadListener  {

    private ImageButton btnAdicionar;
    private EditText edtPesquisa;
    private ListView lstNaufragio;
    private ArrayAdapter<NaufragioVO> adpNaufragios;


    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioNaufragio repositorioNaufragio;
    private SliderLayout.Transformer[] effectsId = { SliderLayout.Transformer.Accordion };
    private SliderLayout slImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naufragio );

        carregarSliderLayout();

        btnAdicionar = (ImageButton)findViewById(R.id.btnAdicionar);
        edtPesquisa = (EditText)findViewById(R.id.edtPesquisa);
        lstNaufragio = (ListView) findViewById(R.id.lstNaufragio);
        btnAdicionar.setOnClickListener(this);

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioNaufragio = new RepositorioNaufragio(conn);

            //ADICIONANDO OS NAUFRAGIOS CADASTRADO DO BANCO NO ARRAY ADAPTER
            adpNaufragios = repositorioNaufragio.buscaNaufragios(this);

            //ADICIONANDO A LISTA DE CONTATOS NO LISTVIEW
            lstNaufragio.setAdapter(adpNaufragios);

            //MENSAGEM DE ALERTA PARA A CONEXÃO DO BANCO DE DADOS
           /* AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Conexão criada com sucesso!");
            dlg.setNeutralButton("OK", null);
            dlg.show();*/

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco!" + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }


    }

    private void carregarSliderLayout(){

        // SLIDERLAYOUT
        slImages = (SliderLayout) findViewById(R.id.slImages);

        DefaultSliderView aux1 = new DefaultSliderView(MergulhoNaufragio.this);
        aux1.image(R.drawable.naufragio01);
        aux1.setOnSliderClickListener(MergulhoNaufragio.this);
        aux1.description("naufragio01");
        slImages.addSlider(aux1);

        aux1 = new DefaultSliderView(MergulhoNaufragio.this);
        aux1.image(R.drawable.naufragio02);
        aux1.setOnSliderClickListener(MergulhoNaufragio.this);
        aux1.description("naufragio02");
        slImages.addSlider(aux1);

        aux1 = new DefaultSliderView(MergulhoNaufragio.this);
        aux1.image(R.drawable.naufragio03);
        aux1.setOnSliderClickListener(MergulhoNaufragio.this);
        aux1.description("naufragio03");
        slImages.addSlider(aux1);

        aux1 = new DefaultSliderView(MergulhoNaufragio.this);
        aux1.image(R.drawable.naufragio04);
        aux1.setOnSliderClickListener(MergulhoNaufragio.this);
        aux1.description("naufragio04");
        slImages.addSlider(aux1);

        aux1 = new DefaultSliderView(MergulhoNaufragio.this);
        aux1.image(R.drawable.naufragio05);
        aux1.setOnSliderClickListener(MergulhoNaufragio.this);
        aux1.description("naufragio05");
        slImages.addSlider(aux1);

        aux1 = new DefaultSliderView(MergulhoNaufragio.this);
        aux1.image(R.drawable.naufragio06);
        aux1.setOnSliderClickListener(MergulhoNaufragio.this);
        aux1.description("naufragio06");
        slImages.addSlider(aux1);

        aux1 = new DefaultSliderView(MergulhoNaufragio.this);
        aux1.image(R.drawable.naufragio07);
        aux1.setOnSliderClickListener(MergulhoNaufragio.this);
        aux1.description("naufragio07");
        slImages.addSlider(aux1);

        slImages.setPresetTransformer(effectsId[0]);
        //slImages.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        slImages.setCustomIndicator((PagerIndicator)findViewById(R.id.custom_indicator));
        //slImages.setDuration(4000);
        //slImages.setSliderTransformDuration(8000, null);
        slImages.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);

        PagerIndicator p = (PagerIndicator)findViewById(R.id.custom_indicator);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LOG", "ID: "+v.getId());
            }
        });
    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {
        Log.i("LOG", "onSliderClick(" + baseSliderView.getDescription() + ")");
        slImages.startAutoCycle();
    }

    @Override
    public void onStart(BaseSliderView baseSliderView) {
        Log.i("LOG", "onStart(" + baseSliderView.getDescription() + ")");
    }

    @Override
    public void onEnd(boolean b, BaseSliderView baseSliderView) {
        Log.i("LOG", "onEnd(" + baseSliderView.getDescription() + ")");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CadastroNaufragio.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        adpNaufragios = repositorioNaufragio.buscaNaufragios(this);
        lstNaufragio.setAdapter(adpNaufragios);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}


