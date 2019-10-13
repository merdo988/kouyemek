package net.kodlar.kouyemek;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CafeInfoActivity extends AppCompatActivity {
    private TextView cafeName, name;
    private ImageView cafeLogo,circleImageInfoScreen ;
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

        cafeName = (TextView) findViewById(R.id.menuName);
        cafeLogo = (ImageView) findViewById(R.id.logo);
        cafeInfoClick = (TextView) findViewById(R.id.cafeClickInfo);
        cafeInfoClick.setVisibility(View.GONE);
        cafeName.setText(getIntent().getStringExtra("cafeName"));
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("cafeLogo")).into(cafeLogo);

        circleImageInfoScreen = (ImageView) findViewById(R.id.circleImageInfoScreen);
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("cafeLogo")).into(circleImageInfoScreen);

        name = (TextView) findViewById(R.id.textViewCafeName);
        name.setText(getIntent().getStringExtra("cafeName"));
        cafeName.setGravity(RelativeLayout.CENTER_IN_PARENT);


        kare1Layout = (RelativeLayout) findViewById(R.id.callIcon);
        kare2Layout=(RelativeLayout)findViewById(R.id.webIcon);
        kare3Layout=(RelativeLayout)findViewById(R.id.mapIcon);

        Galeri = new ArrayList<Galeri>();
        Galeri.add(new Galeri("http://kodlar.net/kouyemek/meal_img/bahcivan_kebabi_128x128.png"));
        Galeri.add(new Galeri("http://kodlar.net/kouyemek/meal_img/bahcivan_kebabi_128x128.png"));
        Galeri.add(new Galeri("http://kodlar.net/kouyemek/meal_img/bahcivan_kebabi_128x128.png"));
    }

    public void Ara(View view) {
        try{
            String phone = "+34666777888";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Bir hata oluştu!!", Toast.LENGTH_SHORT).show();
        }

    }
    public void Web(View view){
        try{
            String url = "https://instagram.com/mert.php";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Bir hata oluştu!!", Toast.LENGTH_SHORT).show();
        }

    }

    public void Adres(View view){
        try{
            Uri uri = Uri.parse("geo:0,0?q=40.9993216,29.1274752 (Maninagar)");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Bir hata oluştu!!", Toast.LENGTH_SHORT).show();
        }
    }
    public void turnBack(View view){
        finish();
    }
}
