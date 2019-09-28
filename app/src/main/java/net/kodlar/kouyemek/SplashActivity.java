package net.kodlar.kouyemek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        System.out.println("Merhaba");
        finish();
    }
}
