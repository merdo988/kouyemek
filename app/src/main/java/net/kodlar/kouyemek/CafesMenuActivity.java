package net.kodlar.kouyemek;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class CafesMenuActivity extends AppCompatActivity {
    ListView listviewCafesMenu;
    ArrayList<CafesMenu> CafesMenu ;
    CustomListViewAdapterCafesMenu customListViewAdapterCafesMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafes_menu);

        CafesMenu = new ArrayList<CafesMenu>();
        CafesMenu.add(new CafesMenu("Lavaş Tavuk Döner Dürüm" , "100 gr. tavuk döner, domates, turşu, sos, göbek marul, nane, mayonez" ,"8,00 TL", R.drawable.tavuk_doner ));
        CafesMenu.add(new CafesMenu("Pide Ekmek Arası Tavuk Döner" , "100 gr. tavuk döner, domates, turşu, sos, göbek marul, nane, mayonez" ,"8,00 TL", R.drawable.tavuk_doner ));
        CafesMenu.add(new CafesMenu("Sütlü Ekmek Arası Tavuk Döner" , "110 gr. tavuk döner, domates, turşu, sos, göbek marul, nane, mayonez" ,"8,50 TL", R.drawable.tavuk_doner ));


        customListViewAdapterCafesMenu = new CustomListViewAdapterCafesMenu(this,R.layout.list_view_item_cafes_menu,CafesMenu);
        listviewCafesMenu = (ListView)findViewById(R.id.cafes_menu_list_view);

            listviewCafesMenu.setAdapter(customListViewAdapterCafesMenu);


    }
}
