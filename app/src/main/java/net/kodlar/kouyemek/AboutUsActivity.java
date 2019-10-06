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

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;

public class AboutUsActivity extends AppCompatActivity {
    ExpandableHeightListView listviewSocial;
    ArrayList<AboutUs> socialListView;
    TextView cafeInfoClick;
    ImageView img , socialIcon;
    TextView menuName , socialInfo;
    CustomListViewAdapterAboutUs customListViewAdapterAboutUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
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



        socialListView.add(new AboutUs(R.drawable.contact_icon , getString(R.string.about_contact_us)));
        socialListView.add(new AboutUs(R.drawable.website_icon , getString(R.string.about_website)));
        socialListView.add(new AboutUs(R.drawable.instagram_icon , getString(R.string.about_instagram)));
        socialListView.add(new AboutUs(R.drawable.playstore_icon , getString(R.string.about_play_store)));
        socialListView.add(new AboutUs(R.drawable.facebook_icon , getString(R.string.about_facebook)));
        socialListView.add(new AboutUs(R.drawable.github_icon , getString(R.string.about_github)));
        socialListView.add(new AboutUs(R.drawable.twitter_icon , getString(R.string.about_twitter)));
        socialListView.add(new AboutUs(R.drawable.youtube_icon , getString(R.string.about_youtube)));


        customListViewAdapterAboutUs = new CustomListViewAdapterAboutUs(this,R.layout.list_view_item_about_us,socialListView);
        listviewSocial = (ExpandableHeightListView)findViewById(R.id.expandable_listview);
        listviewSocial.setAdapter(customListViewAdapterAboutUs);
        listviewSocial.setExpanded(true);
    }
    public void turnBack(View view){
        finish();
    }


}

