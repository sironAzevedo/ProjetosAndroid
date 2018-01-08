package com.example.sironazevedo.diving_life.database;

/**
 * Created by Siron Azevedo on 01/06/2015.
 */
public class ScriptSQL {

    public static String getCreateNaufragio(){

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS NAUFRAGIO ( ");
        sqlBuilder.append("_id      INTEGER            NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT,         ");
        sqlBuilder.append("NOME               VARCHAR (200),  ");
        sqlBuilder.append("PROFUNDIDADE       VARCHAR (200),  ");
        sqlBuilder.append("LOCALIZACAO        VARCHAR (200),  ");
        sqlBuilder.append("HISTORIA           VARCHAR (2000), ");
        sqlBuilder.append("FOTO               VARCHAR (200)   ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();

    }

}
