package net.kodlar.kouyemek;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CardView cardViewCafeler;
    CardView cardViewYemekhane;
    TextView menuName;
    TextView cafeInfoClick;
    ImageView backIcon;
    ImageView menuIcoN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        cafeInfoClick = (TextView) findViewById(R.id.cafeClickInfo);
        cafeInfoClick.setVisibility(View.GONE);

        menuName = (TextView) findViewById(R.id.menuName);
        menuName.setText("Kou Yemek");
        menuName.setGravity(RelativeLayout.CENTER_IN_PARENT);

        backIcon = (ImageView) findViewById(R.id.backIcon);
        backIcon.setVisibility(View.GONE);

        menuIcoN = (ImageView) findViewById(R.id.logo);
        menuIcoN.setVisibility(View.GONE);

        cardViewCafeler=(CardView)findViewById(R.id.cardCafeler);
        cardViewYemekhane=(CardView)findViewById(R.id.cardYemekhane);
    }
    public void cafelereGit(View view){
        if (InternetConnection.checkConnection(getApplicationContext())) {
            try {
                Intent intent = new Intent(getApplicationContext(), CafesActivity.class);
                startActivity(intent);
            }catch (Exception e){
                System.out.println("Hata Oldu "+e);
            }
        }else{
            Toast.makeText(getApplicationContext(), "İnternet Bağlantınız Yok !", Toast.LENGTH_SHORT).show();
        }
    }
    public void yemekhaneyeGit(View view){
        if (InternetConnection.checkConnection(getApplicationContext())) {
            try {
                Intent intent = new Intent(getApplicationContext(), YemekhaneActivity.class);
                startActivity(intent);
            }catch (Exception e){
                System.out.println("Hata Oldu "+e);
            }
        }else{
            Toast.makeText(getApplicationContext(), "İnternet Bağlantınız Yok !", Toast.LENGTH_SHORT).show();
        }
    }
    public void hakkindayaGit(View view){

            try {
                Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(intent);
            }catch (Exception e){
                System.out.println("Hata Oldu "+e);
            }

    }
    public void sponsorlaraGit(View view){
        if (InternetConnection.checkConnection(getApplicationContext())) {
            try {
                Intent intent = new Intent(getApplicationContext(), SponsorlarActivity.class);
                startActivity(intent);
            }catch (Exception e){
                System.out.println("Hata Oldu "+e);
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "İnternet Bağlantınız Yok !", Toast.LENGTH_SHORT).show();
        }

    }
}
