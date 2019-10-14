package net.kodlar.kouyemek;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url;
                Intent i;
                a = getItem(position);
                if (InternetConnection.checkConnection(context)){
                    try {
                        switch (position){
                            case 0:
                                 url= "http://kodlar.net/kouyemek/yonlendir.php?yon=email";
                                i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                context.startActivity(i);
                                break;
                            case 1:
                                url = "http://kodlar.net/kouyemek/yonlendir.php?yon=web";
                                i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                context.startActivity(i);
                                break;
                            case 2:
                                url = "http://kodlar.net/kouyemek/yonlendir.php?yon=instagram";
                                i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                context.startActivity(i);
                                break;
                            case 3:
                                url = "http://kodlar.net/kouyemek/yonlendir.php?yon=playstore";
                                i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                context.startActivity(i);
                                break;
                            case 4:
                                url = "http://kodlar.net/kouyemek/yonlendir.php?yon=twitter";
                                i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                context.startActivity(i);
                                break;
                        }
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
