package com.GPS.app.fooding;

/**
 * Created by Fooding on 07/10/2015.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UsuarioAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final String[] puntos;
    private final Integer[] imgid;

    public UsuarioAdapter(Activity context, String[] itemname, Integer[] imgid, String[] puntos) {
        super(context, R.layout.usuario, itemname);

        this.context=context;
        this.itemname=itemname;
        this.puntos=puntos;
        this.imgid=imgid;
    }

    @Override
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.usuario, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.itemUser);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.iconUser);
        TextView puntostxt = (TextView) rowView.findViewById(R.id.numLikes);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        puntostxt.setText(puntos[position]);
        return rowView;

    };
}