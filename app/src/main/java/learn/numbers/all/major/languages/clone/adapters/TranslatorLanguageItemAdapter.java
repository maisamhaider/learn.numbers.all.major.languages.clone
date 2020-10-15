package learn.numbers.all.major.languages.clone.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import learn.numbers.all.major.languages.clone.R;
import learn.numbers.all.major.languages.clone.annotations.MyAnno;
import learn.numbers.all.major.languages.clone.interfaces.IsTargetLanguage;
import learn.numbers.all.major.languages.clone.preferences.Pref;

public class TranslatorLanguageItemAdapter extends
        RecyclerView.Adapter<TranslatorLanguageItemAdapter.LanguageNameHolder> {

    Context context;
    List<String> languageCodeL;
    List<String> selectedLang;
    boolean isTarget;
    Pref pref;
    IsTargetLanguage isTargetLanguage;
    TranslatorOptions translatorOptions;
    DownloadConditions conditions;
    Translator translator;
    AlertDialog dialog;
    int cPos1 = -1;
    int cPos2 = -1;

    public TranslatorLanguageItemAdapter(Context context, List<String> languageCodeL,
                                         boolean isTarget, IsTargetLanguage isTargetLanguage) {
        this.context = context;
        this.languageCodeL = languageCodeL;
        this.isTarget = isTarget;
        selectedLang = new ArrayList<>();
        pref = new Pref(context);
        this.isTargetLanguage = isTargetLanguage;
    }

    @NonNull
    @Override
    public LanguageNameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tranlator_language_items_layout, parent, false);
        return new LanguageNameHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull final LanguageNameHolder holder, final int position) {
        final String langCode = languageCodeL.get(position);// language code
        Locale locale = new Locale(langCode); //pass to local the language code
        String language = locale.getDisplayLanguage(locale); //get language name from language code

        holder.languageName_tv.setText(language);
        if (isTarget) {
            if (pref.getStringData(MyAnno.TARGET_LANGUAGE,true).matches(langCode)) {
                holder.isDOwnOrSelect_iv.setVisibility(View.VISIBLE);

             } else {
                holder.isDOwnOrSelect_iv.setVisibility(View.GONE);

            }
        } else {
            if (pref.getStringData(MyAnno.USER_LANGUAGE,true).matches(langCode)) {
                holder.isDOwnOrSelect_iv.setVisibility(View.VISIBLE);
                holder.languageNameItem_cl.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

            } else {
                 holder.isDOwnOrSelect_iv.setVisibility(View.GONE);
                holder.languageNameItem_cl.setBackgroundColor(Color.WHITE);

            } }
        holder.languageNameItem_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTarget) {
                    pref.setData(langCode, MyAnno.TARGET_LANGUAGE);

                    if (cPos1 == position) {
                        holder.isDOwnOrSelect_iv.setVisibility(View.VISIBLE);
                        cPos1 = -1;
                        notifyDataSetChanged();
                        return;
                    }
                    cPos1 = position;
                    isTargetLanguage.language(true);
                } else {
                    pref.setData(langCode, MyAnno.USER_LANGUAGE);
                    if (cPos2 == position) {
                        holder.isDOwnOrSelect_iv.setVisibility(View.VISIBLE);
                        cPos2 = -1;
                        notifyDataSetChanged();
                        return;
                    }
                    cPos2 = position;
                    isTargetLanguage.language(false);
                }
                notifyDataSetChanged();
            }
        });}

    @Override
    public int getItemCount() {
        return languageCodeL.size();
    }

    class LanguageNameHolder extends RecyclerView.ViewHolder {
        ConstraintLayout languageNameItem_cl;
        TextView languageName_tv;
        ImageView isDOwnOrSelect_iv;

        public LanguageNameHolder(@NonNull View itemView) {
            super(itemView);
            languageNameItem_cl = itemView.findViewById(R.id.languageNameItem_cl);
            languageName_tv = itemView.findViewById(R.id.languageName_tv);
            isDOwnOrSelect_iv = itemView.findViewById(R.id.isDOwnOrSelect_iv);
        }
    }


}
