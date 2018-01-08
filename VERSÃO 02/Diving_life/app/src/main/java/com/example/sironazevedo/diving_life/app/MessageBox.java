package com.example.sironazevedo.diving_life.app;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by Siron Azevedo on 04/06/2015.
 */
public class MessageBox {

    public static void show(Context context, String title, String msg, int iconId){
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setIcon(iconId);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setNeutralButton("OK", null);
        dlg.show();

    }

    public static void show(Context context, String title, String msg){
        show(context, title, msg, 0);
    }

    public static void showAlert(Context context, String title, String msg){
        show(context, title, msg, android.R.drawable.ic_dialog_alert);
    }

    public static void showInfo(Context context, String title, String msg){
        show(context, title, msg, android.R.drawable.ic_dialog_info);
    }

}
