package net.kodlar.kouyemek;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CafesActivity extends AppCompatActivity {
    String url = "http://kodlar.net/kouyemek/kafeler.php";
    ProgressDialog dialog;
    ListView listviewCafes;
    ArrayList<Cafes> cafes ;
    TextView menuName;
    ImageView img;
    TextView cafeInfoClick;
    CustomListViewAdapterCafes customListViewAdapterCafes;
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
        menuName.setText("Kafeler");
        menuName.setGravity(RelativeLayout.CENTER_IN_PARENT);

        cafes = new ArrayList<Cafes>();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(CafesActivity.this);
        rQueue.add(request);
        setContentView(R.layout.activity_cafes);

    }

    void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray cafelerArray = object.getJSONArray("cafe_name");
            JSONArray cafelerLogoArray = object.getJSONArray("cafe_logo");

            for(int i = 0; i < cafelerArray.length(); ++i) {
                cafes.add(new Cafes(cafelerArray.getString(i),cafelerLogoArray.getString(i)));
            }

            customListViewAdapterCafes = new CustomListViewAdapterCafes(this,R.layout.list_view_item_cafes,cafes);
            listviewCafes = (ListView)findViewById(R.id.cafes_list_view);

            if(listviewCafes != null){
                listviewCafes.setAdapter(customListViewAdapterCafes);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        dialog.dismiss();
    }
    public void turnBack(View view){
        finish();
    }


}
