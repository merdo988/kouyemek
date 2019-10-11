package net.kodlar.kouyemek;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SponsorlarActivity extends AppCompatActivity {
    ListView listviewSponsorlar;
    ArrayList<Sponsorlar> sponsorlarListView;
    TextView sponsorIsmi;
    ImageView socialIcon;
    ProgressDialog dialog;
    CostumListViewAdapterSponsorlar customListViewAdapterSponsorlar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String url = "http://kodlar.net/kouyemek/sponsor.php";
        super.onCreate(savedInstanceState);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        sponsorlarListView = new ArrayList<Sponsorlar>();

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
        RequestQueue rQueue = Volley.newRequestQueue(SponsorlarActivity.this);
        rQueue.add(request);
        setContentView(R.layout.activity_sponsorlar);

    }
        void parseJsonData(String jsonString) {
            try {
                JSONObject object = new JSONObject(jsonString);

                JSONArray sponsorlarLogoArray = object.getJSONArray("sponsorlar_logo");
                JSONArray sponsorlarIsimArray = object.getJSONArray("sponsorlar_isim");
                System.out.println("Uzunluk : "+sponsorlarIsimArray.length());
                for(int i = 0; i < sponsorlarIsimArray.length(); i++) {
                    System.out.println(sponsorlarLogoArray.getString(i)+":"+sponsorlarIsimArray.getString(i));
                    sponsorlarListView.add(new Sponsorlar(sponsorlarLogoArray.getString(i),sponsorlarIsimArray.getString(i)));
                }


                customListViewAdapterSponsorlar = new CostumListViewAdapterSponsorlar(this, R.layout.list_view_item_sponsor, sponsorlarListView);
                listviewSponsorlar = (ListView) findViewById(R.id.sponsorlar_list_view);


                if (listviewSponsorlar != null) {
                    listviewSponsorlar.setAdapter(customListViewAdapterSponsorlar);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

    }

