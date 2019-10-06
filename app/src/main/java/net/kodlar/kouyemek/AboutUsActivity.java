package net.kodlar.kouyemek;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AboutUsActivity extends AppCompatActivity {
    ListView listviewSocial;
    ArrayList<AboutUs> socialListView;
    TextView cafeInfoClick;
    ImageView img , socialIcon;
    TextView menuName , socialInfo;
    CustomListViewAdapterAboutUs customListViewAdapterAboutUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        cafeInfoClick = (TextView) findViewById(R.id.cafeClickInfo);
        cafeInfoClick.setVisibility(View.GONE);
        img = (ImageView) findViewById(R.id.logo);
        img.setVisibility(View.GONE);

        menuName = (TextView) findViewById(R.id.menuName);
        menuName.setText("KN Yazılım ve Teknoloji Hakkında");
        menuName.setGravity(RelativeLayout.CENTER_IN_PARENT);

        socialIcon = (ImageView) findViewById(R.id.logo_social);
        socialInfo = (TextView) findViewById(R.id.info_social);

        socialListView = new ArrayList<AboutUs>();



        socialListView.add(new AboutUs(R.drawable.social_icon , "Facebook'ta"));
        socialListView.add(new AboutUs(R.drawable.social_icon , "Facebook'ta"));
        socialListView.add(new AboutUs(R.drawable.social_icon , "Facebook'ta"));
        socialListView.add(new AboutUs(R.drawable.social_icon , "Facebook'ta"));
        socialListView.add(new AboutUs(R.drawable.social_icon , "Facebook'ta"));

//        setContentView(R.layout.activity_about_us);
        customListViewAdapterAboutUs = new CustomListViewAdapterAboutUs(this,R.layout.list_view_item_about_us,socialListView);
        listviewSocial = (ListView)findViewById(R.id.about_us_list_view);
        listviewSocial.setAdapter(customListViewAdapterAboutUs);
        listviewSocial.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        setContentView(R.layout.activity_about_us);
    }
    public void turnBack(View view){
        finish();
    }


}

