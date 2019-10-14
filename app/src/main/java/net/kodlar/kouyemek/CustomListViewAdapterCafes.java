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
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class CustomListViewAdapterCafes extends ArrayAdapter<Cafes> {
    private final LayoutInflater inflater;
    private final Context context;
    private final ArrayList<Cafes> cafes;
    Cafes c;

    public CustomListViewAdapterCafes(@NonNull Context context,int resource, ArrayList<Cafes> cafes) {
        super(context, resource, cafes);
        this.context = context;
        this.cafes = cafes;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_view_item_cafes, null);
        }
        c = getItem(position);
        if (c != null) {
            ImageView cafeLogo = (ImageView)v.findViewById(R.id.sponsorLogo);
            TextView cafeName = (TextView)v.findViewById(R.id.sponsorText);

            if(cafeLogo != null && cafeName != null){
                cafeName.setText(c.getCafeName());
                Picasso.with(getContext()).load(c.getLogoResourceUrl()).into(cafeLogo);
            }
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = getItem(position);
                if (InternetConnection.checkConnection(context)){
                    try {
                        Intent intent = new Intent(getContext(), CafesMenuActivity.class);
                        intent.putExtra("cafeName", c.getCafeName());
                        intent.putExtra("cafeLogo", c.getLogoResourceUrl());
                        intent.putExtra("cafeID", c.getCafeID());
                        context.startActivity(intent);
                    }catch (Exception e){
                        System.out.println("Hata Oldu "+e);
                    }
                }else{
                    Toast.makeText(context, "İnternet Bağlantısı Yok !!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return v;
    }

}
