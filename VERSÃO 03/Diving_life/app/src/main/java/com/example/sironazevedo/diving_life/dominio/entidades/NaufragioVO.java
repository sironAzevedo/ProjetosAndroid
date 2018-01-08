package com.example.sironazevedo.diving_life.dominio.entidades;

import java.io.Serializable;

/**
 * Created by Siron Azevedo on 17/05/2015.
 */
public class NaufragioVO implements Serializable {

    public static String TABELA = "NAUFRAGIO";
    public static String ID = "_id";
    public static String NOME = "NOME";
    public static String PROFUNDIDADE = "PROFUNDIDADE";
    public static String LOCALIZACAO = "LOCALIZACAO";
    public static String HISTORIA = "HISTORIA";
    public static String FOTO = "FOTO";
    public static String MAPA = "MAPA";

    private long id;
    private String nome;
    private String profundidade;
    private String localizacao;
    private String historia;
    private String foto;


    public NaufragioVO(){
        id = 0;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(String profundidade) {
        this.profundidade = profundidade;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return nome + " " + profundidade;
    }
}
