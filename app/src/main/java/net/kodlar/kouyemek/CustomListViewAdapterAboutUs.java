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

import java.util.ArrayList;

public class CustomListViewAdapterAboutUs extends ArrayAdapter<AboutUs> {
    private final LayoutInflater inflater;
    private final Context context;
    private final ArrayList<AboutUs> aboutUs;
    AboutUs a;
    
    public CustomListViewAdapterAboutUs(@NonNull Context context,int resource, ArrayList<AboutUs> aboutUs) {
        super(context, resource, aboutUs);
        this.context = context;
        this.aboutUs = aboutUs;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_view_item_about_us, null);
        }
        a = getItem(position);
        if (a != null) {
            ImageView socialLogo = (ImageView)v.findViewById(R.id.logo_social);
            TextView socialInfo = (TextView)v.findViewById(R.id.info_social);

            if(socialLogo != null && socialInfo != null){
                socialInfo.setText(a.getSocialInfo());
                socialLogo.setImageResource(a.getSocialIcon());
            }
        }
        return v;
    }
}
