package net.kodlar.kouyemek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardView=(CardView)findViewById(R.id.cardCafeler);
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
}
