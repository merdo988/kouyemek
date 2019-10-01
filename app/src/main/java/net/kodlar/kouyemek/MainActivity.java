package net.kodlar.kouyemek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView cardViewCafeler;
    CardView cardViewYemekhane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardViewCafeler=(CardView)findViewById(R.id.cardCafeler);
        cardViewYemekhane=(CardView)findViewById(R.id.cardYemekhane);
        setContentView(R.layout.activity_main);
    }

    public void cafelereGit(View view){
        try {
            Intent intent = new Intent(getApplicationContext(), CafesActivity.class);
            startActivity(intent);
        }catch (Exception e){
            System.out.println("Hata Oldu "+e);
        }
    }
    public void yemekhaneyeGit(View view){
        try {
            Intent intent = new Intent(getApplicationContext(), YemekhaneActivity.class);
            startActivity(intent);
        }catch (Exception e){
            System.out.println("Hata Oldu "+e);
        }

    }
}
