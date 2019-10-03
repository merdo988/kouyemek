package net.kodlar.kouyemek;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CafesMenuActivity extends AppCompatActivity {
    ListView listviewCafesMenu;
    ArrayList<CafesMenu> CafesMenu ;
    CustomListViewAdapterCafesMenu customListViewAdapterCafesMenu;
    ImageView cafeLogo;
    TextView cafeName;
    RelativeLayout layoutInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafes_menu);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        cafeName = (TextView) findViewById(R.id.menuName);
        cafeLogo = (ImageView) findViewById(R.id.logo);
        cafeName.setText(getIntent().getStringExtra("cafeName"));
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("cafeLogo")).into(cafeLogo);

        CafesMenu = new ArrayList<CafesMenu>();
        CafesMenu.add(new CafesMenu("Lavaş Tavuk Döner Dürüm" , "100 gr. tavuk döner, domates, turşu, sos, göbek marul, nane, mayonez" ,"8,00 TL", R.drawable.tavuk_doner ));
        CafesMenu.add(new CafesMenu("Pide Ekmek Arası Tavuk Döner" , "100 gr. tavuk döner, domates, turşu, sos, göbek marul, nane, mayonez" ,"8,00 TL", R.drawable.tavuk_doner ));
        CafesMenu.add(new CafesMenu("Sütlü Ekmek Arası Tavuk Döner" , "110 gr. tavuk döner, domates, turşu, sos, göbek marul, nane, mayonez" ,"8,50 TL", R.drawable.tavuk_doner ));

        layoutInfo = (RelativeLayout) findViewById(R.id.cafeInfoLayoutClick);
        layoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplicationContext(), CafeInfoActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    System.out.println("Hata Oldu "+e);
                }
            }
        });
        customListViewAdapterCafesMenu = new CustomListViewAdapterCafesMenu(this,R.layout.list_view_item_cafes_menu,CafesMenu);
        listviewCafesMenu = (ListView)findViewById(R.id.cafes_menu_list_view);
        listviewCafesMenu.setAdapter(customListViewAdapterCafesMenu);

    }
    public void turnBack(View view){
        finish();
    }
}
