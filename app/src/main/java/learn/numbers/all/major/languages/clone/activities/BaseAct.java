package learn.numbers.all.major.languages.clone.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Locale;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.interfaces.Language;
import learn.numbers.all.major.languages.clone.languagesutils.AFRIKAANNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.CHINESENumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.ENGLISHNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.ESTONIANNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.FRENCHNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.GERMANNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.GREEKumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.HEBREWNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.INDONESIANNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.IRISHNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.ITALIANNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.JAPANESENumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.KOREANNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.LATINNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.LUXEMBOURGISHNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.PORTUGUESENumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.RUSSIANNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.SPANISHNumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.THAINumConverter;
import learn.numbers.all.major.languages.clone.languagesutils.TURKIShNumConverter;
import learn.numbers.all.major.languages.clone.preferences.Pref;

public class BaseAct extends AppCompatActivity {
    private Pref preferences;
    public Language converter;
    private TextToSpeech tts;
    //    InterstitialAd interstitialAd;
    private int counter = 0;
    ProgressDialog showDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = new Pref(BaseAct.this);
//        loadInterstitial();
        initConverters();
        tts = new TextToSpeech(this,
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                    }
                });

        tts.setLanguage(Locale.US);
        tts.setSpeechRate(1f);

    }

//    public void loadInterstitial() {
//        interstitialAd = new InterstitialAd(getApplicationContext());
//        interstitialAd.setAdUnitId(getString(R.string.interstitial));
//        try {
//            if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
//                AdRequest adRequest = new AdRequest.Builder().build();
//                interstitialAd.loadAd(adRequest);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        reqNewInterstitial();
//
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//    }

//    public void reqNewInterstitial() {
//        AdRequest adRequest = new AdRequest.Builder()
//                .build();
//        interstitialAd.loadAd(adRequest);
//    }

    public void sNFragmentAds(final Fragment fragment) {
//        if (interstitialAd != null && interstitialAd.isLoaded()) {
////            interstitialAd.show();
//        } else {
//            loadFragment(fragment);
//        }
//        interstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                reqNewInterstitial();
//
//            }
//        });
        loadFragment(fragment);

    }

//    public void adView(final AdView adView) {
//
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
//        adView.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                adView.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAdFailedToLoad(LoadAdError var1) {
//                adView.setVisibility(View.GONE);
//            }
//
//        });
//    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragContainer, fragment).commit();
    }

    public void initConverters() {

        switch (preferences.getStringData(MyAnno.S_LANGUAGE_KEY, false)) {
            case MyAnno.Afrikaan:
                converter = new AFRIKAANNumConverter();
                break;
            case MyAnno.Chinese:
                converter = new CHINESENumConverter();
                break;
            case MyAnno.English:
                converter = new ENGLISHNumConverter();
                break;
            case MyAnno.Estonian:
                converter = new ESTONIANNumConverter();
                break;
            case MyAnno.French:
                converter = new FRENCHNumConverter();
                break;
//            case MyAnno.Georgian:
//                converter = new GEORGIANNumConverter();
//                break;
            case MyAnno.German:
                converter = new GERMANNumConverter();
                break;
            case MyAnno.Greek:
                converter = new GREEKumConverter();
                break;
            case MyAnno.Hebrew:
                converter = new HEBREWNumConverter();
                break;
            case MyAnno.Indonesian:
                converter = new INDONESIANNumConverter();
                break;
            case MyAnno.Irish:
                converter = new IRISHNumConverter();
                break;
            case MyAnno.Italian:
                converter = new ITALIANNumConverter();
                break;
            case MyAnno.Japanese:
                converter = new JAPANESENumConverter();
                break;
            case MyAnno.Korean:
                converter = new KOREANNumConverter();
                break;
            case MyAnno.Latin:
                converter = new LATINNumConverter();
                break;
            case MyAnno.Luxembourgish:
                converter = new LUXEMBOURGISHNumConverter();
                break;
            case MyAnno.Portuguese:
                converter = new PORTUGUESENumConverter();
                break;
            case MyAnno.Russian:
                converter = new RUSSIANNumConverter();
                break;
            case MyAnno.Spanish:
                converter = new SPANISHNumConverter();
                break;
            case MyAnno.Thai:
                converter = new THAINumConverter();
                break;
            case MyAnno.Turkish:
                converter = new TURKIShNumConverter();
                break;
            default:
                converter = new ENGLISHNumConverter();

        }
    }

    public void callNumber(final String numString) {

//        if (tts!=null && tts.isSpeaking() && counter != 1) {
//            tts.stop();
//            tts.shutdown();
//        }

//        tts = new TextToSpeech(BaseAct.this,
//                new TextToSpeech.OnInitListener() {
//                    @Override
//                    public void onInit(int i) {
//                        if (i == TextToSpeech.SUCCESS) {
//
//                        } else {
//                            Toast.makeText(BaseAct.this, "Error with TTS", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });


        if (tts.isSpeaking()) {
            tts.stop();
            tts.speak(numString, TextToSpeech.QUEUE_FLUSH,
                    null, null);
            return;
        }
        tts.speak(numString, TextToSpeech.QUEUE_FLUSH,
                null, null);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (showDialog != null) {
            showDialog.dismiss();
        }
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void loadingDialog() {
        try {
            showDialog = ProgressDialog.show(this,
                    getString(R.string.app_name), "Please wait a seconds",
                    true);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (showDialog != null) {
                        showDialog.dismiss();
                    }
                }
            }, 2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
