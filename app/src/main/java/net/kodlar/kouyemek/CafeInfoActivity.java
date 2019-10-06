package net.kodlar.kouyemek;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CafeInfoActivity extends AppCompatActivity {
    private TextView cafeName;
    private ImageView cafeLogo;
    private TextView cafeInfoClick,menuName;
    private RelativeLayout kare1Layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_info);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        kare1Layout=(RelativeLayout)findViewById(R.id.kare1Layout);



    }
    public void Ara(View view){

    }
    public void turnBack(View view){
        finish();
    }
}
