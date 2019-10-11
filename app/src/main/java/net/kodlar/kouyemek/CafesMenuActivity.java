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

public class CafesMenuActivity extends AppCompatActivity {
    String url;
    int cafeID;
    ListView listviewCafesMenu;
    ArrayList<CafesMenu> CafesMenu ;
    ProgressDialog dialog;
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
        cafeID = Integer.parseInt(getIntent().getStringExtra("cafeID"));
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("cafeLogo")).into(cafeLogo);

        CafesMenu = new ArrayList<CafesMenu>();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();
        url = "http://kodlar.net/kouyemek/products.php?product_cafe_id="+cafeID;
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "İnternet Bağlantınızı Kontrol Edin!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(CafesMenuActivity.this);
        rQueue.add(request);

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
        listviewCafesMenu = (ListView)findViewById(R.id.galeri_menu_list_view);
        listviewCafesMenu.setAdapter(customListViewAdapterCafesMenu);

    }

    void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray productNameArray = object.getJSONArray("product_name");
            JSONArray productDescArray = object.getJSONArray("product_desc");
            JSONArray productPriceArray = object.getJSONArray("product_price");
            JSONArray productImgUrlArray = object.getJSONArray("product_img_url");

            for(int i = 0; i < productNameArray.length(); ++i) {
                CafesMenu.add(new CafesMenu(productNameArray.getString(i) ,productDescArray.getString(i),productPriceArray.getString(i),productImgUrlArray.getString(i)));
            }

            customListViewAdapterCafesMenu = new CustomListViewAdapterCafesMenu(this,R.layout.list_view_item_cafes_menu,CafesMenu);
            listviewCafesMenu = (ListView)findViewById(R.id.galeri_menu_list_view);
            if(listviewCafesMenu != null){
                listviewCafesMenu.setAdapter(customListViewAdapterCafesMenu);
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
