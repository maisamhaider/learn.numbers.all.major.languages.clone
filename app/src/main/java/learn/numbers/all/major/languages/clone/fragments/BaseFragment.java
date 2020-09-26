package learn.numbers.all.major.languages.clone.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.adapters.LangAdapter;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.interfaces.TextChangedInterface;
import learn.numbers.all.major.languages.clone.preferences.Pref;

public class BaseFragment extends Fragment {
    private Pref myPreferences;
    private InterstitialAd interstitialAd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPreferences = new Pref(getActivity());
        loadInterstitial();
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }

    public void loadInterstitial() {
        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId(getString(R.string.interstitial));
        try {
            if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
                AdRequest adRequest = new AdRequest.Builder().build();
                interstitialAd.loadAd(adRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        reqNewInterstitial();

        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }

    public void reqNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        interstitialAd.loadAd(adRequest);
    }

    public void newActivityAds(final Activity activity, final String key, final String data) {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Intent intent = new Intent(getActivity(), activity.getClass());
            intent.putExtra(key, data);
            startActivity(intent);
        }
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                reqNewInterstitial();
                Intent intent = new Intent(getActivity(), activity.getClass());
                intent.putExtra(key, data);
                startActivity(intent);
            }
        });

    }

    public void newActivityAds(final Activity activity) {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Intent intent = new Intent(getActivity(), activity.getClass());
            startActivity(intent);
        }
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                reqNewInterstitial();
                Intent intent = new Intent(getActivity(), activity.getClass());
                startActivity(intent);
            }
        });

    }

    public void adView(final AdView adView) {

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                adView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(LoadAdError var1) {
                adView.setVisibility(View.GONE);
            }

        });
    }

    @SuppressLint("SetTextI18n")
    public void sNameOfLang(TextView textView) {
        switch (myPreferences.getStringData(MyAnno.S_LANGUAGE_KEY)) {
            case MyAnno.Afrikaan:
                textView.setText(MyAnno.Afrikaan + "  ");
                break;
            case MyAnno.Chinese:
                textView.setText(MyAnno.Chinese + "  ");
                break;
            case MyAnno.Estonian:
                textView.setText(MyAnno.Estonian + "  ");
                break;
            case MyAnno.Finnsh:
                textView.setText(MyAnno.Finnsh + "  ");
                break;
            case MyAnno.French:
                textView.setText(MyAnno.French + "  ");
                break;
//            case MyAnno.Georgian:
//                textView.setText(MyAnno.Georgian + "  ");
//                break;
            case MyAnno.German:
                textView.setText(MyAnno.German + "  ");
                break;
            case MyAnno.Greek:
                textView.setText(MyAnno.Greek + "  ");
                break;
            case MyAnno.Hebrew:
                textView.setText(MyAnno.Hebrew + "  ");
                break;
            case MyAnno.Indonesian:
                textView.setText(MyAnno.Indonesian + "  ");
                break;
            case MyAnno.Irish:
                textView.setText(MyAnno.Irish + "  ");
                break;
            case MyAnno.Italian:
                textView.setText(MyAnno.Italian + "  ");
                break;
            case MyAnno.Japanese:
                textView.setText(MyAnno.Japanese + "  ");
                break;
            case MyAnno.Korean:
                textView.setText(MyAnno.Korean + "  ");
                break;
            case MyAnno.Latin:
                textView.setText(MyAnno.Latin + "  ");
                break;
            case MyAnno.Luxembourgish:
                textView.setText(MyAnno.Luxembourgish + "  ");
                break;
            case MyAnno.Portuguese:
                textView.setText(MyAnno.Portuguese + "  ");
                break;
            case MyAnno.Russian:
                textView.setText(MyAnno.Russian + "  ");
                break;
            case MyAnno.Spanish:
                textView.setText(MyAnno.Spanish + "  ");
                break;
            case MyAnno.Thai:
                textView.setText(MyAnno.Thai + "  ");
                break;
            case MyAnno.Turkish:
                textView.setText(MyAnno.Turkish + "  ");
                break;

            default:
                textView.setText(MyAnno.English + "  ");
        }
    }

    public void showLanguagesDialog(Context context, TextChangedInterface textChanged) {
        View view = LayoutInflater.from(context).inflate(R.layout.language_dialog_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setCancelable(true);

        final AlertDialog dialog = builder.create();
        dialog.show();
        ArrayList<String> languages = new ArrayList<>();
        languages.add(MyAnno.Afrikaan);
        languages.add(MyAnno.Chinese);
        languages.add(MyAnno.English);
        languages.add(MyAnno.Estonian);
        languages.add(MyAnno.Finnsh);
        languages.add(MyAnno.French);
//        languages.add(MyAnno.Georgian);
        languages.add(MyAnno.German);
        languages.add(MyAnno.Greek);
        languages.add(MyAnno.Hebrew);
        languages.add(MyAnno.Indonesian);
        languages.add(MyAnno.Irish);
        languages.add(MyAnno.Italian);
        languages.add(MyAnno.Japanese);
        languages.add(MyAnno.Korean);
        languages.add(MyAnno.Latin);
        languages.add(MyAnno.Luxembourgish);
        languages.add(MyAnno.Portuguese);
        languages.add(MyAnno.Russian);
        languages.add(MyAnno.Spanish);
        languages.add(MyAnno.Thai);
        languages.add(MyAnno.Turkish);
        TextView done_tv = view.findViewById(R.id.done_tv);
        RecyclerView lang_rv = view.findViewById(R.id.lang_rv);
        LangAdapter adapter = new LangAdapter(context, languages);
        adapter.init(textChanged);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lang_rv.setLayoutManager(layoutManager);
        lang_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        done_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


}
