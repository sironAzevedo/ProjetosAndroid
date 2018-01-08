package com.example.sironazevedo.agendacontato.ContatoCustomizacao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sironazevedo.agendacontato.R;
import com.example.sironazevedo.agendacontato.dominio.entidades.Contato;

/**
 * Created by Siron Azevedo on 05/06/2015.
 */
public class ContatoArrayAdapter extends ArrayAdapter<Contato> {

    private int resource = 0;
    private LayoutInflater inflater;


    public ContatoArrayAdapter(Context context, int resource){
        super(context, resource);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        ViewHolder viewHolder = null;

        if(convertView == null){

            viewHolder = new ViewHolder();

            view = inflater.inflate(resource, parent, false);

            viewHolder.txtCor = (TextView) view.findViewById(R.id.txtCor);
            viewHolder.txtNome = (TextView) view.findViewById(R.id.txtNome);
            viewHolder.txtTelefone = (TextView) view.findViewById(R.id.txtTelefone);

            view.setTag(viewHolder);

            convertView = view;

        }else{
            viewHolder = (ViewHolder)convertView.getTag();
            view = convertView;
        }

//        if(position % 2 == 0){
//            view.setBackgroundColor(view.getResources().getColor(R.color.linha_par));
//        }else{
//            view.setBackgroundColor(view.getResources().getColor(R.color.linha_impar));
//        }

        Contato contato = getItem(position);
//        viewHolder.txtCor
        viewHolder.txtNome.setText(contato.getNome());
        viewHolder.txtTelefone.setText(contato.getTelefone());


        return view;
    }

    private static class ViewHolder{
        TextView txtCor;
        TextView txtNome;
        TextView txtTelefone;
    }
}
