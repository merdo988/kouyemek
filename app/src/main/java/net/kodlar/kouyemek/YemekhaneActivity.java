package net.kodlar.kouyemek;

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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class YemekhaneActivity extends AppCompatActivity {
    ProgressDialog dialog;
    TextView tarih;
    String bugun;
    TextView anayemek2Text;
    TextView anayemek1Text;
    ImageView anayemek2;
    ImageView anayemek1;
    String url="http://kodlar.net/kouyemek/meals.php?date=11.09.2019";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemekhane);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                System.out.println("veri çekildi");
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(YemekhaneActivity.this);
        rQueue.add(request);

        anayemek2Text=(TextView)findViewById(R.id.anayemek2Text);
        anayemek1Text=(TextView)findViewById(R.id.anayemek1Text);
        anayemek2=(ImageView)findViewById(R.id.anayemek2);
        anayemek1=(ImageView)findViewById(R.id.anayemek1);
        tarih=(TextView)findViewById(R.id.tarih);
        bugun=getDateToday();


    }
    void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray mealsObject = object.getJSONArray("meals");
            JSONArray mealsName = mealsObject.getJSONArray(0);
            JSONArray mealsImgUrl = mealsObject.getJSONArray(1);
            JSONArray mealsKalori = mealsObject.getJSONArray(2);

            JSONArray soupsObject = object.getJSONArray("soups");
            JSONArray soupsName = soupsObject.getJSONArray(0);
            JSONArray soupsImgUrl = soupsObject.getJSONArray(1);
            JSONArray soupsKalori = soupsObject.getJSONArray(2);

            JSONArray snacksObject = object.getJSONArray("snacks");
            JSONArray snacksName = snacksObject.getJSONArray(0);
            JSONArray snacksImgUrl = snacksObject.getJSONArray(1);
            JSONArray snacksKalori = snacksObject.getJSONArray(2);

            anayemek1Text.setText(mealsName.getString(1));

            if(anayemek2 != null && mealsImgUrl != null){
                anayemek2Text.setText(mealsName.getString(0));
                Picasso.with(getApplicationContext()).load(mealsImgUrl.getString(0)).into(anayemek2);
            }
            if(anayemek1 != null && mealsImgUrl != null){
                anayemek1Text.setText(mealsName.getString(1));
                Picasso.with(getApplicationContext()).load(mealsImgUrl.getString(1)).into(anayemek1);
            }
            System.out.println(snacksName.getString(0) + "->" + snacksImgUrl.getString(0)+"->"+snacksKalori.getString(0));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        dialog.dismiss();
    }


    public String getDateToday(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day=c.get(Calendar.DAY_OF_MONTH);
        String date=String.format("%02d", day)+"."+String.format("%02d", month)+"."+String.valueOf(year);
        tarih.setText(date);
        return date;
    }
}
