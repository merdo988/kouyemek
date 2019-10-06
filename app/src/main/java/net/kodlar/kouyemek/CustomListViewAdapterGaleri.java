package net.kodlar.kouyemek;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListViewAdapterGaleri extends ArrayAdapter<Galeri> {
    private final LayoutInflater inflater;
    private final Context context;
    private final ArrayList<Galeri> galeri;
    Galeri a;

    public CustomListViewAdapterGaleri(@NonNull Context context,int resource, ArrayList<Galeri> galeri) {
        super(context, resource, galeri);
        this.context = context;
        this.galeri = galeri;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_view_item_cafe_galari, null);
        }
        a = getItem(position);
        if (a != null) {
            ImageView resim = (ImageView)v.findViewById(R.id.resim);

            if(resim != null){

                Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(resim);
            }
        }
        return v;
    }
}
