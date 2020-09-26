package learn.numbers.all.major.languages.clone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdView;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.activities.OtherLangAct;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;


public class CountingFragment extends BaseFragment {

    private  View view;
    public CountingFragment() {
        // Required empty public constructor
    }

    public static CountingFragment newInstance() {


        return new CountingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_counting,container,false);
            AdView aView = view.findViewById(R.id.extraFrag_adView);
            adView(aView);
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
}