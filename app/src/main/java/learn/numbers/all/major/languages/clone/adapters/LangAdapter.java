package learn.numbers.all.major.languages.clone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.interfaces.TextChangedInterface;
import learn.numbers.all.major.languages.clone.preferences.Pref;

public class LangAdapter extends RecyclerView.Adapter<LangAdapter.LanguageHolder> {

    private Context context;
    private ArrayList<String> languagesAL;
    private ArrayList<String> list = new ArrayList<>();
    private Pref preferences;
    private TextChangedInterface textChangedInterface;
    private int selected_position = -1;
    InterstitialAd interstitialAd;

    public LangAdapter(Context context, ArrayList<String> languagesAL) {
        this.context = context;
        this.languagesAL = languagesAL;
        preferences = new Pref(context);
        this.list.add(preferences.getStringData(MyAnno.S_LANGUAGE_KEY,false));
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(context.getResources().getString(R.string.interstitial));
        reqNewInterstitial();
    }

    @NonNull
    @Override
    public LanguageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lang_name_item, parent, false);
        return new LanguageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LanguageHolder holder, final int position) {
        final String cLanguageName = languagesAL.get(position);

        holder.langName_tv.setText(cLanguageName);

        imageVisibility(holder.sLangItem_iv, cLanguageName);

        holder.langItemMain_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (interstitialAd != null && interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    }
                    else
                        {
                            final String name = languagesAL.get(position);
                            String lastLang = preferences.getStringData(MyAnno.S_LANGUAGE_KEY,false);
                            list.remove(lastLang);
                            preferences.setData(name, MyAnno.S_LANGUAGE_KEY);
                            String newLang = preferences.getStringData(MyAnno.S_LANGUAGE_KEY,false);
                            list.add(newLang);
//                imageVisibility(holder.sLanguageItem_iv, newLang);
                            if (selected_position == position) {
                                holder.sLangItem_iv.setVisibility(View.VISIBLE);
                                selected_position = -1;
                                notifyDataSetChanged();
                                return;
                            }
                            selected_position = position;
                            textChangedInterface.changed(newLang);
                            notifyDataSetChanged();
                    }
                    interstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            reqNewInterstitial();
                            final String name = languagesAL.get(position);
                            String lastLang = preferences.getStringData(MyAnno.S_LANGUAGE_KEY,false);
                            list.remove(lastLang);
                            preferences.setData(name, MyAnno.S_LANGUAGE_KEY);
                            String newLang = preferences.getStringData(MyAnno.S_LANGUAGE_KEY,false);
                            list.add(newLang);
//                imageVisibility(holder.sLanguageItem_iv, newLang);
                            if (selected_position == position) {
                                holder.sLangItem_iv.setVisibility(View.VISIBLE);
                                selected_position = -1;
                                notifyDataSetChanged();
                                return;
                            }
                            selected_position = position;
                            textChangedInterface.changed(newLang);
                            notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void imageVisibility(ImageView v, String s) {

        if (list.contains(s)) {
            v.setVisibility(View.VISIBLE);
        } else {
            v.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return languagesAL.size();
    }

    class LanguageHolder extends RecyclerView.ViewHolder {
        TextView langName_tv;
        ImageView sLangItem_iv;
        RelativeLayout langItemMain_cl;

        public LanguageHolder(@NonNull View itemView) {
            super(itemView);
            langName_tv = itemView.findViewById(R.id.langName_tv);
            sLangItem_iv = itemView.findViewById(R.id.sLangItem_iv);
            langItemMain_cl = itemView.findViewById(R.id.langItemMain_cl);
        }
    }

    public void reqNewInterstitial() {
        interstitialAd.loadAd(new AdRequest.Builder().build());

    }

    public void init(TextChangedInterface textChangedInterface) {
        this.textChangedInterface = textChangedInterface;
    }
}
