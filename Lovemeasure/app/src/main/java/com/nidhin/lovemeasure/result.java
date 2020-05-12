package com.nidhin.lovemeasure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.HashMap;
import java.util.Map;

public class result extends AppCompatActivity {

    TextView txtsearch;
    String flames = "FLAMES";
    String resultout = "";
    String finalsecondname = "";
    String finalfirstname = "";

    Map<String, String> Flamesvalues = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        com.google.android.gms.ads.AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        txtsearch = findViewById(R.id.txtsearch);
        Flamesvalues.put("F", "Friend");
        Flamesvalues.put("L", "Love");
        Flamesvalues.put("A", "Affection");
        Flamesvalues.put("M", "Marriage");
        Flamesvalues.put("E", "Enemy");
        Flamesvalues.put("S", "Siblings");
        Intent i = getIntent();
        finalfirstname = i.getExtras().get("first").toString();
        finalsecondname = i.getExtras().get("second").toString();
        calculate(finalfirstname, finalsecondname);
    }

    public void calculate(String firstname, String secondname) {
        //txtsearch = findViewById(R.id.txtsearch);
        secondname = secondname.toLowerCase();
        char[] firstnamechar = firstname.toLowerCase().toCharArray();
        // char[] secondnamechar = secondname.toCharArray();
        txtsearch.setText("Matching the names...");
        int leftcount = 0;
        for (int i = 0; i < firstnamechar.length; i++) {

            if (firstnamechar[i] >= 'a' && Character.toLowerCase(firstnamechar[i]) <= 'z') {
                int index = secondname.indexOf(firstnamechar[i]);
                if (secondname.indexOf(firstnamechar[i]) <= -1) {
                    leftcount = leftcount + 1;
                    //txtsearch.setText(Integer.toString(leftcount));
                } else {
                    secondname = secondname.substring(0, index) + secondname.substring(index + 1, secondname.length());
                    //txtsearch.setText(txtsearch.getText() + "  " + secondname);
                }
            }

        }
        leftcount = leftcount + secondname.length();
        int left = leftcount % 6;
        if (left == 0 && leftcount>6) {
            left = 6;
        }else if (leftcount ==0){
            left=1;
        }
        resultout = Flamesvalues.get(Character.toString(flames.toCharArray()[left - 1]));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //prgbar.setVisibility(View.GONE);
                Intent currentintent = new Intent(result.this, finalresult.class);
                currentintent.putExtra("finalresult", resultout);
                currentintent.putExtra("first", finalfirstname);
                currentintent.putExtra("second", finalsecondname);
                startActivity(currentintent);
                //txtsearch.setText(resultout);
            }
        }, 2000);

    }
}
