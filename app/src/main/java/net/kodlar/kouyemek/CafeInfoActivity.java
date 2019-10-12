package net.kodlar.kouyemek;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CafeInfoActivity extends AppCompatActivity {
    private TextView cafeName;
    private ImageView cafeLogo;
    private TextView cafeInfoClick, menuName;
    private RelativeLayout kare1Layout;
    private RelativeLayout kare2Layout;
    private RelativeLayout kare3Layout;
    ArrayList<Galeri> Galeri ;
    ListView listviewGaleri;;
    CustomListViewAdapterGaleri customListViewAdapterGaleri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_info);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        kare1Layout = (RelativeLayout) findViewById(R.id.callIcon);
        kare2Layout=(RelativeLayout)findViewById(R.id.webIcon);
        kare3Layout=(RelativeLayout)findViewById(R.id.mapIcon);
        Galeri = new ArrayList<Galeri>();
        Galeri.add(new Galeri("http://kodlar.net/kouyemek/meal_img/bahcivan_kebabi_128x128.png"));
        Galeri.add(new Galeri("http://kodlar.net/kouyemek/meal_img/bahcivan_kebabi_128x128.png"));
        Galeri.add(new Galeri("http://kodlar.net/kouyemek/meal_img/bahcivan_kebabi_128x128.png"));
        customListViewAdapterGaleri= new CustomListViewAdapterGaleri(this,R.layout.list_view_item_cafe_galari,Galeri);
        listviewGaleri = (ListView)findViewById(R.id.galeri_menu_list_view);
        listviewGaleri.setAdapter(customListViewAdapterGaleri);

    }

    public void Ara(View view) {
        String phone = "+34666777888";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }
    public void Web(View view){
        String url = "https://instagram.com/mert.php";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void Adres(View view){
        Uri uri = Uri.parse("geo:0,0?q=40.9993216,29.1274752 (Maninagar)");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void turnBack(View view){
        finish();
    }
}
