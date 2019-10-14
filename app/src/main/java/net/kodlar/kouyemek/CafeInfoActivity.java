package net.kodlar.kouyemek;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
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

public class CafeInfoActivity extends AppCompatActivity {
    private TextView cafeName, name;
    private ImageView cafeLogo,circleImageInfoScreen ;
    private TextView cafeInfoClick, menuName;
    private RelativeLayout kare1Layout;
    private RelativeLayout kare2Layout;
    private RelativeLayout kare3Layout;
    private TextView cafeHakkinda;
    private ImageView resim1;
    private ImageView resim2;
    private ImageView resim3;
    private ImageView resim4;
    private ImageView resim5;
    private ImageView resim6;
    private RelativeLayout rel1;
    private RelativeLayout rel2;
    ProgressDialog dialog;
    String lokasyon;
    String phone;
    String webUrl;
    int cafeId;
    String url;
    float dpWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_info);
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        dpWidth= displayMetrics.widthPixels / displayMetrics.density;
        rel1=(RelativeLayout)findViewById(R.id.rel1);
        rel2=(RelativeLayout)findViewById(R.id.rel2);
        /*
        Li.LayoutParams lp = new RelativeLayout.LayoutParams((int)(dpWidth/2.0),RelativeLayout.LayoutParams.WRAP_CONTENT);
        rel1.setLayoutParams(lp);
        rel2.setLayoutParams(lp);
*/
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setCustomView(R.layout.toolbar);
            cafeName = (TextView) findViewById(R.id.menuName);
            cafeLogo = (ImageView) findViewById(R.id.logo);
            cafeInfoClick = (TextView) findViewById(R.id.cafeClickInfo);
            cafeInfoClick.setVisibility(View.GONE);

            cafeName.setText(getIntent().getStringExtra("cafeName"));
            Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("cafeLogo")).into(cafeLogo);
            cafeId=getIntent().getIntExtra("cafeId",0);
            System.out.println("Kafe ID"+cafeId);
            circleImageInfoScreen = (ImageView) findViewById(R.id.circleImageInfoScreen);
            Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("cafeLogo")).into(circleImageInfoScreen);

            name = (TextView) findViewById(R.id.textViewCafeName);
            name.setText(getIntent().getStringExtra("cafeName"));
            cafeName.setGravity(RelativeLayout.CENTER_IN_PARENT);

            kare1Layout = (RelativeLayout) findViewById(R.id.callIcon);
            kare2Layout=(RelativeLayout)findViewById(R.id.webIcon);
            kare3Layout=(RelativeLayout)findViewById(R.id.mapIcon);
            cafeHakkinda=(TextView)findViewById(R.id.cafe_hakkinda);
            resim1=(ImageView)findViewById(R.id.resim1);
            resim2=(ImageView)findViewById(R.id.resim2);
            resim3=(ImageView)findViewById(R.id.resim3);
            resim4=(ImageView)findViewById(R.id.resim4);
        resim5=(ImageView)findViewById(R.id.resim5);
        resim6=(ImageView)findViewById(R.id.resim6);

            dialog = new ProgressDialog(this);
            dialog.setMessage("Loading....");
            dialog.show();
            url = "http://kodlar.net/kouyemek/cafeinfo.php?cafe_id="+cafeId;
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
            RequestQueue rQueue = Volley.newRequestQueue(CafeInfoActivity.this);
            rQueue.add(request);

    }


    void parseJsonData(String jsonString) {
        System.out.println(jsonString);
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray cafe_Info= object.getJSONArray("cafe_info");
            JSONArray cafe_Ara = object.getJSONArray("cafe_ara");
            JSONArray cafe_Web = object.getJSONArray("cafe_web");
            JSONArray cafe_Adres = object.getJSONArray("cafe_adres");
            JSONArray cafe_foto1url= object.getJSONArray("cafe_foto1url");
            JSONArray cafe_foto2url= object.getJSONArray("cafe_foto2url");
            JSONArray cafe_foto3url= object.getJSONArray("cafe_foto3url");
            JSONArray cafe_foto4url= object.getJSONArray("cafe_foto4url");
            JSONArray cafe_foto5url= object.getJSONArray("cafe_foto5url");
            JSONArray cafe_foto6url= object.getJSONArray("cafe_foto6url");
            cafeHakkinda.setText(cafe_Info.getString(0));
            phone=cafe_Ara.getString(0);
            webUrl=cafe_Web.getString(0);
            lokasyon=cafe_Adres.getString(0);

            Picasso.with(getApplicationContext()).load(cafe_foto1url.getString(0)).into(resim1);
            Picasso.with(getApplicationContext()).load(cafe_foto2url.getString(0)).into(resim2);
            Picasso.with(getApplicationContext()).load(cafe_foto3url.getString(0)).into(resim3);
            Picasso.with(getApplicationContext()).load(cafe_foto4url.getString(0)).into(resim4);
            Picasso.with(getApplicationContext()).load(cafe_foto5url.getString(0)).into(resim5);
            Picasso.with(getApplicationContext()).load(cafe_foto6url.getString(0)).into(resim6);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dialog.dismiss();
    }

    public void Ara(View view) {
        try{
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Bir hata oluştu!!", Toast.LENGTH_SHORT).show();
        }
    }
    public void Web(View view) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(webUrl));
            startActivity(i);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Bir hata oluştu!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Adres(View view){
        try{
            Uri uri = Uri.parse("geo:0,0?q="+lokasyon+" (Maninagar)");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Bir hata oluştu!!", Toast.LENGTH_SHORT).show();
        }
    }
    public void turnBack(View view){
        finish();
    }
}
