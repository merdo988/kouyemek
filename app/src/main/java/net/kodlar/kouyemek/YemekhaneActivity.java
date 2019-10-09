package net.kodlar.kouyemek;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class YemekhaneActivity extends AppCompatActivity {
    ProgressDialog dialog;
    TextView tarih;
    int gun;
    int ay;
    int yil;
    int currentDayNumber;
    String currentDayText;
    TextView anayemek2Text;
    TextView anayemek1Text;
    TextView corba2Text;
    TextView corba1Text;
    TextView yardimciYemekText;
    TextView tatliText;
    RelativeLayout noInternet;
    LinearLayout lineer;
    RelativeLayout tarihler;

    RelativeLayout anaRelat;
    RelativeLayout corbaRelat;
    RelativeLayout eklerRelat;
    TextView cafeInfoClick;
    ImageView img;
    TextView menuName;

    CircleImageView anayemek2;
    CircleImageView anayemek1;
    CircleImageView corba2;
    CircleImageView corba1;
    CircleImageView yardimciYemek;
    CircleImageView tatli;
    String url;
    int maxDay;
    String dayText;
    TextView gunText;
    Calendar c;
    ScrollView scroll;

    TextView anayemek1kalori;
    TextView anayemek2kalori;
    TextView corba1kalori;
    TextView corba2kalori;
    TextView icecekkalori;
    TextView yardimcikalori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemekhane);

        showBannerDialog();
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        cafeInfoClick = (TextView) findViewById(R.id.cafeClickInfo);
        cafeInfoClick.setVisibility(View.GONE);
        img = (ImageView) findViewById(R.id.logo);
        img.setVisibility(View.GONE);
        menuName = (TextView) findViewById(R.id.menuName);
        menuName.setText("Yemek Listesi");
        menuName.setGravity(RelativeLayout.CENTER_IN_PARENT);


        anayemek2Text = (TextView) findViewById(R.id.anayemek2Text);
        anayemek1Text = (TextView) findViewById(R.id.anayemek1Text);
        anayemek2 = (CircleImageView) findViewById(R.id.anayemek2);
        anayemek1 = (CircleImageView) findViewById(R.id.anayemek1);
        corba2Text = (TextView) findViewById(R.id.corba2Text);
        corba1Text = (TextView) findViewById(R.id.corba1Text);
        corba2 = (CircleImageView) findViewById(R.id.corba2);
        corba1 = (CircleImageView) findViewById(R.id.corba1);
        yardimciYemekText = (TextView) findViewById(R.id.yardimciText);
        yardimciYemek = (CircleImageView) findViewById(R.id.yardimciIcon);
        tatliText = (TextView) findViewById(R.id.icecekText);
        tatli = (CircleImageView) findViewById(R.id.icecekIcon);
        tarih = (TextView) findViewById(R.id.tarih);
        anaRelat = (RelativeLayout) findViewById(R.id.anaRelat);
        corbaRelat = (RelativeLayout) findViewById(R.id.corbaRelat);
        eklerRelat = (RelativeLayout) findViewById(R.id.eklerRelat);
        noInternet = (RelativeLayout) findViewById(R.id.noInternet);
        tarihler = (RelativeLayout) findViewById(R.id.tarihler);
        scroll = (ScrollView) findViewById(R.id.scroll);
        anayemek1kalori = (TextView) findViewById(R.id.anayemek1kalori);
        anayemek2kalori = (TextView) findViewById(R.id.anayemek2kalori);
        corba1kalori = (TextView) findViewById(R.id.corba1kalori);
        corba2kalori = (TextView) findViewById(R.id.corba2kalori);
        yardimcikalori = (TextView) findViewById(R.id.yardimcikalori);
        icecekkalori = (TextView) findViewById(R.id.icecekkalori);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        gunText = (TextView) findViewById(R.id.gunText);
        Date d = new Date();
        currentDayText = sdf.format(d);
        scroll.setOnTouchListener(new OnSwipeTouchListener(YemekhaneActivity.this) {

            /*
            public void onSwipeTop() {

                Toast.makeText(YemekhaneActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(YemekhaneActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }
            */
            public void onSwipeRight() {

                scroll.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade));
                evvelsiGun();
            }

            public void onSwipeLeft() {
                scroll.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade));
                ertesiGun();
            }


        });

        noInternet.setVisibility(View.GONE);
        lineer = (LinearLayout) findViewById(R.id.lineer);
        maxDay = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        c = Calendar.getInstance();
        currentDayNumber = c.get(Calendar.DAY_OF_MONTH);
        gun = c.get(Calendar.DAY_OF_MONTH);
        ay = c.get(Calendar.MONTH) + 1;
        yil = c.get(Calendar.YEAR);
        String date = String.format("%02d", gun) + "." + String.format("%02d", ay) + "." + String.valueOf(yil);
        tarih.setText(date);
        yukle(date);
        getTodayText(date);

    }

    void parseJsonData(String jsonString) {

        if (!jsonString.equals("null")) {


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
                    anayemek2kalori.setText(mealsKalori.getString(0));
                    anayemek1Text.setText(mealsName.getString(1));
                    anayemek1kalori.setText(mealsKalori.getString(1));
                    Picasso.with(getApplicationContext()).load(mealsImgUrl.getString(0)).into(anayemek2);
                    Picasso.with(getApplicationContext()).load(mealsImgUrl.getString(1)).into(anayemek1);


                    corba2Text.setText(soupsName.getString(0));
                    corba2kalori.setText(soupsKalori.getString(0));
                    corba1Text.setText(soupsName.getString(1));
                    corba1kalori.setText(soupsKalori.getString(1));
                    Picasso.with(getApplicationContext()).load(soupsImgUrl.getString(0)).into(corba2);
                    Picasso.with(getApplicationContext()).load(soupsImgUrl.getString(1)).into(corba1);

                    yardimciYemekText.setText(snacksName.getString(1));
                    yardimcikalori.setText(snacksKalori.getString(1));
                    tatliText.setText(snacksName.getString(0));
                    icecekkalori.setText(snacksKalori.getString(0));
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
        } else {
            anaRelat.setVisibility(View.GONE);
            corbaRelat.setVisibility(View.GONE);
            eklerRelat.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
        }


    }

    public void yukle(String tarihimiz) {
        url = "http://kodlar.net/kouyemek/meals.php?date=" + tarihimiz;
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
                Toast.makeText(getApplicationContext(), "İnternet Bağlantınızı Kontrol Edin!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(YemekhaneActivity.this);
        rQueue.add(request);

        dialog.dismiss();


    }

    public void ertesiGun(View view) {
        if (gun != maxDay) {
            gun = gun + 1;
            String date = String.format("%02d", gun) + "." + String.format("%02d", ay) + "." + String.valueOf(yil);
            tarih.setText(date);
            yukle(date);
            getTodayText(date);
        }

    }

    public void ertesiGun() {
        if (gun != maxDay) {
            gun = gun + 1;
            String date = String.format("%02d", gun) + "." + String.format("%02d", ay) + "." + String.valueOf(yil);
            tarih.setText(date);
            yukle(date);
            getTodayText(date);
        }

    }

    public void evvelsiGun(View view) {
        if (gun != 1) {
            gun = gun - 1;
            String date = String.format("%02d", gun) + "." + String.format("%02d", ay) + "." + String.valueOf(yil);
            tarih.setText(date);
            yukle(date);
            getTodayText(date);
        }

    }

    public void evvelsiGun() {
        if (gun != 1) {
            gun = gun - 1;
            String date = String.format("%02d", gun) + "." + String.format("%02d", ay) + "." + String.valueOf(yil);
            tarih.setText(date);
            yukle(date);
            getTodayText(date);
        }

    }

    public String dayTurkish(String gun) {
        System.out.println("Bugünkü gün " + gun);
        return "0";
    }


    public void getDateToday() {
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

    public void turnBack(View view) {
        finish();
    }

    public void getTodayText(String dateString) {

        System.out.println("mertmert");
        Calendar b = Calendar.getInstance();
        int nowDay;
        try {
            b.setTime(new SimpleDateFormat("dd.MM.yyyy").parse(dateString));

        } catch (ParseException e) {
            System.out.println("Hata");
            e.printStackTrace();
        }
        int dayOfWeek = b.get(Calendar.DAY_OF_WEEK);
        nowDay = b.get(Calendar.DAY_OF_MONTH);
        switch (dayOfWeek) {
            case 1:
                dayText = "Pazar";
                break;
            case 2:
                dayText = "Pazartesi";
                break;
            case 3:
                dayText = "Salı";
                break;
            case 4:
                dayText = "Çarşamba";
                break;
            case 5:
                dayText = "Perşembe";
                break;
            case 6:
                dayText = "Cuma";
                break;
            case 7:
                dayText = "Cumartesi";
                break;
            default:
                dayText = "Hata";
                break;
        }
        gunText.setText(dayText);
        if (currentDayNumber == nowDay) {
            gunText.setText("Bugün");
            gunText.setTextSize(20);
            gunText.setTextColor(getResources().getColor(R.color.yesil));
        }
        if (currentDayNumber == nowDay + 1) {
            gunText.setText("Dün");
            gunText.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        if (currentDayNumber == nowDay - 1) {
            gunText.setText("Yarın");
            gunText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        }

    }

    public void showBannerDialog() {
        String url = "http://kodlar.net/kouyemek/banner.php";
        System.out.println("Girdi");
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);


        final ImageView bannerClose = (ImageView) dialog.findViewById(R.id.closeBanner);
        bannerClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                try {
                    JSONObject object = new JSONObject(string);
                    JSONArray bannerArray = object.getJSONArray("banner_url");
                    JSONArray bannerTurArray = object.getJSONArray("banner_tur");
                    final JSONArray bannerIcerik = object.getJSONArray("banner_icerik");
                    final int a = (int) (Math.random() * bannerArray.length());
                    gosterimArttir(a);
                    ImageView bannerAfis = (ImageView) dialog.findViewById(R.id.bannerAfis);
                    Picasso.with(dialog.getContext()).load(bannerArray.getString(a)).into(bannerAfis);

                    int x = Integer.parseInt(bannerTurArray.getString(a));
                    if (x == 0) {
                        bannerAfis.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String url = null;
                                try {
                                    url = bannerIcerik.getString(a);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                                tiklamaArttir(a);
                            }
                        });

                    } else {
                        bannerAfis.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String url = null;
                                try {
                                    url = bannerIcerik.getString(a);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", url, null));
                                startActivity(intent);
                                tiklamaArttir(a);
                            }
                        });

                    }


                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "İnternet Bağlantınızı Kontrol Edin!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(YemekhaneActivity.this);
        rQueue.add(request);
        dialog.show();
    }


    public void gosterimArttir(int id) {
        String istekUrl="http://kodlar.net/kouyemek/gosterim.php?banner_id="+id;
        StringRequest request = new StringRequest(istekUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(YemekhaneActivity.this);
        rQueue.add(request);

    }
    public void tiklamaArttir(int id) {
        String istekUrl="http://kodlar.net/kouyemek/tiklama.php?banner_id="+id;
        StringRequest request = new StringRequest(istekUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(YemekhaneActivity.this);
        rQueue.add(request);

    }
}
