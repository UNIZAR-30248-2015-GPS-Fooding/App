package com.GPS.app.fooding;

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

public class RecetaAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;
    private final Double[] puntos;

    public RecetaAdapter(Activity context, String[] itemname, Integer[] imgid, Double[] puntos) {
        super(context, R.layout.receta, itemname);

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
        this.puntos=puntos;
    }

    @Override
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.receta, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        final ImageView favView = (ImageView)rowView.findViewById(R.id.iconStar);
        favView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO Usar metodo que dado un nombre de receta y un email, te devuelva un boolean que diga si ese user tiene esa receta como fav

                if (favView.getTag().equals("favON")){
                    favView.setImageResource(R.mipmap.no_fav_logo);
                    favView.setTag("favOFF");
                }
                else{
                    favView.setImageResource(R.mipmap.fav_logo);
                }

            }
        });

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        extratxt.setText(puntos[position] + "% de votos positivos");
        return rowView;

    };
}