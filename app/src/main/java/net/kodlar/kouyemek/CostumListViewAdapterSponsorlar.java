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
public class CostumListViewAdapterSponsorlar extends  ArrayAdapter<Sponsorlar>{
    private final LayoutInflater inflater;
    private final Context context;
    private final ArrayList<Sponsorlar> sponsorlar;
    Sponsorlar a;
    public CostumListViewAdapterSponsorlar(@NonNull Context context, int socialIcon, ArrayList<Sponsorlar> sponsorlar) {
        super(context, socialIcon,sponsorlar);
        this.context = context;
        this.sponsorlar = sponsorlar;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_view_item_sponsor, null);
        }
        a = getItem(position);
        if (a != null) {
            ImageView socialLogo = (ImageView)v.findViewById(R.id.logo);
            TextView socialInfo = (TextView)v.findViewById(R.id.sponsorText);

            if(socialLogo != null && socialInfo != null){
                socialInfo.setText(a.getSocialInfo());
                Picasso.with(getContext()).load(a.getSocialIcon()).into(socialLogo);

            }
        }
        return v;
    }
}
