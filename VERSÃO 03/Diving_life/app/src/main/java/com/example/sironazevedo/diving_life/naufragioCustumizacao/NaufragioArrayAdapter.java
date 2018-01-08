package com.example.sironazevedo.diving_life.naufragioCustumizacao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sironazevedo.diving_life.R;
import com.example.sironazevedo.diving_life.dominio.entidades.NaufragioVO;

/**
 * Created by ADM on 05/06/2015.
 */
public class NaufragioArrayAdapter  extends ArrayAdapter<NaufragioVO> {

    private int resource = 0;
    private LayoutInflater inflater;
    private Context context;
    private String[][] lis;


    public NaufragioArrayAdapter(Context context, int resource){
        super(context, resource);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       NaufragioVO naufragio = getItem(position);

        View view = null;
        ViewHolder viewHolder = null;

        if(convertView == null){

            viewHolder = new ViewHolder();

            view = inflater.inflate(resource, parent, false);

            viewHolder.naufragioFoto = (ImageView) view.findViewById(R.id.naufragioFoto);
            viewHolder.txtNome = (TextView) view.findViewById(R.id.txtNome);
            viewHolder.txtProfundidade = (TextView) view.findViewById(R.id.txtProfundidade);

            view.setTag(viewHolder);

            convertView = view;

        }else{
            viewHolder = (ViewHolder)convertView.getTag();
            view = convertView;
        }

        if(naufragio.getFoto() != null){

           /* String strPath = "/mnt/sdcard/contact/"+lis[position][3].toString();
            ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.naufragioFoto);
            imgPhoto.setPadding(5, 5, 5, 5);
            Bitmap bm = BitmapFactory.decodeFile(strPath);
            int width=100;
            int height=100;
            Bitmap resizedbitmap = Bitmap.createScaledBitmap(bm, width, height, true);
            imgPhoto.setImageBitmap(resizedbitmap);*/


            Bitmap fotoNaufragio = BitmapFactory.decodeFile(naufragio.getFoto());
            Bitmap fotoReduzida = Bitmap.createScaledBitmap(fotoNaufragio, 200, 200,true);
            viewHolder.naufragioFoto.setImageBitmap(fotoReduzida);
        }else{
            Drawable semFoto = view.getResources().getDrawable(R.drawable.ic_no_image);
            viewHolder.naufragioFoto.setImageDrawable(semFoto);

        }
        viewHolder.txtNome.setText(naufragio.getNome());
        viewHolder.txtProfundidade.setText(naufragio.getProfundidade());


        return view;
    }

    private static class ViewHolder{
        ImageView naufragioFoto;
        TextView txtNome;
        TextView txtProfundidade;
    }
}
