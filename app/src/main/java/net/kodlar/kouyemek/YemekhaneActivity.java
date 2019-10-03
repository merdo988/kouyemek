package net.kodlar.kouyemek;

import android.app.ProgressDialog;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class YemekhaneActivity extends AppCompatActivity {
    ProgressDialog dialog;
    TextView tarih;
    int gun;
    int ay;
    int yil;
    String bugun;
    TextView anayemek2Text;
    TextView anayemek1Text;
    TextView corba2Text;
    TextView corba1Text;
    TextView yardimciYemekText;
    TextView tatliText;
    RelativeLayout noInternet;

    RelativeLayout anaRelat;
    RelativeLayout corbaRelat;
    RelativeLayout eklerRelat;

    CircleImageView anayemek2;
    CircleImageView anayemek1;
    CircleImageView corba2;
    CircleImageView corba1;
    CircleImageView yardimciYemek;
    CircleImageView tatli;
    String url;
    int maxDay;
    String dayText;
    Calendar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemekhane);
        anayemek2Text=(TextView)findViewById(R.id.anayemek2Text);
        anayemek1Text=(TextView)findViewById(R.id.anayemek1Text);
        anayemek2=(CircleImageView)findViewById(R.id.anayemek2);
        anayemek1=(CircleImageView)findViewById(R.id.anayemek1);
        corba2Text=(TextView)findViewById(R.id.corba2Text);
        corba1Text=(TextView)findViewById(R.id.corba1Text);
        corba2=(CircleImageView)findViewById(R.id.corba2);
        corba1=(CircleImageView)findViewById(R.id.corba1);
        yardimciYemekText=(TextView)findViewById(R.id.yardimciText);
        yardimciYemek=(CircleImageView)findViewById(R.id.yardimciIcon);
        tatliText=(TextView)findViewById(R.id.icecekText);
        tatli=(CircleImageView)findViewById(R.id.icecekIcon);
        tarih=(TextView)findViewById(R.id.tarih);
        anaRelat=(RelativeLayout)findViewById(R.id.anaRelat);
        corbaRelat=(RelativeLayout)findViewById(R.id.corbaRelat);
        eklerRelat=(RelativeLayout)findViewById(R.id.eklerRelat);
        noInternet=(RelativeLayout)findViewById(R.id.noInternet);
        maxDay=Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        c = Calendar.getInstance();
        gun = c.get(Calendar.DAY_OF_MONTH);
        ay = c.get(Calendar.MONTH)+1;
        yil=c.get(Calendar.YEAR);
        String date=String.format("%02d", gun)+"."+String.format("%02d", ay)+"."+String.valueOf(yil);
        tarih.setText(date);
        yukle(date);


    }
    void parseJsonData(String jsonString) {

        if(!jsonString.equals("null")) {


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
                if (anayemek2 != null && mealsImgUrl != null) {
                    anayemek2Text.setText(mealsName.getString(0));
                    anayemek1Text.setText(mealsName.getString(1));
                    Picasso.with(getApplicationContext()).load(mealsImgUrl.getString(0)).into(anayemek2);
                    Picasso.with(getApplicationContext()).load(mealsImgUrl.getString(1)).into(anayemek1);

                    corba2Text.setText(soupsName.getString(0));
                    corba1Text.setText(soupsName.getString(1));
                    Picasso.with(getApplicationContext()).load(soupsImgUrl.getString(0)).into(corba2);
                    Picasso.with(getApplicationContext()).load(soupsImgUrl.getString(1)).into(corba1);

                    yardimciYemekText.setText(snacksName.getString(1));
                    tatliText.setText(snacksName.getString(0));
                    Picasso.with(getApplicationContext()).load(snacksImgUrl.getString(1)).into(yardimciYemek);
                    Picasso.with(getApplicationContext()).load(snacksImgUrl.getString(0)).into(tatli);
                    anaRelat.setVisibility(View.VISIBLE);
                    corbaRelat.setVisibility(View.VISIBLE);
                    eklerRelat.setVisibility(View.VISIBLE);
                    noInternet.setVisibility(View.GONE);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            anaRelat.setVisibility(View.GONE);
            corbaRelat.setVisibility(View.GONE);
            eklerRelat.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
        }


    }
    public void yukle(String tarihimiz){
        url="http://kodlar.net/kouyemek/meals.php?date="+tarihimiz;
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

        dialog.dismiss();


    }
    public void ertesiGun(View view){
        if(gun!=maxDay){
            dayText=dayTurkish(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(c.getTime()));
            gun=gun+1;
            String date=String.format("%02d", gun)+"."+String.format("%02d", ay)+"."+String.valueOf(yil);
            tarih.setText(date);
            yukle(date);
        }

    }
    public void evvelsiGun(View view){
        if(gun!=1){
            gun=gun-1;
            String date=String.format("%02d", gun)+"."+String.format("%02d", ay)+"."+String.valueOf(yil);
            tarih.setText(date);
            yukle(date);
        }

    }
    public String dayTurkish(String gun){
        System.out.println("Bugünkü gün " + gun);
        return "0";
    }


    public void getDateToday(){
/*
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = Integer.parseInt(tarih.getText())+1;
        int day=c.get(Calendar.DAY_OF_MONTH);
        String date=String.format("%02d", day)+"."+String.format("%02d", month)+"."+String.valueOf(year);
        tarih.setText(date);
        return date;
        */
    }
}
