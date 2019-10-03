package net.kodlar.kouyemek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView cardViewCafeler;
    CardView cardViewYemekhane;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardViewCafeler=(CardView)findViewById(R.id.cardCafeler);
        cardViewYemekhane=(CardView)findViewById(R.id.cardYemekhane);


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
