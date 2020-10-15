package learn.numbers.all.major.languages.clone.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.preferences.Pref;


public class SplashActivity extends AppCompatActivity {
    TextView close_tv;
    TextView ok_tv;
    View splash_v, terms_v;
    boolean previouslyStarted;
    Pref preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        close_tv = findViewById(R.id.close_tv);
        ok_tv = findViewById(R.id.ok_tv);
        splash_v = findViewById(R.id.splash_v);
        terms_v = findViewById(R.id.terms_v);
        preferences = new Pref(this);
        previouslyStarted = preferences.getBooleanData("if_Accept");
        loadFun();
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
                    ok_tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferences.setData(true, "if_Accept");
                            startActivity(new Intent(SplashActivity.this
                                    , MainAct.class));
                            finish(); }});
                    close_tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                    splash_v.setVisibility(View.GONE);
                    terms_v.setVisibility(View.VISIBLE);}
            }
        }, 1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}