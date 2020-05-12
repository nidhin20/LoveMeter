package com.nidhin.lovemeasure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    Button btnsearch;
    TextView firstname;
    TextView Secondnames;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        imageView= findViewById(R.id.imageView);
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.rotateanim);
        imageView.startAnimation(anim);

        com.google.android.gms.ads.AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        btnsearch = findViewById(R.id.btncalculate);
        firstname = findViewById(R.id.txtfirstname);
        Secondnames = findViewById(R.id.txtsecondname);
        firstname.setText("");
        Secondnames.setText("");

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname.setError(null);
                Secondnames.setError(null);
                if (firstname.getText().toString().equals("")) {
                    firstname.setError("Name can not be empty");
                    Toast.makeText(getApplication(),"Name can not be empty",Toast.LENGTH_LONG).show();
                    return;
                }
                if (Secondnames.getText().toString().equals("")) {
                    Secondnames.setError("Partner name can not be empty");
                    Toast.makeText(getApplication(),"Partner name can not be empty",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent result = new Intent(MainActivity.this, com.nidhin.lovemeasure.result.class);
                result.putExtra("first", firstname.getText());
                result.putExtra("second", Secondnames.getText());
                startActivity(result);
            }
        });
    }
}
