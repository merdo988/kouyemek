package net.kodlar.kouyemek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class CafesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cafes);
        ArrayList<Cafes> cafes = new ArrayList<Cafes>();
        cafes.add(new Cafes("Mahur Kafe",R.drawable.logo));
        cafes.add(new Cafes("Deep Kafe",R.drawable.logo));
        CustomListViewAdapterCafes customListViewAdapterCafes = new CustomListViewAdapterCafes(this,R.layout.list_view_item_cafes,cafes);

        ListView listviewCafes = (ListView)findViewById(R.id.cafes_list_view);
        if(listviewCafes != null){
            listviewCafes.setAdapter(customListViewAdapterCafes);
        }
    }
}
