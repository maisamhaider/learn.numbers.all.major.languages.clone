package learn.numbers.all.major.languages.clone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdView;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.activities.CustomAct;
import learn.numbers.all.major.languages.clone.activities.ExtraNumbersAct;
import learn.numbers.all.major.languages.clone.activities.NumbersAct;
import learn.numbers.all.major.languages.clone.activities.OtherLangAct;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.interfaces.TextChangedInterface;
import learn.numbers.all.major.languages.clone.preferences.Pref;
import learn.numbers.all.major.languages.clone.utils.MyUtil;


public class HomeFragment extends BaseFragment implements View.OnLongClickListener {

    private Pref preferences;
    TextView sSelected_language_tv;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {


        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        preferences = new Pref(getContext());

        AdView aView = view.findViewById(R.id.homeFrag_adView);
        adView(aView);
        sSelected_language_tv = view.findViewById(R.id.sSelected_language_tv);
        ImageView home_lang_change_iv = view.findViewById(R.id.home_lang_change_iv);
        sNameOfLang(sSelected_language_tv);
        ConstraintLayout one_to_nine_cl = view.findViewById(R.id.one_to_nine_cl);
        ConstraintLayout eleven_to_nineteen_cl = view.findViewById(R.id.eleven_to_nineteen_cl);
        ConstraintLayout ten_to_ninety_cl = view.findViewById(R.id.ten_to_ninety_cl);
        ConstraintLayout specialNumbers_cl = view.findViewById(R.id.specialNumbers_cl);
        ConstraintLayout customInput_cl = view.findViewById(R.id.customInput_cl);

        one_to_nine_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new NumbersAct(), MyAnno.Which_Number,
                        MyAnno.One_tO_9);
            }
        });
        eleven_to_nineteen_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new NumbersAct(), MyAnno.Which_Number,
                        MyAnno.Eleven_tO_19);
            }
        });
        ten_to_ninety_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new NumbersAct(), MyAnno.Which_Number,
                        MyAnno.Ten_tO_90);
            }
        });
        specialNumbers_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new ExtraNumbersAct());
            }
        });
        customInput_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new CustomAct());
            }
        });
        home_lang_change_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showLanguagesDialog(getActivity(), new TextChangedInterface() {
                    @Override
                    public boolean changed(String val) {
                        sSelected_language_tv.setText(val);
                        return false;
                    }
                });
            }
        });

        view.findViewById(R.id.one_to_100_arabic_cl).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new OtherLangAct(), MyAnno.Extra_LANGUAGE_KEY,
                        MyAnno.Arabic);
            }
        }); view.findViewById(R.id.one_to_100_persian_cl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new OtherLangAct(),MyAnno.Extra_LANGUAGE_KEY,
                        MyAnno.Persian);
            }
        }); view.findViewById(R.id.one_to_100_pakhtu_cl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new OtherLangAct(),MyAnno.Extra_LANGUAGE_KEY,
                        MyAnno.Pakhtu);
            }
        }); view.findViewById(R.id.one_to_100_urdu_cl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivityAds(new OtherLangAct(),MyAnno.Extra_LANGUAGE_KEY,
                        MyAnno.Urdu);}});

        return view;
    }


    @Override
    public boolean onLongClick(View v) {
        MyUtil util = new MyUtil();
        switch (v.getId())
        {
            case R.id.one_to_nine_cl:
                if (v.isPressed())
                {
//                    util.showView("");
                }else
                {

                }
                break;
        }
        return false;
    }
}