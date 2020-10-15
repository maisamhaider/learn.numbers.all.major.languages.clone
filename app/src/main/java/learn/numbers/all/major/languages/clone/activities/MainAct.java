package learn.numbers.all.major.languages.clone.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.fragments.AboutFragment;
import learn.numbers.all.major.languages.clone.fragments.HomeFragment;
import learn.numbers.all.major.languages.clone.fragments.TranslatorFragment;
import learn.numbers.all.major.languages.clone.interfaces.TrueFalse;
import learn.numbers.all.major.languages.clone.preferences.Pref;

//import com.google.android.gms.ads.AdListener;

public class MainAct extends BaseAct {

    private ImageView home_nav_iv, tools_nav_iv, me_nav_iv;
    private TextView home_nav_tv, tools_nav_tv, me_nav_tv;
    private ConstraintLayout home_nav_cl, tools_nav_cl, me_nav_cl;
    private int fragInt = 0;
    public boolean isLanguagesList_llShowing = false;
    private Pref pref;
    private TextToSpeech tts;

    public void setTrueFalse(TrueFalse trueFalse) {
        this.trueFalse = trueFalse;
    }

    private TrueFalse trueFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        pref = new Pref(this);
        tts = new TextToSpeech(MainAct.this,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i)
                    { }
                });
//
//        AdView aView = findViewById(R.id.home_adView);
//        adView(aView);

        home_nav_iv = findViewById(R.id.home_nav_iv);
        tools_nav_iv = findViewById(R.id.tools_nav_iv);
        me_nav_iv = findViewById(R.id.me_nav_iv);

        home_nav_tv = findViewById(R.id.home_nav_tv);
        tools_nav_tv = findViewById(R.id.tools_nav_tv);
        me_nav_tv = findViewById(R.id.me_nav_tv);

        home_nav_cl = findViewById(R.id.home_nav_cl);
        tools_nav_cl = findViewById(R.id.translate_nav_cl);
        me_nav_cl = findViewById(R.id.me_nav_cl);

        home_nav_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFun(v);
            }
        });
        tools_nav_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFun(v);
            }
        });
        me_nav_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFun(v);
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        //ads
//        ads();

        if (fragInt == 0) {
            homeSelected();
        } else if (fragInt == 1) {
            TranslatorSelected();
        } else if (fragInt == 2) {
            aboutSelected();
        }

    }

    @Override
    public void onBackPressed() {
        if (fragInt != 0) {
            if (fragInt == 1) {
                if (isLanguagesList_llShowing) {
                    trueFalse.isTrue(true);
                } else {
                    exitt();
                }
            } else {
                getSupportFragmentManager().popBackStack();
                homeSelected();
            }
        } else {
            exitt();
        }
    }


    public void navigationFun(View view) {
        switch (view.getId()) {
            case R.id.home_nav_cl:
                homeSelected();
                break;
            case R.id.translate_nav_cl:
                if (fragInt != 1) {
                    TranslatorSelected();
                }

                break;
            case R.id.me_nav_cl:
                aboutSelected();
                break;
        }
    }

    public void exitt() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater layoutInflater = getLayoutInflater();
            @SuppressLint("InflateParams") final View dialogView =
                    layoutInflater.inflate(R.layout.exit_layout, null);
            ConstraintLayout yes_cl = dialogView.findViewById(R.id.yes_cl);
            ConstraintLayout no_cl = dialogView.findViewById(R.id.no_cl);
            ConstraintLayout rateUs_cl = dialogView.findViewById(R.id.rateUs_cl);


            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.create();
//            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();

            yes_cl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainAct.this.moveTaskToBack(true);
                    alertDialog.cancel();
                    MainAct.this.finishAffinity();
                }
            });

            no_cl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//
                    alertDialog.dismiss();

                }
            });
            rateUs_cl.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 MainAct.this.rate();
                                             }
                                         }
            );

        } catch (Exception a) {
            a.printStackTrace();
        }
    }

    public void rate() {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
    }

    public void homeSelected() {
        loadFragment(new HomeFragment());
        fragInt = 0;
        home_nav_iv.setImageAlpha(500);
        tools_nav_iv.setImageAlpha(50);
        me_nav_iv.setImageAlpha(50);

        home_nav_tv.setVisibility(View.VISIBLE);
        tools_nav_tv.setVisibility(View.GONE);
        me_nav_tv.setVisibility(View.GONE);

        home_nav_cl.setBackground(getDrawable(R.drawable.bottom_nav_d));
        tools_nav_cl.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        me_nav_cl.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

    }

    public void TranslatorSelected() {
        loadFragment(new TranslatorFragment());
        fragInt = 1;
        home_nav_iv.setImageAlpha(50);
        tools_nav_iv.setImageAlpha(500);
        me_nav_iv.setImageAlpha(50);

        home_nav_tv.setVisibility(View.GONE);
        tools_nav_tv.setVisibility(View.VISIBLE);
        me_nav_tv.setVisibility(View.GONE);

        home_nav_cl.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        tools_nav_cl.setBackground(getDrawable(R.drawable.bottom_nav_d));
        me_nav_cl.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void aboutSelected() {
        fragInt = 2;
        loadFragment(new AboutFragment());
        home_nav_iv.setImageAlpha(50);
        tools_nav_iv.setImageAlpha(50);
        me_nav_iv.setImageAlpha(500);

        home_nav_tv.setVisibility(View.GONE);
        tools_nav_tv.setVisibility(View.GONE);
        me_nav_tv.setVisibility(View.VISIBLE);

        home_nav_cl.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        tools_nav_cl.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        me_nav_cl.setBackground(getDrawable(R.drawable.bottom_nav_d));

    }

//    public void ads() {
//
//        try {
//            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                    if (interstitialAd != null && interstitialAd.isLoaded()) {
//                        interstitialAd.show();
//                    }
//                    interstitialAd.setAdListener(new AdListener() {
//                        @Override
//                        public void onAdClosed() {
////                            reqNewInterstitial();
//                        }
//                    });
//                }
//            }, 4000);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        pref.setData("", MyAnno.LAST_TEXT_INPUT);
        pref.setData("", MyAnno.LAST_TEXT_RESULT);
        tts.stop();
        tts.shutdown();
    }

    @Override
    protected void onPause() {
        super.onPause();
        tts.stop();
        tts.shutdown();
    }

}