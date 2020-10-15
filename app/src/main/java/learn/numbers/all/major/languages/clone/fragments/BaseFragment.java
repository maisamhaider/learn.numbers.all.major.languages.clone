package learn.numbers.all.major.languages.clone.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.preferences.Pref;

//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.LoadAdError;

public class BaseFragment extends Fragment {
    private Pref myPreferences;
//    private InterstitialAd interstitialAd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPreferences = new Pref(getActivity());
//        loadInterstitial();
    }

//    public void loadInterstitial() {
//        interstitialAd = new InterstitialAd(getActivity());
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
//        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//
//    }
//
//    public void reqNewInterstitial() {
//        AdRequest adRequest = new AdRequest.Builder()
//                .build();
//        interstitialAd.loadAd(adRequest);
//    }

    public void newActivityAds(final Activity activity, final String key, final String data) {
//        if (interstitialAd != null && interstitialAd.isLoaded()) {
////            interstitialAd.show();
//        } else {
//            Intent intent = new Intent(getActivity(), activity.getClass());
//            intent.putExtra(key, data);
//            startActivity(intent);
//        }
//        interstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                reqNewInterstitial();
//                Intent intent = new Intent(getActivity(), activity.getClass());
//                intent.putExtra(key, data);
//                startActivity(intent);
//            }
//        });

        Intent intent = new Intent(getActivity(), activity.getClass());
        intent.putExtra(key, data);
        startActivity(intent);

    }

    public void newActivityAds(final Activity activity) {
//        if (interstitialAd != null && interstitialAd.isLoaded()) {
//            interstitialAd.show();
//        } else {
//            Intent intent = new Intent(getActivity(), activity.getClass());
//            startActivity(intent);
//        }
//        interstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                reqNewInterstitial();
//                Intent intent = new Intent(getActivity(), activity.getClass());
//                startActivity(intent);
//            }
//        });
        Intent intent = new Intent(getActivity(), activity.getClass());
        startActivity(intent);

    }
//
//    public void adView(final AdView adView) {
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

    @SuppressLint("SetTextI18n")
    public void sNameOfLang(TextView textView) {
        switch (myPreferences.getStringData(MyAnno.S_LANGUAGE_KEY, false)) {
            case MyAnno.Afrikaan:
                textView.setText(MyAnno.Afrikaan);
                break;
            case MyAnno.Chinese:
                textView.setText(MyAnno.Chinese);
                break;
            case MyAnno.Estonian:
                textView.setText(MyAnno.Estonian);
                break;
            case MyAnno.Finnsh:
                textView.setText(MyAnno.Finnsh);
                break;
            case MyAnno.French:
                textView.setText(MyAnno.French);
                break;
//            case MyAnno.Georgian:
//                textView.setText(MyAnno.Georgian );
//                break;
            case MyAnno.German:
                textView.setText(MyAnno.German);
                break;
            case MyAnno.Greek:
                textView.setText(MyAnno.Greek);
                break;
            case MyAnno.Hebrew:
                textView.setText(MyAnno.Hebrew);
                break;
            case MyAnno.Indonesian:
                textView.setText(MyAnno.Indonesian);
                break;
            case MyAnno.Irish:
                textView.setText(MyAnno.Irish);
                break;
            case MyAnno.Italian:
                textView.setText(MyAnno.Italian);
                break;
            case MyAnno.Japanese:
                textView.setText(MyAnno.Japanese);
                break;
            case MyAnno.Korean:
                textView.setText(MyAnno.Korean);
                break;
            case MyAnno.Latin:
                textView.setText(MyAnno.Latin);
                break;
            case MyAnno.Luxembourgish:
                textView.setText(MyAnno.Luxembourgish);
                break;
            case MyAnno.Portuguese:
                textView.setText(MyAnno.Portuguese);
                break;
            case MyAnno.Russian:
                textView.setText(MyAnno.Russian);
                break;
            case MyAnno.Spanish:
                textView.setText(MyAnno.Spanish);
                break;
            case MyAnno.Thai:
                textView.setText(MyAnno.Thai);
                break;
            case MyAnno.Turkish:
                textView.setText(MyAnno.Turkish);
                break;

            default:
                textView.setText(MyAnno.English);
        }
    }



//    public PopupWindow popupWindowDisplay(String message) {
//        PopupWindow popupWindow = new PopupWindow(getActivity());
//        // inflate your layout or dynamically add view
//        final LayoutInflater inflater = (LayoutInflater) getActivity().
//                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View view = inflater.inflate(R.layout.popup_xml, null);
//
//        TextView popup_tv = view.findViewById(R.id.popup_tv);
//        popup_tv.setText(message);
//
//        popupWindow.setFocusable(true);
//        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
//        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//        popupWindow.setContentView(view);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//            }
//        });
//
//        return popupWindow;
//    }
//
//    public void showPopup(View view, String message) {
//        final PopupWindow popupWindowObj = popupWindowDisplay(message);
//        popupWindowObj.showAsDropDown(view, view.getWidth() / 2, 10);
//
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (popupWindowObj.isShowing()) {
//                    popupWindowObj.dismiss();
//                }
//            }
//        }, 3000);
//
//    }


}
