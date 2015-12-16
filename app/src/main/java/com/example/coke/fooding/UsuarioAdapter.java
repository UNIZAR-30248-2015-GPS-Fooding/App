package com.example.coke.fooding;

/**
 * Created by coke on 07/10/2015.
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
    private final Integer[] imgid;

    public UsuarioAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.usuario, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    @Override
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.usuario, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.itemUser);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.iconUser);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        return rowView;

    };
}