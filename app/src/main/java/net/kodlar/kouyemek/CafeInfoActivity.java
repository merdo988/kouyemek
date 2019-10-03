package net.kodlar.kouyemek;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CafeInfoActivity extends AppCompatActivity {
    private TextView cafeName;
    private ImageView cafeLogo;
    private TextView cafeInfoClick,menuName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_info);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        cafeInfoClick = (TextView) findViewById(R.id.cafeClickInfo);
        cafeInfoClick.setVisibility(View.GONE);
        menuName = (TextView) findViewById(R.id.menuName);
        menuName.setText("Kafe Bilgi");
        cafeLogo = (ImageView) findViewById(R.id.logo);
        cafeLogo.setVisibility(View.GONE);

        cafeName = (TextView) findViewById(R.id.cafeName);
        cafeLogo = (ImageView) findViewById(R.id.cafeLogo);
        cafeName.setText(getIntent().getStringExtra("cafeName"));
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("cafeLogo")).into(cafeLogo);

    }
    public void turnBack(View view){
        finish();
    }
}
