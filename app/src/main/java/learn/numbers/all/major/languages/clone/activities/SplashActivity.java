package learn.numbers.all.major.languages.clone.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.preferences.Pref;


public class SplashActivity extends AppCompatActivity {
    InterstitialAd interstitialAd;
    Button close_btn;
    Button ok_btn;
    View splash_v, terms_v;
    boolean previouslyStarted;
    Pref preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstitial));
        reqNewInterstitial();
        close_btn = findViewById(R.id.close_btn);
        ok_btn = findViewById(R.id.ok_btn);
        splash_v = findViewById(R.id.splash_v);
        terms_v = findViewById(R.id.terms_v);
        preferences = new Pref(this);
        previouslyStarted = preferences.getBooleanData("if_Accept");
        LWithAds(this);

    }

    public void loadFun() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (previouslyStarted) {
                    startActivity(new Intent(SplashActivity.this,
                            MainAct.class));
                    finish();
                } else {
                    ok_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferences.setData(true, "if_Accept");
                            startActivity(new Intent(SplashActivity.this
                                    , MainAct.class));
                            finish(); }});
                    close_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                    splash_v.setVisibility(View.GONE);
                    terms_v.setVisibility(View.VISIBLE);
                }
            }
        }, 3000);
    }
    public void LWithAds(final Context context) {
        try {
            final ProgressDialog showDialog = ProgressDialog.show(context,
                    getString(R.string.app_name), "Please wait for few seconds",
                    true);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {

                    showDialog.dismiss();
                    if (interstitialAd != null && interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    } else {
                        SplashActivity.this.loadFun();
                    }
                    interstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            loadFun();
                            reqNewInterstitial();
                        }
                    });
                }
            }, 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reqNewInterstitial() {
        interstitialAd.loadAd(new AdRequest.Builder().build());

    }
}